package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TopNav_Map extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    String id;
    DatabaseReference mref;
    ListView list;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arr=new ArrayList<>();
    Dialog mydialog;
    TextView entry,exit,status,date1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_nav__map);
        //bottomNavigationView=findViewById(R.id.top_nav);

        /*bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.make:{
                        //Toast.makeText(TopNav_Map.this,"working",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(TopNav_Map.this,MapsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.view:{
                        //Toast.makeText(TopNav_Map.this,"working1",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });*/



        list = findViewById(R.id.List_View32);
        //arr=new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);



        id = getIntent().getStringExtra("id");
        //Toast.makeText(TopNav_Map.this, id, Toast.LENGTH_SHORT).show();

        mref = FirebaseDatabase.getInstance().getReference("employee").child(id).child("attendance");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) ;
                {
                    //Toast.makeText(Appointment.this, "value:" + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                    for (DataSnapshot dt : dataSnapshot.getChildren()) {
                        String val = dt.getKey();
                        arr.add(val);


                    }
                    display(arr);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String[] arr1 = arr.toArray(new String[arr.size()]);
                            String path1=arr1[position];
                            MyAlertdialog(path1);
                            //Toast.makeText(TopNav_Map.this, "value:" + path1, Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





    public void MyAlertdialog (final String  path) {


        mydialog = new Dialog(TopNav_Map.this);
        mydialog.setContentView(R.layout.popup1);

        entry = mydialog.findViewById(R.id.entryPop);
        exit = mydialog.findViewById(R.id.exitPop);
        status = mydialog.findViewById(R.id.DatePop);
        date1 = mydialog.findViewById(R.id.StatusPop);
        mref = FirebaseDatabase.getInstance().getReference("employee").child(id).child("attendance").child(path);




        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String ds = dataSnapshot.getValue().toString();
                    String name=dataSnapshot.child("entryTime").getValue().toString();
                    String date=dataSnapshot.child("currentDate").getValue().toString();
                    String phone=dataSnapshot.child("present").getValue().toString();
                    entry.setText("Entry Time  :"+name);

                    //data2.setMovementMethod(new ScrollingMovementMethod());
                    status.setText("Date  :"+date);
                    date1.setText("Status  :"+phone);
                    if(dataSnapshot.child("exit_time").exists()){
                        String purpose=dataSnapshot.child("exit_time").getValue().toString();
                        exit.setText("Exit Time  :"+purpose);
                    }
                    else{
                        exit.setText("Exit Time  :00:00:00");

                    }
                    int size = (int) dataSnapshot.getChildrenCount();
                    //Toast.makeText(Appointment.this, "value:" + size, Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mydialog.setTitle("New Appointment");
        //Toast.makeText(getApplicationContext(), "opened popoup", Toast.LENGTH_LONG).show();

        mydialog.show();

    }
    void display(ArrayList<String> str){
        ArrayAdapter arrayAdapter=new ArrayAdapter(TopNav_Map.this,android.R.layout.simple_list_item_1,str);
        list.setAdapter(arrayAdapter);
    }
    public void onBackPressed() {
        Intent intent=new Intent(TopNav_Map.this,AttendanceHome.class);
        intent.putExtra("id",id);
        startActivity(intent);

        super.onBackPressed();
    }
}
