package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VisitorLogin extends AppCompatActivity {

  private Button login,Signup;
  private EditText userId,VPassword;
  DatabaseReference ref;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_visitor_login);


    userId= findViewById(R.id.etVisitorName);
    VPassword= findViewById(R.id.etEpassword);
    login= findViewById(R.id.btELogin);
    Signup= findViewById(R.id.btESignup);
    DatabaseReference ref;

    Signup.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EmployeeSignup();
      }

      private void EmployeeSignup() {
        Intent intent=new Intent(VisitorLogin.this,VisitorSignup.class);
        startActivity(intent);
      }
    });




    login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openActivityVisitorLoginPage();


      }
    });
  }

      private void openActivityVisitorLoginPage() {
    ref=FirebaseDatabase.getInstance().getReference().child("Visitor");

        ref.orderByChild("userId").equalTo(userId.getText().toString().trim())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                      //Toast.makeText(VisitorSignup.this,"User Id is in use Please take another User ID!!",Toast.LENGTH_SHORT).show();

                      ref = FirebaseDatabase.getInstance().getReference("Visitor").child(userId.getText().toString().trim()).child("password");
                      ref.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                          String pass = dataSnapshot.getValue(String.class);
                          if (pass.equals(VPassword.getText().toString().trim())) {

                            Intent intent = new Intent(VisitorLogin.this, Visitor_Home.class);
                            intent.putExtra("id",userId.getText().toString().trim());
                            startActivity(intent);
                            userId.setText("");
                            VPassword.setText("");
                          } else {
                            Toast.makeText(VisitorLogin.this, "your id and Password is Not correct", Toast.LENGTH_SHORT).show();
                            recreate();


                          }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                      });
                    }
                    else{
                      Toast.makeText(VisitorLogin.this, "your id and Password is Not correct", Toast.LENGTH_SHORT).show();
                      recreate();
                    }
                  } @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
                });


      }
}
