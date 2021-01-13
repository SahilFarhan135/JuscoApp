package com.example.tatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {
     TextView Atv;
     String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
         Atv= findViewById(R.id.atv);

         info="Created by sahil & yasir & ashraf";
         Atv.setText(info);
    }
}
