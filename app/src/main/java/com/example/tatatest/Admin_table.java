package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class Admin_table extends AppCompatActivity {
   ListView list,list1,list2,list3,list4;
    String id;
    DatabaseReference mref;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arr=new ArrayList<>();
    Dialog mydialog;
    TextView entry,exit,status,date1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_table);

        list = findViewById(R.id.AId);
        //arr=new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);


        mref = FirebaseDatabase.getInstance().getReference("employee");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
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
                            //Toast.makeText(Admin_table.this, "value:" + path1, Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(Admin_table.this,Admin_table3.class);
                            intent.putExtra("id",path1);
                            startActivity(intent);
                            //MyAlertdialog(path1);
                            //Toast.makeText(TopNav_Map.this, "value:" + path1, Toast.LENGTH_SHORT).show();

                        }
                    });

                }
                else{

                        Toast.makeText(Admin_table.this,"Not Exits",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





   /* public void MyAlertdialog (final String  path) {


        mydialog = new Dialog(Admin_table.this);
        mydialog.setContentView(R.layout.popup1);

        entry = (TextView) mydialog.findViewById(R.id.entryPop);
        exit = (TextView) mydialog.findViewById(R.id.exitPop);
        status = (TextView) mydialog.findViewById(R.id.DatePop);
        date1 = (TextView) mydialog.findViewById(R.id.StatusPop);
        mref = FirebaseDatabase.getInstance().getReference("employee").child("555").child("attendance").child(path);




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

    }*/
    void display(ArrayList<String> str){
        ArrayAdapter arrayAdapter=new ArrayAdapter(Admin_table.this,android.R.layout.simple_list_item_1,str);
        list.setAdapter(arrayAdapter);
    }
}
