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

public class Forget_Email extends AppCompatActivity {
    EditText Evalidate;
    DatabaseReference ref;
    Button submit;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__email);
        Evalidate= findViewById(R.id.EmailValidate);
         Email=Evalidate.getText().toString() ;
        submit= findViewById(R.id.ESubmit);
        ref= FirebaseDatabase.getInstance().getReference("employee");



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.orderByChild("id").equalTo(Evalidate.getText().toString().trim())
                        .addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Intent intent=new Intent(Forget_Email.this,Forget.class);
                            intent.putExtra("id",Evalidate.getText().toString().trim());
                            startActivity(intent);
                            Toast.makeText(Forget_Email.this,"Id "+Evalidate.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Forget_Email.this,"Id not Exits",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
}
}
