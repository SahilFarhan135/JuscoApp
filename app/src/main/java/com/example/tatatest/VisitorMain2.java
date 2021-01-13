package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class  VisitorMain2 extends AppCompatActivity {

    private EditText Name, No,email,purpose,date;
    private Button submit;
    String userId;
    long count;
    // private Spinner Select;
    DatabaseReference myRef;
    // Member member1;
    String[] employeeName = {"Gaurav-555", "kumar-12345", "Rahul-98765", "Mishra-0001"};
    ArrayAdapter<String> adapter;
    Spinner sp;
    String path;
    String currentTime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());
    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    String set,s1;

//    Resources resources=getResources();

    /*Spinner spinner=(Spinner)findViewById(R.id.spn);
    String size=spinner.getSelectedItem().toString();
    int spinner_pos=spinner.getSelectedItemPosition();
    String arr[]=getResources().getStringArray(R.array.employeeName);
    String str=(arr[spinner_pos]).toString();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_main2);

        Name = findViewById(R.id.EtVame);
        No = findViewById(R.id.EtNo);
        email = findViewById(R.id.EtEmail);
        purpose = findViewById(R.id.EtPurpose);
        sp = findViewById(R.id.spn);
        submit = findViewById(R.id.btSubmit);
        date = findViewById(R.id.etDate);
        setDate Date1 = new setDate(this, R.id.etDate);
        // spinner=(Spinner)findViewById(R.id.spn);
        userId=getIntent().getStringExtra("id");


        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, employeeName);
        sp.setAdapter(adapter);




            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                    switch (i) {
                        case 0: {

                            //Toast.makeText(VisitorMain2.this,employeeName[i],Toast.LENGTH_LONG).show();
                            path = employeeName[i].substring(7, 10);
                            //Toast.makeText(VisitorMain2.this, path, Toast.LENGTH_LONG).show();

                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String name = Name.getText().toString().trim();
                                    String Number = No.getText().toString().trim();
                                    String Email = email.getText().toString().trim();
                                    String Purpose = purpose.getText().toString().trim();
                                    String Date = date.getText().toString().trim();
                                    if (name.isEmpty() || Number.isEmpty() || Email.isEmpty() || Purpose.isEmpty() || Date.isEmpty()) {
                                        Toast.makeText(VisitorMain2.this, " Please Enter correct details", Toast.LENGTH_LONG).show();
                                    } else {


                                        myRef = FirebaseDatabase.getInstance().getReference("employee").child(path).child("Visitor Appointment");
                                        Member1 d1 = new Member1(name, Purpose, Email, Number, Date);
                                        myRef.child(name).setValue(d1);
                                        myRef = FirebaseDatabase.getInstance().getReference("Visitor").child(userId).child("appointment");
                                        Member1 d2 = new Member1(name, Purpose, Email, Number, Date);
                                        myRef.child(name).setValue(d2);
                                        //myRef.orderByValue();

                                        myRef =  FirebaseDatabase.getInstance().getReference("Visitor").child(userId).child("appointment").child(currentDate);
                                        Member1 d3 = new Member1(name, Purpose, Email, Number, Date);



                                        myRef.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                //Toast.makeText(MainDash.this, "hhh" ,Toast.LENGTH_LONG).show();

                                                if(dataSnapshot.exists()) {
                                                    //Toast.makeText(MainDash.this,"ggggx", Toast.LENGTH_LONG).show();
                                                    count = (dataSnapshot.getChildrenCount());
                                                    Toast.makeText(VisitorMain2.this, String.valueOf(count), Toast.LENGTH_LONG).show();



                                                }
                                                //  Toast.makeText(MainDash.this, count, Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                                    set="";
                                                        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                        myRef = FirebaseDatabase.getInstance().getReference("employee").child(path).child("Visitor Appointment1").child(currentDate);


                                                        Member1 d4 = new Member1(name, Purpose, Email, Number, Date);

                                                        if (count < 9) {
                                                            Toast.makeText(VisitorMain2.this, "hi", Toast.LENGTH_LONG).show();
                                                            s1=String.valueOf(count + 1);
                                                            set = "Visitor" + s1;
                                                            // Toast.makeText(MainDash.this, "9" + set, Toast.LENGTH_LONG).show();


                                                        } else {
                                                            set = "Visitor" + 9 + (count + 1);
                                                            Toast.makeText(VisitorMain2.this, "10" + set, Toast.LENGTH_LONG).show();

                                                        }
                                                        myRef.child(set).setValue(d4);









                                                        //Toast.makeText(VisitorMain2.this, String.valueOf(ServerValue.TIMESTAMP), Toast.LENGTH_LONG).show();


                                        Toast.makeText(VisitorMain2.this, "Your Value Stored Successful!!!", Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(VisitorMain2.this,Visitor_Home.class);
                                        intent.putExtra("id",userId);
                                        startActivity(intent);
                                        recreate();

                                    }
                                }
                            });
                            break;
                        }

                        case 1: {

                            //Toast.makeText(VisitorMain2.this,employeeName[i],Toast.LENGTH_LONG).show();
                            path = employeeName[i].substring(6, 11);
                            //Toast.makeText(VisitorMain2.this, path, Toast.LENGTH_LONG).show();

                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String name = Name.getText().toString().trim();
                                    String Number = No.getText().toString().trim();
                                    String Email = email.getText().toString().trim();
                                    String Purpose = purpose.getText().toString().trim();
                                    String Date = date.getText().toString().trim();

                                    if (name.isEmpty() || Number.isEmpty() || Email.isEmpty() || Purpose.isEmpty() || Date.isEmpty()) {
                                        Toast.makeText(VisitorMain2.this, " Please Enter correct details", Toast.LENGTH_LONG).show();
                                    } else {

                                        myRef = FirebaseDatabase.getInstance().getReference("employee").child(path).child("Visitor Appointment");
                                        Member1 d1 = new Member1(name, Purpose, Email, Number, Date);
                                        myRef.child(name).setValue(d1);
                                        myRef = FirebaseDatabase.getInstance().getReference("Visitor").child(userId).child("appointment");
                                        Member1 d2 = new Member1(name, Purpose, Email, Number, Date);
                                        myRef.child(name).setValue(d2);

                                        //Intent intent =new Intent(VisitorMain2.this,VisitorDisplay2.class);
                                        //startActivity(intent);

                                        myRef =  FirebaseDatabase.getInstance().getReference("Visitor").child(userId).child("appointment").child(currentDate);
                                        Member1 d3 = new Member1(name, Purpose, Email, Number, Date);



                                        myRef.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                //Toast.makeText(MainDash.this, "hhh" ,Toast.LENGTH_LONG).show();

                                                if(dataSnapshot.exists()) {
                                                    //Toast.makeText(MainDash.this,"ggggx", Toast.LENGTH_LONG).show();
                                                    count = (dataSnapshot.getChildrenCount());
                                                    Toast.makeText(VisitorMain2.this, String.valueOf(count), Toast.LENGTH_LONG).show();



                                                }
                                                //  Toast.makeText(MainDash.this, count, Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                        set="";
                                        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                        myRef = FirebaseDatabase.getInstance().getReference("employee").child(path).child("Visitor Appointment1").child(currentDate);


                                        Member1 d4 = new Member1(name, Purpose, Email, Number, Date);

                                        if (count < 9) {
                                            Toast.makeText(VisitorMain2.this, "hi", Toast.LENGTH_LONG).show();
                                            s1=String.valueOf(count + 1);
                                            set = "Visitor" + s1;
                                            // Toast.makeText(MainDash.this, "9" + set, Toast.LENGTH_LONG).show();


                                        } else {
                                            set = "Visitor" + 9 + (count + 1);
                                            Toast.makeText(VisitorMain2.this, "10" + set, Toast.LENGTH_LONG).show();

                                        }
                                        myRef.child(set).setValue(d4);


                                        Toast.makeText(VisitorMain2.this, "Your Value Stored Successful!!!", Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(VisitorMain2.this,Visitor_Home.class);
                                        intent.putExtra("id",userId);
                                        startActivity(intent);
                                        recreate();

                                    }
                                }
                            });
                            break;
                        }

                        case 2: {

                            //Toast.makeText(VisitorMain2.this,employeeName[i],Toast.LENGTH_LONG).show();
                            path = employeeName[i].substring(6, 11);
                            //Toast.makeText(VisitorMain2.this, path, Toast.LENGTH_LONG).show();

                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String name = Name.getText().toString().trim();
                                    String Number = No.getText().toString().trim();
                                    String Email = email.getText().toString().trim();
                                    String Purpose = purpose.getText().toString().trim();
                                    String Date = date.getText().toString().trim();

                                    if (name.isEmpty() || Number.isEmpty() || Email.isEmpty() || Purpose.isEmpty() || Date.isEmpty()) {
                                        Toast.makeText(VisitorMain2.this, " Please Enter correct details", Toast.LENGTH_LONG).show();
                                    } else {

                                        myRef = FirebaseDatabase.getInstance().getReference("employee").child(path).child("Visitor Appointment");
                                        Member1 d1 = new Member1(name, Purpose, Email, Number, Date);
                                        myRef.child(name).setValue(d1);
                                        Member1 d2 = new Member1(name, Purpose, Email, Number, Date);
                                        myRef.child(name).setValue(d2);

                                        //Intent intent =new Intent(VisitorMain2.this,VisitorDisplay2.class);
                                        //startActivity(intent);

                                        myRef =  FirebaseDatabase.getInstance().getReference("Visitor").child(userId).child("appointment").child(currentDate);
                                        Member1 d3 = new Member1(name, Purpose, Email, Number, Date);



                                        myRef.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                //Toast.makeText(MainDash.this, "hhh" ,Toast.LENGTH_LONG).show();

                                                if(dataSnapshot.exists()) {
                                                    //Toast.makeText(MainDash.this,"ggggx", Toast.LENGTH_LONG).show();
                                                    count = (dataSnapshot.getChildrenCount());
                                                    Toast.makeText(VisitorMain2.this, String.valueOf(count), Toast.LENGTH_LONG).show();



                                                }
                                                //  Toast.makeText(MainDash.this, count, Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                        set="";
                                        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                        myRef = FirebaseDatabase.getInstance().getReference("employee").child(path).child("Visitor Appointment1").child(currentDate);


                                        Member1 d4 = new Member1(name, Purpose, Email, Number, Date);

                                        if (count < 9) {
                                            Toast.makeText(VisitorMain2.this, "hi", Toast.LENGTH_LONG).show();
                                            s1=String.valueOf(count + 1);
                                            set = "Visitor" + s1;
                                            // Toast.makeText(MainDash.this, "9" + set, Toast.LENGTH_LONG).show();


                                        } else {
                                            set = "Visitor" + 9 + (count + 1);
                                            Toast.makeText(VisitorMain2.this, "10" + set, Toast.LENGTH_LONG).show();

                                        }
                                        myRef.child(set).setValue(d4);

                                        Toast.makeText(VisitorMain2.this, "Your Value Stored Successful!!!", Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(VisitorMain2.this,Visitor_Home.class);
                                        intent.putExtra("id",userId);
                                        startActivity(intent);
                                        recreate();

                                    }
                                }
                            });
                            break;
                        }

                        case 3: {

                            //Toast.makeText(VisitorMain2.this,employeeName[i],Toast.LENGTH_LONG).show();
                            path = employeeName[i].substring(7, 11);
                            //Toast.makeText(VisitorMain2.this, path, Toast.LENGTH_LONG).show();

                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String name = Name.getText().toString().trim();
                                    String Number = No.getText().toString().trim();
                                    String Email = email.getText().toString().trim();
                                    String Purpose = purpose.getText().toString().trim();
                                    String Date = date.getText().toString().trim();

                                    if (name.isEmpty() || Number.isEmpty() || Email.isEmpty() || Purpose.isEmpty() || Date.isEmpty()) {
                                        Toast.makeText(VisitorMain2.this, " Please Enter correct details", Toast.LENGTH_LONG).show();
                                    } else {

                                        myRef = FirebaseDatabase.getInstance().getReference("employee").child(path).child("Visitor Appointment");
                                        Member1 d1 = new Member1(name, Purpose, Email, Number, Date);
                                        myRef.child(name).setValue(d1);
                                        Member1 d2 = new Member1(name, Purpose, Email, Number, Date);
                                        myRef.child(name).setValue(d2);

                                        //Intent intent =new Intent(VisitorMain2.this,VisitorDisplay2.class);
                                        //startActivity(intent);

                                        myRef =  FirebaseDatabase.getInstance().getReference("Visitor").child(userId).child("appointment").child(currentDate);
                                        Member1 d3 = new Member1(name, Purpose, Email, Number, Date);



                                        myRef.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                //Toast.makeText(MainDash.this, "hhh" ,Toast.LENGTH_LONG).show();

                                                if(dataSnapshot.exists()) {
                                                    //Toast.makeText(MainDash.this,"ggggx", Toast.LENGTH_LONG).show();
                                                    count = (dataSnapshot.getChildrenCount());
                                                    Toast.makeText(VisitorMain2.this, String.valueOf(count), Toast.LENGTH_LONG).show();



                                                }
                                                //  Toast.makeText(MainDash.this, count, Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                        set="";
                                        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                        myRef = FirebaseDatabase.getInstance().getReference("employee").child(path).child("Visitor Appointment1").child(currentDate);


                                        Member1 d4 = new Member1(name, Purpose, Email, Number, Date);

                                        if (count < 9) {
                                            Toast.makeText(VisitorMain2.this, "hi", Toast.LENGTH_LONG).show();
                                            s1=String.valueOf(count + 1);
                                            set = "Visitor" + s1;
                                            // Toast.makeText(MainDash.this, "9" + set, Toast.LENGTH_LONG).show();


                                        } else {
                                            set = "Visitor" + 9 + (count + 1);
                                            Toast.makeText(VisitorMain2.this, "10" + set, Toast.LENGTH_LONG).show();

                                        }
                                        myRef.child(set).setValue(d4);

                                        Toast.makeText(VisitorMain2.this, "Your Value Stored Successful!!!", Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(VisitorMain2.this,Visitor_Home.class);
                                        intent.putExtra("id",userId);
                                        //show_Notification("New Appointment "," You have One New Appointment");
                                        startActivity(intent);
                                        recreate();

                                    }
                                }
                            });
                            break;
                        }


                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public void show_Notification(String title,String body){

        Intent intent=new Intent();
        String CHANNEL_ID="MYCHANNEL";
        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"name", NotificationManager.IMPORTANCE_LOW);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),1,intent,0);
        Notification notification=new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentText(body)
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.sym_action_chat,title,pendingIntent)
                .setChannelId(CHANNEL_ID)
                .setSmallIcon(android.R.drawable.sym_action_chat)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1,notification);


    }

    }
