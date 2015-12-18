package com.duongtran.smskute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ListTopic extends AppCompatActivity {

    Button btnRomantic  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topic);

        btnRomantic=(Button) findViewById(R.id.btn_romantic);
        btnRomantic.setOnClickListener(new eventTopic());
    }

    private class eventTopic implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(v.getId()==R.id.btn_romantic)
            {
                Intent myIntent=new Intent(ListTopic.this, DetailSMS.class);
                startActivity(myIntent);
            }
//            else if(v.getId()==R.id.buttonShowAuthorList)
//            {
//                showAuthorList1();
//            }
//
//            else if(v.getId()==R.id.buttonInsertBook)
//            {
//                Intent intent=new Intent(MainActivity.this, InsertBookActivity.class);
//                startActivity(intent);
//            }
        }

    }
}
