package com.example.tatatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VisitorDisplay2 extends AppCompatActivity {

    ListView list;
    ArrayList<String> arr=new ArrayList<>();
    String id1;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_display2);
        list= findViewById(R.id.Vlist);
        id1=getIntent().getStringExtra("id");

        ref = FirebaseDatabase.getInstance().getReference("Visitor").child(id1).child("appointment");
        ref.addValueEventListener(new ValueEventListener() {
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
                            Intent intent=new Intent(VisitorDisplay2.this,Visitor_display.class);
                            intent.putExtra("id",id1);
                            intent.putExtra("Userid",path1);
                            startActivity(intent);

                            //Toast.makeText(VisitorDisplay2.this, "value:" + path1, Toast.LENGTH_SHORT).show();

                        }
                    });

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void display(ArrayList<String> str){
        ArrayAdapter arrayAdapter=new ArrayAdapter(VisitorDisplay2.this,android.R.layout.simple_list_item_1,str);
        list.setAdapter(arrayAdapter);
    }
}
