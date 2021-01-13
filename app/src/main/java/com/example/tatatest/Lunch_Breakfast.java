package com.example.tatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Lunch_Breakfast extends AppCompatActivity {
    ListView list;
    ArrayList<String> arr=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String slot,item,id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch__breakfast);
        list= findViewById(R.id.bfView);
        arr.add("Puri");
        arr.add("Jalebi");
        arr.add("Phapra");
        arr.add("Singhara");
        arr.add("Sabzi");
        arr.add("Item 6");
        slot= getIntent().getStringExtra("slot1");
        id1=getIntent().getStringExtra("id");
        //Toast.makeText(Lunch_Breakfast.this, "value:"+id1 , Toast.LENGTH_SHORT).show();


        arrayAdapter=new ArrayAdapter<>(Lunch_Breakfast.this,android.R.layout.simple_list_item_1,arr);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] arr1 = arr.toArray(new String[arr.size()]);
                item=arr1[position];
                Intent intent=new Intent(Lunch_Breakfast.this,BreakFast_Display.class);
                intent.putExtra("slot",slot);
                intent.putExtra("item",item);
                intent.putExtra("id",id1);
                startActivity(intent);
                //Toast.makeText(Lunch_Breakfast.this, "value:" , Toast.LENGTH_SHORT).show();
                kill_activity();

            }
        });
       // Toast.makeText(Lunch_Breakfast.this, "value:hii" , Toast.LENGTH_SHORT).show();

    }
    void kill_activity()
    {
        finish();
    }
}
