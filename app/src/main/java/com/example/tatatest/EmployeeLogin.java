package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spark.submitbutton.SubmitButton;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class EmployeeLogin extends AppCompatActivity {

    private EditText EmployeeID, EPassword;
    private String dValue,dPass;
    String Eid;
    TelephonyManager tm,no,opt;
    String Imei,imei,no1,optname;
    public static int REQUEST_PERMISSION=1;
    private SubmitButton login,register;
    TextView forget;
     boolean flag=false;
    String currentTime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());


    Member member;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("employee");

    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    //  EmployeeS emp;



   /* public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    String ip=getLocalIpAddress();*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);


        member=new Member();
        forget= findViewById(R.id.forget);



        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!= PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},0);

        }
        else{
            TelephonyManager manager=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
            if(manager==null){
                Toast.makeText(this,"HardWare Not Supported",Toast.LENGTH_SHORT).show();
            }
            else{
                //signup.setEnabled(true);
                imei=manager.getImei();
                //no1=manager.getLine1Number();
                //optname=manager.getNetworkOperatorName();
               // Toast.makeText(this,imei,Toast.LENGTH_SHORT).show();
                // Toast.makeText(this,no1,Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,optname,Toast.LENGTH_SHORT).show();

            }
        }






        // emp = new EmployeeS();




        EmployeeID = findViewById(R.id.etVisitorName);
        EPassword = findViewById(R.id.etEpassword);
        login = findViewById(R.id.btELogin);
        register = findViewById(R.id.btESignup);

        //Eid=EmployeeID.getText().toString().trim();
        //String pass=EPassword.getText().toString().trim();








        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeSignup();
            }

            private void EmployeeSignup() {
                Intent intent = new Intent(EmployeeLogin.this, EmployeeSignup.class);
                startActivity(intent);
            }
        });









        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(EmployeeLogin.this, ""+EmployeeID.getText().toString().trim()+" "+EPassword.getText().toString().trim(), Toast.LENGTH_LONG).show();

                fun();


            }

        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EmployeeLogin.this,Forget_Email.class);
                startActivity(intent);
            }
        });


    }

    public void fun(){
        //Toast.makeText(EmployeeLogin.this, "working", Toast.LENGTH_SHORT).show();



              ref.orderByChild("id").equalTo(EmployeeID.getText().toString().trim())
                      .addListenerForSingleValueEvent(new ValueEventListener() {

                          @Override
                          public void onDataChange(DataSnapshot dataSnapshot) {
                              //Toast.makeText(EmployeeLogin.this, "working1", Toast.LENGTH_LONG).show();

                              if (dataSnapshot.exists()) {

                                  ref = FirebaseDatabase.getInstance().getReference("employee").child(EmployeeID.getText().toString().trim()).child("password");
                                  ref.addListenerForSingleValueEvent(new ValueEventListener() {


                                      @Override
                                      public void onDataChange(DataSnapshot dataSnapshot) {
                                          String pass = dataSnapshot.getValue(String.class);
                                          if (pass.equals(EPassword.getText().toString().trim())) {

                                              ref = FirebaseDatabase.getInstance().getReference("employee").child(EmployeeID.getText().toString().trim()).child("ipAddress");
                                              ref.addListenerForSingleValueEvent(new ValueEventListener() {

                                                  @Override
                                                  public void onDataChange(DataSnapshot dataSnapshot) {
                                                      String VIp = dataSnapshot.getValue(String.class);
                                                      if (VIp.equals(imei)) {

                                                          Intent intent = new Intent(EmployeeLogin.this, EmployeeHome.class);
                                                          intent.putExtra("id", EmployeeID.getText().toString().trim());

                                                          startActivity(intent);

                                                          show_Notification("LogIn ","welcome !!! you login your account at "+ currentTime);
                                                          EmployeeID.getText().clear();
                                                          EPassword.getText().clear();

                                                      } else {
                                                          Toast.makeText(EmployeeLogin.this, "Login from Your Own Phone!!!", Toast.LENGTH_SHORT).show();

                                                      }


                                                  }

                                                  @Override
                                                  public void onCancelled(@NonNull DatabaseError databaseError) {

                                                  }
                                              });
                                          } else {
                                              //Toast.makeText(EmployeeLogin.this, "" + ref.getRef(), Toast.LENGTH_LONG).show();

                                              Toast.makeText(EmployeeLogin.this, "your id and Password is Not correct", Toast.LENGTH_SHORT).show();


                                              //deleteCache(EmployeeLogin.this);
                                              //EmployeeID.getText().clear();
                                              //EPassword.getText().clear();
                                              //flag=true;
                                              recreate();


                                          }
                                      }

                                      @Override
                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                      }
                                  });
                              } else {
                                  //bus number doesn't exists.
                                  Toast.makeText(EmployeeLogin.this, "" + ref.getRef(), Toast.LENGTH_LONG).show();

                                  Toast.makeText(EmployeeLogin.this, "your id and Password is Not correct", Toast.LENGTH_SHORT).show();
                                  //deleteCache(EmployeeLogin.this);
                                  //EmployeeID.getText().clear();
                                  //EPassword.getText().clear();
                                 // flag=true;
                                  recreate();



                              }

                          }


                          @Override
                          public void onCancelled(@NonNull DatabaseError databaseError) {

                          }



                      });




    }
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public void show_Notification(String title,String body){

        Intent intent=new Intent(getApplicationContext(),EmployeeHome.class);
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


   /* public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
        }
    }

        public static boolean deleteDir(File dir) {
            if (dir != null && dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
            return dir.delete();
        }*/




}