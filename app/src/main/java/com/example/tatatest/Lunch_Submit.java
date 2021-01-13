package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Lunch_Submit extends AppCompatActivity {
    TextView tex1,tex2,tex3,tex4,tex5;
    Button submit12;
    String name="yasir",id,slot,item;
    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    String currentTime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());
    DatabaseReference ref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch__submit);
        tex1= findViewById(R.id.st1);
        tex2= findViewById(R.id.st2);
        tex3= findViewById(R.id.st3);
        tex4= findViewById(R.id.st4);
        tex5= findViewById(R.id.st5);
        submit12= findViewById(R.id.st_button1);
        slot=getIntent().getStringExtra("slot");
        item=getIntent().getStringExtra("item");
        id=getIntent().getStringExtra("id");

        tex1.setText("Name  :"+name);
        tex2.setText("Id    :"+id);
        tex3.setText("Time  :"+currentTime);
        tex4.setText("Item  :"+item);
        tex5.setText("Slot  :"+slot);


        submit12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref= FirebaseDatabase.getInstance().getReference("Lunch").child("Lunch").child(currentDate).child(id);
                BreakFastData bf=new BreakFastData(name,id,slot,item,currentTime);
                ref.setValue(bf);
                Intent intent=new Intent(Lunch_Submit.this,LunchHome.class);
                intent.putExtra("id",id);
                startActivity(intent);


            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Lunch_Submit.this,LunchHome.class);
        intent.putExtra("id",id);
        startActivity(intent);

        super.onBackPressed();
    }
}
