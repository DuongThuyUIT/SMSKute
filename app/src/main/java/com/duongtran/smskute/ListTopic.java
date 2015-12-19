package com.duongtran.smskute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ListTopic extends AppCompatActivity {

    Button btnValentine  = null;
    Button btnFavorite  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topic);

        btnValentine=(Button) findViewById(R.id.btn_Valentine);
        btnValentine.setOnClickListener(new eventTopic());

        btnFavorite=(Button) findViewById(R.id.btn_Favorite);
        btnFavorite.setOnClickListener(new eventTopic());
    }

    private class eventTopic implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent myIntent=new Intent(ListTopic.this, DetailSMS.class);
            Bundle bundle=new Bundle();
            // TODO Auto-generated method stub
            if(v.getId()==R.id.btn_Valentine)
            {
                String topic = "VLT";
                bundle.putString("topic", topic);
                myIntent.putExtra("MyTopic", bundle);
                startActivity(myIntent);
            }
            else if(v.getId()==R.id.btn_Favorite)
            {
                String topic = "Favorite";
                bundle.putString("topic", topic);
                myIntent.putExtra("MyTopic", bundle);
                startActivity(myIntent);
            }

//            else if(v.getId()==R.id.buttonInsertBook)
//            {
//                Intent intent=new Intent(MainActivity.this, InsertBookActivity.class);
//                startActivity(intent);
//            }
        }

    }
}
