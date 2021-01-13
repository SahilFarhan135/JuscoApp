package com.example.tatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Logo extends AppCompatActivity {
    private static int SPLASH_SCREEN=5000;
    Animation topAnim,bottomAnim,midd;
    ImageView logo,cropPic1,cropPic2,cropPic3;
    TextView Pow,pow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_logo);
        topAnim= AnimationUtils.loadAnimation(this, R.anim.downtoup);
        bottomAnim= AnimationUtils.loadAnimation(this, R.anim.uptodown);
        midd= AnimationUtils.loadAnimation(this, R.anim.myanimation);


        logo= findViewById(R.id.Imlogo);

        cropPic1= findViewById(R.id.ImC1);

        //logo=(ImageView)findViewById(R.id.I);
        Pow= findViewById(R.id.Tvpow);
       // pow2= findViewById(R.id.Tvpow1);


        cropPic1.setAnimation(bottomAnim);
        logo.setAnimation(midd);
        Pow.setAnimation(topAnim);
        //pow2.setAnimation(topAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Logo.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}
