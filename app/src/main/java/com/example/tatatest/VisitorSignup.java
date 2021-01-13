package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisitorSignup extends AppCompatActivity {





  public static boolean emailValidat(String str) {
    String reg = "^(.+)@(.+)$";
    Pattern p = Pattern.compile(reg);
    Matcher matcher = p.matcher(str);
      return matcher.matches();


  }


  public static boolean passValidat(String str1) {
    String reg = "(?=^.{6,10}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,])(?!.*\\s).*$";
    Pattern pp = Pattern.compile(reg);
    Matcher matcher = pp.matcher(str1);
      return matcher.matches();
  }



  private EditText username, Upassword, Ucnfpassword, Uemail,userId;
  private Button signup;
  private TextView logo;
  DatabaseReference ref;
  Member member;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_visitor_signup);

    // Toast.makeText(ActivitySignIN.this, "Firebase connection valid", Toast.LENGTH_SHORT).show();


    logo = findViewById(R.id.etVLOGO);
    userId= findViewById(R.id.userId);
    username = findViewById(R.id.etVisitoR);
    Upassword = findViewById(R.id.etVPassword);
    Ucnfpassword = findViewById(R.id.etVCnfPassword);
    Uemail = findViewById(R.id.etVEmail);
    signup = findViewById(R.id.btVSIGNUP);
    member= new Member();
    ref=FirebaseDatabase.getInstance().getReference().child("Visitor");



    String name = username.getText().toString();
    String password = Upassword.getText().toString();
    String cPassword = Ucnfpassword.getText().toString();
    String email = Uemail.getText().toString();
    member.setId(username.getText().toString().trim());
    member.setPassword(Upassword.getText().toString().trim());
    member.setEmail(Uemail.getText().toString().trim());


    signup.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {



        String userID=userId.getText().toString();
        String id = username.getText().toString();
        String email = Uemail.getText().toString();
        String password = Upassword.getText().toString();
        String cPassword = Ucnfpassword.getText().toString();
        member.setUserId(userId.getText().toString());
        member.setId(username.getText().toString().trim());
        member.setPassword(Upassword.getText().toString().trim());
        member.setEmail(Uemail.getText().toString().trim());




        if (id.isEmpty())
        {
          username.setError("required");
        }
        else if( email.isEmpty() && !emailValidat(email)){
          Uemail.setError("required");
        }
        else if( !emailValidat(email)){
          Uemail.setError("Please Enter Correct Email");
        }
        else if(password.isEmpty()){
          Upassword.setError("required");
        }
        else if( !passValidat(password)){
          Upassword.setError("please enter atleast 1 capital 1 small 1 digit 1 special character!!");
        }

        else if( !password.equals(cPassword)){
          Toast.makeText(VisitorSignup.this,"Password Not Match!!",Toast.LENGTH_SHORT).show();
        }



        else {
          //Toast.makeText(VisitorSignup.this,""+email,Toast.LENGTH_SHORT).show();
          ref.orderByChild("userId").equalTo(userId.getText().toString().trim())
                  .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      if(dataSnapshot.exists()){
                        //Toast.makeText(VisitorSignup.this,"User Id is in use Please take another User ID!!",Toast.LENGTH_SHORT).show();
                        userId.setError("User Id is in use Please take another User ID!!");
                      }
                      else{
                        ref=FirebaseDatabase.getInstance().getReference("Visitor").child(userId.getText().toString());
                        ref.setValue(member);
                        openActivity2();
                      }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                  });

                          //reff=FirebaseDatabase.getInstance().getReference("Visitor").child(email);
                          //reff.setValue(member);
                          // reff.child(String.valueOf(maxid+1)).setValue("member");




        }

      }

      private void openActivity2() {

        Intent intent = new Intent(VisitorSignup.this, VisitorLogin.class);
        startActivity(intent);
      }

    });}}
