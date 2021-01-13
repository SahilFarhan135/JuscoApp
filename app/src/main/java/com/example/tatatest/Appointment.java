package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.tatatest.R.layout.activity_appointment;
import static com.example.tatatest.R.layout.popup;

public class Appointment extends AppCompatActivity {

    String id1;
    DatabaseReference mref;
    ListView list;
    Dialog mydialog;
     ArrayList<String> arr=new ArrayList<>();
    //String arr1[]={"123","234","4567","9876"};
    ArrayAdapter arrayAdapter;
    private Button Appoint;
    Button Accept,Reject;
    TextView data,status,data1,data2,data3;
    DatabaseReference reff;
    ShowData member;
    int i=0;
    ArrayList<String> pos=new ArrayList<>();

    String[] employeeName = {"select time ", "9-10 am", "10-11 am", "11-12 am", "12-01 pm", "01-02 pm", "02-03 pm", "03-04 pm", "04-05 pm"};
    ArrayAdapter<String> adapter;
    Spinner sp;
    String spnn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_appointment);
        list = findViewById(R.id.List_View33);
        //arr=new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);





        id1 = getIntent().getStringExtra("id");
        //Toast.makeText(Appointment.this, id, Toast.LENGTH_SHORT).show();

        mref = FirebaseDatabase.getInstance().getReference("employee").child(id1).child("Visitor Appointment");
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
                    mref = FirebaseDatabase.getInstance().getReference("employee").child(id1).child("pos");
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String[] arr1 = arr.toArray(new String[arr.size()]);
                            String path1=arr1[position];
                            //parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.gray1));
                            //Toast.makeText(Appointment.this, "value:"+String.valueOf(position) , Toast.LENGTH_SHORT).show();
                            //mref.child(String.valueOf(position)).setValue(position);


                            //pos.add(String.valueOf(position));
                            //Toast.makeText(Appointment.this,"good",Toast.LENGTH_LONG).show();
                           // mref = FirebaseDatabase.getInstance().getReference("employee").child(id1).child("pos");
                           /* mref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()) {
                                        for(DataSnapshot dt:dataSnapshot.getChildren()) {
                                            pos.add(dt.getValue().toString());

                                        }

                                    }

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            for(int i=0;i<pos.size();i++){
                                int val=Integer.parseInt(pos.get(i));
                                parent.getChildAt(val).setBackgroundColor(getResources().getColor(R.color.gray1));

                            }*/

                            MyAlertdialog(path1);
                            //Toast.makeText(Appointment.this, "value:" + path1, Toast.LENGTH_SHORT).show();

                            //Toast.makeText(Appointment.this, "value:"+pos , Toast.LENGTH_SHORT).show();

                        }

                    });
                   // Toast.makeText(Appointment.this, "value:"+pos , Toast.LENGTH_SHORT).show();



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Toast.makeText(Appointment.this, "value:" + arr, Toast.LENGTH_SHORT).show();




    }


        public void MyAlertdialog (final String  path) {


        mydialog = new Dialog(Appointment.this);

        mydialog.setContentView(R.layout.popup);
            //setContentView(popup);;
            View mview=getLayoutInflater().inflate(R.layout.popup,null);

            sp= mview.findViewById(R.id.spn);

            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, employeeName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(adapter);
            mydialog.setContentView(mview);
        data = mydialog.findViewById(R.id.TvpopupPur);
            data1 = mydialog.findViewById(R.id.TvpopupPur1);
            data2 = mydialog.findViewById(R.id.TvpopupPur2);
            data3 = mydialog.findViewById(R.id.TvpopupPur3);
        mref = FirebaseDatabase.getInstance().getReference("employee").child(id1).child("Visitor Appointment").child(path);




        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String ds = dataSnapshot.getValue().toString();
                    String name=dataSnapshot.child("name").getValue().toString();
                    String purpose=dataSnapshot.child("purpose").getValue().toString();
                    String date=dataSnapshot.child("date").getValue().toString();
                    String phone=dataSnapshot.child("phone").getValue().toString();
                    data.setText("Name  :"+name);
                    data1.setText("Phone  :"+phone);
                    data2.setMovementMethod(new ScrollingMovementMethod());
                    data2.setText("Purpose  :"+purpose);
                    data3.setText("Date  :"+date);

                    sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if(position==0) {
                                Toast.makeText(Appointment.this, "Please select time", Toast.LENGTH_SHORT).show();
                                Accept.setEnabled(false);

                            }
                            if(position>0) {
                                spnn = employeeName[position];
                                Accept.setEnabled(true);
                            }



                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
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


        Accept = mydialog.findViewById(R.id.btD1);
        Reject = mydialog.findViewById(R.id.btD2);
        //member.setStatus(Set1);
        //member.setStatus(Set2);

        Accept.setEnabled(true);
        Reject.setEnabled(true);
        // reff=FirebaseDatabase.getInstance().getReference("employee").child("777");


            Accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mref = FirebaseDatabase.getInstance().getReference("employee").child(id1).child("Visitor Appointment").child(path);
                    mref.child("Status").setValue("Accepted");
                    mref.child("time").setValue(spnn);



                    Toast.makeText(getApplicationContext(), " accepted !!", Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(), " accepted !!"+spnn, Toast.LENGTH_LONG).show();
                    sendNotification("Appointment","you Accepted an appointment");


                }
            });

            Reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mref = FirebaseDatabase.getInstance().getReference("employee").child(id1).child("Visitor Appointment").child(path);
                    mref.child("Status").setValue("Rejected");

                    Toast.makeText(getApplicationContext(), "rejected !!!", Toast.LENGTH_LONG).show();
                    sendNotification("Appointment","you reject an appointment");

                    mydialog.cancel();
                }
            });

    }
            
    void display(ArrayList<String> str){
        ArrayAdapter arrayAdapter=new ArrayAdapter(Appointment.this,android.R.layout.simple_list_item_1,str);
        list.setAdapter(arrayAdapter);


    }


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    private void sendNotification(String title, String body) {
        Intent intent=new Intent(getApplicationContext(),Appointment.class);
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
    /*void back(final AdapterView<?> parent,int val){
        Toast.makeText(Appointment.this,"good",Toast.LENGTH_LONG).show();
       // parent.getChildAt(val).setBackgroundColor(getResources().getColor(R.color.gray1));
        mref = FirebaseDatabase.getInstance().getReference("employee").child(id1).child("pos");
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot dt:dataSnapshot.getChildren()) {
                        String val=dt.getValue().toString();
                        int val1=Integer.parseInt(val);
                        Toast.makeText(Appointment.this,"good"+val1,Toast.LENGTH_LONG).show();

                        parent.getChildAt(val1).setBackgroundColor(getResources().getColor(R.color.gray1));
                    }

                }

                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/


}
