package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Forget extends AppCompatActivity {
    EditText text, text1;
    String id;
    DatabaseReference ref;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        text = findViewById(R.id.FText);
        text1 = findViewById(R.id.FText2);
        submit= findViewById(R.id.FButton);
        id = getIntent().getStringExtra("id");
        String value=text.getText().toString();
        Toast.makeText(Forget.this,""+id,Toast.LENGTH_SHORT).show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.getText().toString().equals(text1.getText().toString())) {
                    ref = FirebaseDatabase.getInstance().getReference("employee").child(id);

                    ref.child("password").setValue(text.getText().toString());
                    Toast.makeText(Forget.this,"Password Succesfully change",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Forget.this,"Password Does not match",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
