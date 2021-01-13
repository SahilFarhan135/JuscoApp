package com.example.tatatest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.DrawableWrapper;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
//import android.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

//import android.drawerlayout.widget.DrawerLayout;
//import androidx.drawerlayout.widget.DrawerLayout;


public class EmployeeHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String id1;
    DatabaseReference ref;
    TextView entry1,exit1,date1,status,nameView,idView,lt1,lt2,lt3,lt4;
    View header ;

    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    String currentTime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        id1 = getIntent().getStringExtra("id");

        //Toast.makeText(EmployeeHome.this,id1,Toast.LENGTH_SHORT).show();

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        entry1= findViewById(R.id.entry);
        exit1= findViewById(R.id.exit);
        date1= findViewById(R.id.date);
        status= findViewById(R.id.status);

        lt1= findViewById(R.id.Lt1);
        lt2= findViewById(R.id.Lt2);
        lt3= findViewById(R.id.Lt3);
        lt4= findViewById(R.id.Lt4);



        drawerLayout = findViewById(R.id.drawer_layout1);
        navigationView = findViewById(R.id.navigationView1);
        header=navigationView.getHeaderView(0);
        nameView=header.findViewById(R.id.idView) ;
        idView=header.findViewById(R.id.nameView) ;

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();
       // Toast.makeText(EmployeeHome.this, "working"+id1, Toast.LENGTH_SHORT).show();

        ref= FirebaseDatabase.getInstance().getReference("employee").child(id1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name=dataSnapshot.child("name").getValue().toString();
                    nameView.setText("Name : "+name);
                    idView.setText("Id  : "+id1);
                    //show_Notification("LogIn ","welcome "+name+" you login your account at "+ currentTime);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ref= FirebaseDatabase.getInstance().getReference("employee").child(id1).child("attendance").child(currentDate).child("entryTime");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String entry= dataSnapshot.getValue().toString();
                   // String exit=dataSnapshot.child("exit_time").getValue().toString();

                    entry1.setText(entry);
                    //exit1.setText(exit);
                    date1.setText(currentDate);

                }
                else{
                    entry1.setText("00:00:00");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref= FirebaseDatabase.getInstance().getReference("employee").child(id1).child("attendance").child(currentDate).child("exit_time");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                   // String entry= dataSnapshot.child("entryTime").getValue().toString();
                     String exit=dataSnapshot.getValue().toString();

                    //entry1.setText(entry);
                    exit1.setText(exit);


                }
                else{
                    exit1.setText("00:00:00");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref= FirebaseDatabase.getInstance().getReference("employee").child(id1).child("attendance").child(currentDate).child("present");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    status.setText("Present");
                }
                else{
                    status.setText("Absent");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref = FirebaseDatabase.getInstance().getReference("employee").child(id1).child("Visitor Appointment1").child(currentDate);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.child("name").exists()){
                    String name=dataSnapshot.child("name").getValue().toString();
                    lt1.setText("Name     : "+name);

                }
                else{
                    lt1.setText("Name    :         ");
                }
                if(dataSnapshot.child("purpose").exists()){
                    String name=dataSnapshot.child("purpose").getValue().toString();
                    lt2.setText("Purpose     : "+name);

                }
                else{
                    lt2.setText("Purpose    :         ");
                }
                if(dataSnapshot.child("date").exists()){
                    String name=dataSnapshot.child("date").getValue().toString();
                    lt3.setText("Date       : "+name);

                }
                else{
                    lt3.setText("Date    :         ");
                }
                if(dataSnapshot.child("phone").exists()){
                    String name=dataSnapshot.child("phone").getValue().toString();
                    lt4.setText("Phone    : "+name);

                }
                else{
                    lt4.setText("Phone    :         ");
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home: {
                break;
            }

            case R.id.attendance: {
                Intent intent = new Intent(EmployeeHome.this, AttendanceHome.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                break;
            }
            case R.id.appoinment: {
                Intent intent = new Intent(EmployeeHome.this, Appointment.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                break;
            }

            case R.id.lunch: {
                Intent intent = new Intent(EmployeeHome.this, LunchHome.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                break;
            }
            case R.id.send: {
                Intent intent = new Intent(this,About.class);
                startActivity(intent);
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return  true;
    }
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                EmployeeHome.this);

        // set title
        alertDialogBuilder.setTitle("Exit");

        // set dialog message
        alertDialogBuilder
                .setMessage("Do you really want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        Intent intent=new Intent(EmployeeHome.this,MainActivity.class);
                        startActivity(intent);
                        show_Notification("LogOut","You Log Out Your account at "+ currentTime);
                        //EmployeeHome.this.finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
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
