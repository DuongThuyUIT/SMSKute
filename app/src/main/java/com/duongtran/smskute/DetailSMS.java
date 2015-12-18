package com.duongtran.smskute;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailSMS extends Activity {

    ArrayList<SMS> arrSMS = new ArrayList<SMS>();
    ArrayAdapterSMS adapter=null;
    ListView lvSMS = null;
//    private ArrayAdapter<SMS> listViewAdapter;
//    ListView lv = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sms);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        lvSMS = (ListView) findViewById(R.id.lvSMS);
        String str = "VLT";
        List<SMS> list=  db.getSMS(str);
        this.arrSMS.addAll(list);
        //Khởi tạo đối tượng adapter và gán Data source

//
        adapter = new ArrayAdapterSMS(
                this,
                R.layout.item_sms,// lấy custom layout
                arrSMS/*thiết lập data source*/);
        lvSMS.setAdapter(adapter);//gán

//        lvSMS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1,
//                                    int arg2, long arg3) {
//                // TODO Auto-generated method stub
//                Toast.makeText(ShowListAuthorActivity.this, "View -->" + list.get(arg2).toString(), Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(ShowListAuthorActivity.this, CreateAuthorActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("KEY", 1);
//                bundle.putString("getField1", list.get(arg2).getField1().toString());
//                bundle.putString("getField2", list.get(arg2).getField2().toString());
//                bundle.putString("getField3", list.get(arg2).getField3().toString());
//                intent.putExtra("DATA", bundle);
//                dataClick = list.get(arg2);
//                startActivityForResult(intent, MainActivity.OPEN_AUTHOR_DIALOG);
//            }
//        });


    }
}
