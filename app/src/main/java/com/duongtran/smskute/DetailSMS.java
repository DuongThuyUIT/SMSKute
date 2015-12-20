package com.duongtran.smskute;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DetailSMS extends Activity {
    private static final int MENU_ITEM_SENT = 111;
    private static final int MENU_ITEM_LIKE = 222;
    private static final int MENU_ITEM_SHARE = 333;
    private static final int MENU_ITEM_EDIT = 444;
    private static final int MENU_ITEM_DELETE = 555;
    private static final int MENU_ITEM_CREATE = 666;

    private static final int MY_REQUEST_CODE = 1000;//D

    private final List<SMS> SMSList = new ArrayList<SMS>();
    private ArrayAdapter<SMS> listViewAdapter;

    ArrayList<SMS> arrSMS = new ArrayList<SMS>();
    ArrayAdapterSMS adapter=null;

   ListView lvSMS = null;
    private boolean needRefresh;
//    private ArrayAdapter<SMS> listViewAdapter;
//    ListView lv = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sms);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        lvSMS = (ListView) findViewById(R.id.lvSMS);


        db.createDefaultSMSIfNeed();

        List<SMS> list=  db.getAllSMS();
        this.SMSList.addAll(list);

        // Định nghĩa một Adapter.
        // 1 - Context
        // 2 - Layout cho các dòng.
        // 3 - ID của TextView mà dữ liệu sẽ được ghi vào
        // 4 - Danh sách dữ liệu.

        this.listViewAdapter = new ArrayAdapter<SMS>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, this.SMSList);


        // Đăng ký Adapter cho ListView.
        this.lvSMS.setAdapter(this.listViewAdapter);

        // Đăng ký Context menu cho ListView.
        registerForContextMenu(this.lvSMS);

        //////////////////
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyTopic");
        String topic = packageFromCaller.getString("topic");



        if(topic.equals("Favorite"))
            list =  db.getSMSLiked();
        else
            list =  db.getSMS(topic);

        this.arrSMS.addAll(list);
        //Khởi tạo đối tượng adapter và gán Data source

        adapter = new ArrayAdapterSMS(
                this,
                R.layout.item_sms,// lấy custom layout
                arrSMS/*thiết lập data source*/);
        lvSMS.setAdapter(adapter);//gán

        registerForContextMenu(this.lvSMS);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo)    {

        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Select The Action");

        // groupId, itemId, order, title
        menu.add(0, MENU_ITEM_SENT , 0, "Sent");
        menu.add(0, MENU_ITEM_LIKE, 1, "Like");
        menu.add(0, MENU_ITEM_SHARE , 2, "Share");
        menu.add(0, MENU_ITEM_EDIT, 3, "Edit");
        menu.add(0, MENU_ITEM_DELETE, 4, "Delete");
        menu.add(0, MENU_ITEM_CREATE, 5, "Create");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final SMS selectedSMS = (SMS) this.lvSMS.getItemAtPosition(info.position);

        if(item.getItemId() == MENU_ITEM_DELETE){
            // Hỏi trước khi xóa.
            new AlertDialog.Builder(this)
                    .setMessage(selectedSMS.getNContent() + ". Are you sure you want to delete?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteSMS(selectedSMS);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        else if(item.getItemId() == MENU_ITEM_CREATE){
            Intent intent = new Intent(this, AddEditSmsAtivity.class);

            // Start AddEditNoteActivity, có phản hồi.
            this.startActivityForResult(intent, MY_REQUEST_CODE);
        }
        else if(item.getItemId() == MENU_ITEM_EDIT ){
            Intent intent = new Intent(this, AddEditSmsAtivity.class);
            intent.putExtra("sms", selectedSMS);

            // Start AddEditNoteActivity, có phản hồi.
            this.startActivityForResult(intent,MY_REQUEST_CODE);
        }
        else
        if(item.getItemId() == MENU_ITEM_LIKE){
            MyDatabaseHelper db = new MyDatabaseHelper(this);
            selectedSMS.setLiked(true);
            db.updateSMS(selectedSMS);
            this.needRefresh = true;
        }
        return true;
    }

    // Người dùng đồng ý xóa một SMS.
    private void deleteSMS(SMS sms)  {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteSMS(sms);
        this.arrSMS.remove(sms);
        // Refresh ListView.
        this.adapter.notifyDataSetChanged();
    }
    // Khi AddEditNoteActivity hoàn thành, nó gửi phản hồi lại.
    // (Nếu bạn đã start nó bằng cách sử dụng startActivityForResult()  )

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE ) {
            boolean needRefresh = data.getBooleanExtra("needRefresh",true);
            // Refresh ListView
            if(needRefresh) {
                this.SMSList.clear();
                MyDatabaseHelper db = new MyDatabaseHelper(this);
                List<SMS> list=  db.getAllSMS();
                this.SMSList.addAll(list);
                // Thông báo dữ liệu thay đổi (Để refresh ListView).
                this.listViewAdapter.notifyDataSetChanged();
            }
        }
    }

}
