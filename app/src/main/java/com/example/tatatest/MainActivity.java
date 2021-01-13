package com.example.tatatest;


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
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private Button employee,visitor,admin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    admin = findViewById(R.id.btADMIN);
    employee = findViewById(R.id.btEmployee);
    visitor = findViewById(R.id.btVisitor);

    //show_Notification("Jusco","Welcome in Jusco");
    employee.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        openEmployeeLogin();
      }

      private void openEmployeeLogin() {
        Intent intent = new Intent(MainActivity.this, EmployeeLogin.class);
        startActivity(intent);
      }
    });
    visitor.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        moveToVistorLogin();
      }

      private void moveToVistorLogin() {
        Intent intent = new Intent(MainActivity.this,VisitorLogin.class);
        startActivity(intent);

      }
    });

    admin.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,Admin_table.class);
        startActivity(intent);

      }
  });


  }

  @TargetApi(Build.VERSION_CODES.O)
  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

  public void show_Notification(String title,String body){

    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
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


  @Override
  public void onBackPressed(){
    Intent a = new Intent(Intent.ACTION_MAIN);
    a.addCategory(Intent.CATEGORY_HOME);
    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(a);
  }
}