package com.duongtran.smskute;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailSMS extends Activity {
    private static final int MENU_ITEM_SENT = 111;
    private static final int MENU_ITEM_LIKE = 222;
    private static final int MENU_ITEM_SHARE = 333;
    private static final int MENU_ITEM_EDIT = 444;
    private static final int MENU_ITEM_DELETE = 555;

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

        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyTopic");
        String topic = packageFromCaller.getString("topic");

        List<SMS> list = null;

        if(topic.equals("Favorite"))
            list =  db.getSMSLiked();
        else
            list =  db.getSMS(topic);
        if(list.size() == 0){
            Toast toast=Toast.makeText(this, "Chủ đề này chưa có SMS nào nhé bợn!",   Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            this.arrSMS.addAll(list);
            //Khởi tạo đối tượng adapter và gán Data source
            adapter = new ArrayAdapterSMS(
                    this,
                    R.layout.item_sms,// lấy custom layout
                    arrSMS/*thiết lập data source*/);
            lvSMS.setAdapter(adapter);//gán

            registerForContextMenu(this.lvSMS);
        }


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
        else
        if(item.getItemId() == MENU_ITEM_LIKE){
            MyDatabaseHelper db = new MyDatabaseHelper(this);
            selectedSMS.setLiked(true);
            db.updateSMS(selectedSMS);
            this.needRefresh = true;
        }
        else
        if(item.getItemId() == MENU_ITEM_SHARE){
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, selectedSMS.getContent());
            ((ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE)).setText(selectedSMS.getContent());
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        }
        return true;
    }

    // Người dùng đồng ý xóa một SMS.
    private void deleteSMS(SMS sms) {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteSMS(sms);
        this.arrSMS.remove(sms);
        // Refresh ListView.
        this.adapter.notifyDataSetChanged();
    }
}
