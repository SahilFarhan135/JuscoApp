package com.example.tatatest;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.WriterException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.BitSet;

public class LunchDisplay2 extends AppCompatActivity {

    TextView tName,tId,tSlot,tTime,tPaid,tItem;
    ImageView qr;
    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    String currentTime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());
    String id1;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_display2);
        tName= findViewById(R.id.lName);
        tId= findViewById(R.id.lId);
        tSlot= findViewById(R.id.lSlot);
        tTime= findViewById(R.id.lTime);
        tName= findViewById(R.id.lName);
        tItem= findViewById(R.id.lItem);
        tPaid= findViewById(R.id.lpaid);
        qr= findViewById(R.id.qr);

        id1=getIntent().getStringExtra("id");

        ref= FirebaseDatabase.getInstance().getReference("Lunch").child("Lunch").child(currentDate).child(id1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name=dataSnapshot.child("name").getValue().toString();
                    String id=dataSnapshot.child("id").getValue().toString();
                    String item=dataSnapshot.child("item").getValue().toString();
                    String slot=dataSnapshot.child("slot").getValue().toString();
                    String time=dataSnapshot.child("time").getValue().toString();

                    tName.setText("Name   : "+name);
                    tId.setText("Id  : "+id);
                    tItem.setText("Item   : "+item);
                    tSlot.setText("Slot  : "+slot);
                    tTime.setText("Time   : "+time);

                    QRGEncoder qrgEncoder = new QRGEncoder("Name="+name+" "+"Id="+id+" "+"Item="+item+" "+"Slot="+slot+" "+"Time="+time, null, QRGContents.Type.TEXT, 2500);
                    // Getting QR-Code as Bitmap
                    Bitmap bitmap = qrgEncoder.getBitmap();
                    // Setting Bitmap to ImageView
                    qr.setImageBitmap(bitmap);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
