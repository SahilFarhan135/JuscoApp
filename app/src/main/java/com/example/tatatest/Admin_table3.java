package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_table3 extends AppCompatActivity {
    ListView list,list1,list2,list3;
    String id;
    DatabaseReference mref;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arr=new ArrayList<>();
    ArrayList<String> entry1=new ArrayList<>();
    ArrayList<String> exit1=new ArrayList<>();
    ArrayList<String> status1=new ArrayList<>();
    ArrayList<String> merge=new ArrayList<>();
    Dialog mydialog;
    TextView VID,VName;
    int index=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_table3);
        VID= findViewById(R.id.TVId);
        VName= findViewById(R.id.TVName);
        list= findViewById(R.id.tvList);

        id=getIntent().getStringExtra("id");
        VID.setText("Id   :"+id);





            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);


        mref = FirebaseDatabase.getInstance().getReference("employee").child(id).child("attendance");
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
                        //display(arr);
                        String[] arr1 = arr.toArray(new String[arr.size()]);

                        for(int i=0;i<arr1.length;i++) {

                            mref = FirebaseDatabase.getInstance().getReference("employee").child(id).child("attendance").child(arr1[i]);
                            mref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()){
                                        String name=dataSnapshot.child("entryTime").getValue().toString();
                                        String present=dataSnapshot.child("present").getValue().toString();
                                        //String exit_time=dataSnapshot.child("exit_time").getValue().toString();
                                        entry1.add(name);
                                        //exit1.add(exit_time);
                                        status1.add(present);
                                    }

                                    if(dataSnapshot.child("exit_time").exists()){
                                        String exit_time=dataSnapshot.child("exit_time").getValue().toString();
                                        exit1.add(exit_time);
                                    }

                                    TableLayout table= findViewById(R.id.ATable);
                                    String[] entry12 = entry1.toArray(new String[arr.size()]);
                                    String[] exit12 = exit1.toArray(new String[arr.size()]);
                                    String[] status12 = status1.toArray(new String[arr.size()]);


                                        merge.add(arr.get(index)+"        "+entry12[index]+"        "+exit12[index]+"        "+status12[index]);


                                    //Toast.makeText(Admin_table3.this, "Value" + merge, Toast.LENGTH_SHORT).show();
                                    display(merge);
                                    index++;





                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                        //Toast.makeText(Admin_table3.this, "Value" + entry1, Toast.LENGTH_SHORT).show();

                        //TableLayout.LayoutParams lp=new TableLayout.LayoutParams((ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));



                    }
                    else{

                        Toast.makeText(Admin_table3.this,"Not Exits",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        mref = FirebaseDatabase.getInstance().getReference("employee").child(id).child("name");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String Name = dataSnapshot.getValue().toString();

                    VName.setText("Name  :"+Name);
                    //Toast.makeText(getApplicationContext(), "v" + Name, Toast.LENGTH_LONG).show();
                }
                else{
                    VName.setText("Employee Name");

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








    }


        void display(ArrayList<String> str){
            ArrayAdapter arrayAdapter=new ArrayAdapter(Admin_table3.this,android.R.layout.simple_list_item_1,str);
            list.setAdapter(arrayAdapter);
        }

   /* void display1(ArrayList<String> str){
        ArrayAdapter arrayAdapter=new ArrayAdapter(Admin_table3.this,android.R.layout.simple_list_item_1,str);
        list1.setAdapter(arrayAdapter);
    }
    void display2(ArrayList<String> str){
        ArrayAdapter arrayAdapter=new ArrayAdapter(Admin_table3.this,android.R.layout.simple_list_item_1,str);
        list2.setAdapter(arrayAdapter);
    }
    void display3(ArrayList<String> str){
        ArrayAdapter arrayAdapter=new ArrayAdapter(Admin_table3.this,android.R.layout.simple_list_item_1,str);
        list3.setAdapter(arrayAdapter);
    }*/
    }
