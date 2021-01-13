package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Lunch extends AppCompatActivity {
    Button slot1,slot2,slot3,slot4;
    String id;
    DatabaseReference ref;
    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    long ChildValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        slot1= findViewById(R.id.slot1);
        slot2= findViewById(R.id.slot2);
        slot3= findViewById(R.id.slot3);
        slot4= findViewById(R.id.slot4);
        id=getIntent().getStringExtra("id");
        //Toast.makeText(Lunch.this,id,Toast.LENGTH_SHORT).show();



        slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref= FirebaseDatabase.getInstance().getReference("Lunch").child("Break Fast").child(currentDate).child("slot1");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            ChildValue=dataSnapshot.getChildrenCount();
                            if(ChildValue<=20){
                                Intent intent=new Intent(Lunch.this,Lunch_Breakfast.class);
                                intent.putExtra("slot1","slot1");
                                intent.putExtra("id",id);
                                startActivity(intent);

                            }
                            else{
                                slot1.setEnabled(false);
                                //slot1.setBackgroundColor(getResources().getColor(R.color.gray1));
                                slot1.setBackgroundResource(R.drawable.roundbutton3);


                            }

                        }
                        else{
                            slot1.setEnabled(true);
                            Intent intent=new Intent(Lunch.this,Lunch_Breakfast.class);
                            intent.putExtra("slot1","slot1");
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref= FirebaseDatabase.getInstance().getReference("Lunch").child("Break Fast").child(currentDate).child("slot1");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            ChildValue=dataSnapshot.getChildrenCount();
                            if(ChildValue<=20){
                                Intent intent=new Intent(Lunch.this,Lunch_Breakfast.class);
                                intent.putExtra("slot1","slot2");
                                intent.putExtra("id",id);
                                startActivity(intent);

                            }
                            else{
                                slot2.setEnabled(false);
                                slot2.setBackgroundColor(getResources().getColor(R.color.gray1));
                            }

                        }
                        else{
                            slot1.setEnabled(true);
                            Intent intent=new Intent(Lunch.this,Lunch_Breakfast.class);
                            intent.putExtra("slot1","slot2");
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        slot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref= FirebaseDatabase.getInstance().getReference("Lunch").child("Break Fast").child(currentDate).child("slot1");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            ChildValue=dataSnapshot.getChildrenCount();
                            if(ChildValue<=20){
                                Intent intent=new Intent(Lunch.this,Lunch_Breakfast.class);
                                intent.putExtra("slot1","slot3");
                                intent.putExtra("id",id);
                                startActivity(intent);

                            }
                            else{
                                slot3.setEnabled(false);
                                slot3.setBackgroundColor(getResources().getColor(R.color.gray1));
                            }

                        }
                        else{
                            slot3.setEnabled(true);
                            Intent intent=new Intent(Lunch.this,Lunch_Breakfast.class);
                            intent.putExtra("slot1","slot3");
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        slot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref= FirebaseDatabase.getInstance().getReference("Lunch").child("Break Fast").child(currentDate).child("slot1");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            ChildValue=dataSnapshot.getChildrenCount();
                            if(ChildValue<=20){
                                Intent intent=new Intent(Lunch.this,Lunch_Breakfast.class);
                                intent.putExtra("slot1","slot4");
                                intent.putExtra("id",id);
                                startActivity(intent);

                            }
                            else{
                                slot4.setEnabled(false);
                                slot4.setBackgroundColor(getResources().getColor(R.color.gray1));
                            }

                        }
                        else{
                            slot4.setEnabled(true);
                            Intent intent=new Intent(Lunch.this,Lunch_Breakfast.class);
                            intent.putExtra("slot1","slot4");
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        /*slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref= FirebaseDatabase.getInstance().getReference("Lunch").child("Break Fast").child(currentDate).child("slot1");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            ChildValue=dataSnapshot.getChildrenCount();
                            if(ChildValue<=20){
                                Intent intent=new Intent(Lunch.this,Lunch_Breakfast.class);
                                intent.putExtra("slot1","slot1");
                                intent.putExtra("id",id);
                                startActivity(intent);

                            }
                            else{
                                slot1.setEnabled(false);
                            }

                        }
                        else{
                            slot1.setEnabled(true);
                            Intent intent=new Intent(Lunch.this,Lunch_Breakfast.class);
                            intent.putExtra("slot1","slot1");
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });*/
    }
}
