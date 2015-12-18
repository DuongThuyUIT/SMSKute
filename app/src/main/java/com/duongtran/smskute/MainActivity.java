package com.duongtran.smskute;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    ImageButton btnSMS = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultTopicsIfNeed();
        db.createDefaultSMSIfNeed();

        btnSMS=(ImageButton) findViewById(R.id.btnSMS);
        btnSMS.setOnClickListener(new MyEvent());

    }

    private class MyEvent implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(v.getId()==R.id.btnSMS)
            {
                Intent myIntent=new Intent(MainActivity.this, ListTopic.class);
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
