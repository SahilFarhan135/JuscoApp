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
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Visitor_display extends AppCompatActivity {
    TextView tName,tEmail,tPhone,tDate,tPurpose,tStatus,tTime;
    String Userid,id;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_display);
        Userid=getIntent().getStringExtra("Userid");
        id=getIntent().getStringExtra("id");
        tName= findViewById(R.id.vName);
        tEmail= findViewById(R.id.vEmail);
        tPhone= findViewById(R.id.vPhone);
        tDate= findViewById(R.id.vDate);
        tPurpose= findViewById(R.id.vPurpose);
        tStatus= findViewById(R.id.vStatus);
        tTime= findViewById(R.id.vTime);

        //Toast.makeText(Visitor_display.this,"val"+id,Toast.LENGTH_SHORT).show();

        ref = FirebaseDatabase.getInstance().getReference("Visitor").child(id).child("appointment").child(Userid);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name=dataSnapshot.child("name").getValue().toString();
                    String email=dataSnapshot.child("email").getValue().toString();
                    String phone=dataSnapshot.child("phone").getValue().toString();
                    String purpose=dataSnapshot.child("purpose").getValue().toString();
                    final String date=dataSnapshot.child("date").getValue().toString();
                    //String time=dataSnapshot.child("time").getValue().toString();


                    //Toast.makeText(Visitor_display.this,"val"+name,Toast.LENGTH_SHORT).show();
                    ref = FirebaseDatabase.getInstance().getReference("employee").child("555").child("Visitor Appointment").child(name).child("Status");

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String value = dataSnapshot.getValue().toString();
                                //String time = dataSnapshot.child("time").getValue().toString();
                                if (value.equals("Accepted")) {
                                    tStatus.setText("Status   :  Accepted:)");
                                    sendNotification("Appointment","Your appointment has been accepted");
                                }
                                if (value.equals("Rejected"))
                                    tStatus.setText("Status   :  Rejected.");
                                sendNotification("Appointment","Your appointment has been Rejected");
                            }
                                else {
                                    tStatus.setText("Status   :  Pending...");
                                }

                            }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    ref = FirebaseDatabase.getInstance().getReference("employee").child("555").child("Visitor Appointment").child(name).child("time");

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                //String value = dataSnapshot.child("Status").getValue().toString();
                                String time = dataSnapshot.getValue().toString();
                                tTime.setText("Time     :"+time  );
                            }
                            else {
                                //tStatus.setText("Status   :  Pending...");
                                tTime.setText("Time     :--:--:--"  );
                            }

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    tName.setText("Name     :"+name);
                    tEmail.setText("Email   :"+email);
                    tPhone.setText("Phone    :"+phone);
                    tPurpose.setText("Purpose  :"+purpose);
                    tDate.setText("Date     :"+date);


                    //Toast.makeText(Visitor_display.this,"val"+name,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    private void sendNotification(String title, String body) {
        Intent intent=new Intent(getApplicationContext(),Visitor_display.class);
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
