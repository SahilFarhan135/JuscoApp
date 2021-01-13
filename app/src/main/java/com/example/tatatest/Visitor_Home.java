package com.example.tatatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class Visitor_Home extends AppCompatActivity {
    GridLayout grid;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor__home);
        grid= findViewById(R.id.grid);

        setSingleEvent(grid);
        userId=getIntent().getStringExtra("id");
    }

    private void setSingleEvent(GridLayout mGrid){
        for(int i=0;i<mGrid.getChildCount();i++){
            final CardView cardView=(CardView)mGrid.getChildAt(i);
            final int final1=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cardView.getCardBackgroundColor().getDefaultColor()==-1){
                        cardView.setCardBackgroundColor(Color.parseColor("#41CEFC"));
                        //Toast.makeText(Visitor_Home.this,""+final1,Toast.LENGTH_LONG).show();

                    }
                    else{
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    if(final1==1){
                        Intent intent=new Intent(Visitor_Home.this,VisitorMain2.class);
                        intent.putExtra("id",userId);
                        startActivity(intent);
                    }

                    if(final1==3){
                        Intent intent=new Intent(Visitor_Home.this,VisitorDisplay2.class);
                        intent.putExtra("id",userId);
                        startActivity(intent);
                    }

                }
            });
        }
    }
    @Override
    public void onBackPressed(){
        Intent a = new Intent(Visitor_Home.this,MainActivity.class);
        startActivity(a);
    }
}
