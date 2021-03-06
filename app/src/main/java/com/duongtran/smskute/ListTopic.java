package com.duongtran.smskute;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ListTopic extends AppCompatActivity {

    Button btnValentine  = null;
    Button btnFavorite  = null;
    ImageButton imgbHome = null;

    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topic);

        //set grid view item
        Bitmap happy = BitmapFactory.decodeResource(this.getResources(), R.drawable.happy);
        Bitmap valentine = BitmapFactory.decodeResource(this.getResources(), R.drawable.valentine);
        Bitmap christmas = BitmapFactory.decodeResource(this.getResources(), R.drawable.christmas);
        Bitmap _20_10 = BitmapFactory.decodeResource(this.getResources(), R.drawable._20_10);
        Bitmap _08_03 = BitmapFactory.decodeResource(this.getResources(), R.drawable._08_03);
        Bitmap night = BitmapFactory.decodeResource(this.getResources(), R.drawable.night);
        Bitmap goodLuck = BitmapFactory.decodeResource(this.getResources(), R.drawable.goodluck_1);
        Bitmap favorite = BitmapFactory.decodeResource(this.getResources(), R.drawable.favorite);
        Bitmap greeting = BitmapFactory.decodeResource(this.getResources(), R.drawable.greeting);
        Bitmap newYear = BitmapFactory.decodeResource(this.getResources(), R.drawable.newyear_1);
        Bitmap birthday = BitmapFactory.decodeResource(this.getResources(), R.drawable.birthday);
        Bitmap love = BitmapFactory.decodeResource(this.getResources(), R.drawable.love);

        gridArray.add(new Item(valentine,"Valentine"));
        gridArray.add(new Item(christmas,"Christmas"));
        gridArray.add(new Item(_20_10,"20-10"));
        gridArray.add(new Item(_08_03,"08-03"));
        gridArray.add(new Item(night,"Good Night"));
        gridArray.add(new Item(love,"Love"));
        gridArray.add(new Item(goodLuck,"Good Luck"));
        gridArray.add(new Item(happy,"Happy"));
        gridArray.add(new Item(favorite,"Favorite"));
        gridArray.add(new Item(greeting,"Greeting"));
        gridArray.add(new Item(newYear,"New Year"));
        gridArray.add(new Item(birthday,"Birthday"));

//
//
        gridView = (GridView) findViewById(R.id.gridView1);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent myIntent=new Intent(ListTopic.this, DetailSMS.class);
                Bundle bundle=new Bundle();
                TextView text = (TextView) v.findViewById(R.id.item_text);
                String topic = text.getText().toString();

                bundle.putString("topic", topic);
                myIntent.putExtra("MyTopic", bundle);
                startActivity(myIntent);
            }
        });


//        btnValentine=(Button) findViewById(R.id.btn_Valentine);
//        btnValentine.setOnClickListener(new eventTopic());
//
//        btnFavorite=(Button) findViewById(R.id.btn_Favorite);
//        btnFavorite.setOnClickListener(new eventTopic());

//        imgbHome=(ImageButton)findViewById(R.id.imghome);
//        imgbHome.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            }
//        });
    }

//    private class eventTopic implements View.OnClickListener
//    {
//
//        @Override
//        public void onClick(View v) {
//            Intent myIntent=new Intent(ListTopic.this, DetailSMS.class);
//            Bundle bundle=new Bundle();
//            // TODO Auto-generated method stub
//            if(v.getId()==R.id.btn_Valentine)
//            {
//                String topic = "VLT";
//                bundle.putString("topic", topic);
//                myIntent.putExtra("MyTopic", bundle);
//                startActivity(myIntent);
//            }
//            else if(v.getId()==R.id.btn_Favorite)
//            {
//                String topic = "Favorite";
//                bundle.putString("topic", topic);
//                myIntent.putExtra("MyTopic", bundle);
//                startActivity(myIntent);
//            }
//
////            else if(v.getId()==R.id.buttonInsertBook)
////            {
////                Intent intent=new Intent(MainActivity.this, InsertBookActivity.class);
////                startActivity(intent);
////            }
//        }
//
//    }
}
