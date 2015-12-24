package com.duongtran.smskute;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailSMS extends TabActivity {

    private static final String TAG = "daothuy";
    TabHost tabHost;
    private static final int MENU_ITEM_SENT = 111;
    private static final int MENU_ITEM_LIKE = 222;
    private static final int MENU_ITEM_SHARE = 333;
    private static final int MENU_ITEM_EDIT = 444;
    private static final int MENU_ITEM_DELETE = 555;
    private static final int MENU_ITEM_CREATE = 666;

    private static final int MY_REQUEST_CODE = 1000;//D

    private final List<SMS> SMSList = new ArrayList<SMS>();
    private ArrayAdapter<SMS> listViewAdapter;

    ArrayList<SMS> arrSMSValentine = new ArrayList<SMS>();
    ArrayList<SMS> arrSMSChristmas = new ArrayList<SMS>();
    ArrayList<SMS> arrSMS2010 = new ArrayList<SMS>();
    ArrayList<SMS> arrSMS0803 = new ArrayList<SMS>();
    ArrayList<SMS> arrSMSGoodNight = new ArrayList<SMS>();
    ArrayList<SMS> arrSMSGreeting = new ArrayList<SMS>();
    ArrayList<SMS> arrSMSLove = new ArrayList<SMS>();
    ArrayList<SMS> arrSMSNewYear = new ArrayList<SMS>();
    ArrayList<SMS> arrSMSBirthday = new ArrayList<SMS>();
    ArrayList<SMS> arrSMSGoodLuck = new ArrayList<SMS>();
    ArrayList<SMS> arrSMSHappy = new ArrayList<SMS>();
    ArrayList<SMS> arrSMSFavorite = new ArrayList<SMS>();

    ArrayAdapterSMS adapterValentine = null;
    ArrayAdapterSMS adapterChristmas = null;
    ArrayAdapterSMS adapter2010 = null;
    ArrayAdapterSMS adapter0803 = null;
    ArrayAdapterSMS adapterGoodNight = null;
    ArrayAdapterSMS adapterGreeting = null;
    ArrayAdapterSMS adapterLove = null;
    ArrayAdapterSMS adapterNewYear = null;
    ArrayAdapterSMS adapterBirthday = null;
    ArrayAdapterSMS adapterGoodLuck = null;
    ArrayAdapterSMS adapterHappy = null;
    ArrayAdapterSMS adapterFavorite = null;


    ListView lvSMSValentine = null;
    ListView lvSMSChristmas = null;
    ListView lvSMS2010 = null;
    ListView lvSMS0803 = null;
    ListView lvSMSGoodNight = null;
    ListView lvSMSGreeting = null;
    ListView lvSMSLove = null;
    ListView lvSMSNewYear = null;
    ListView lvSMSBirthday = null;
    ListView lvSMSGoodLuck = null;
    ListView lvSMSHappy = null;
    ListView lvSMSFavorite = null;
    private boolean needRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sms);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        lvSMSValentine = (ListView) findViewById(R.id.lvSMSValentine);
        lvSMSChristmas = (ListView) findViewById(R.id.lvSMSChristmas);
        lvSMS2010 = (ListView) findViewById(R.id.lvSMS2010);
        lvSMS0803 = (ListView) findViewById(R.id.lvSMS0803);
        lvSMSGoodNight = (ListView) findViewById(R.id.lvSMSGoodNight);
        lvSMSGreeting = (ListView) findViewById(R.id.lvSMSGreeting);
        lvSMSLove = (ListView) findViewById(R.id.lvSMSLove);
        lvSMSNewYear = (ListView) findViewById(R.id.lvSMSNewYear);
        lvSMSBirthday = (ListView) findViewById(R.id.lvSMSBirthday);
        lvSMSGoodLuck = (ListView) findViewById(R.id.lvSMSGoodLuck);
        lvSMSHappy = (ListView) findViewById(R.id.lvSMSHappy);
        lvSMSFavorite = (ListView) findViewById(R.id.lvSMSFavorite);


        List<SMS> listValentine = db.getSMS("VLT");
        List<SMS> listChristmas = db.getSMS("CH");
        List<SMS> list2010 = db.getSMS("2010");
        List<SMS> list0803 = db.getSMS("83");
        List<SMS> listGoodNight = db.getSMS("GN");
        List<SMS> listGreeting = db.getSMS("GR");
        List<SMS> listLove = db.getSMS("LO");
        List<SMS> listNewYear = db.getSMS("NY");
        List<SMS> listBirthday = db.getSMS("BD");
        List<SMS> listGoodLuck = db.getSMS("GL");
        List<SMS> listHappy = db.getSMS("HP");
        List<SMS> listFavorite = db.getSMSLiked();

        this.arrSMSValentine.addAll(listValentine);
        adapterValentine = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMSValentine);
        lvSMSValentine.setAdapter(adapterValentine);
        registerForContextMenu(this.lvSMSValentine);

        this.arrSMSChristmas.addAll(listChristmas);
        adapterChristmas = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMSChristmas);
        lvSMSChristmas.setAdapter(adapterChristmas);
        registerForContextMenu(this.lvSMSChristmas);

        this.arrSMS2010.addAll(list2010);
        adapter2010 = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMS2010);
        lvSMS2010.setAdapter(adapter2010);
        registerForContextMenu(this.lvSMS2010);

        this.arrSMS0803.addAll(list0803);
        adapter0803 = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMS0803);
        lvSMS0803.setAdapter(adapter0803);
        registerForContextMenu(this.lvSMS0803);

        this.arrSMSGoodNight.addAll(listGoodNight);
        adapterGoodNight = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMSGoodNight);
        lvSMSGoodNight.setAdapter(adapterGoodNight);
        registerForContextMenu(this.lvSMSGoodNight);

        this.arrSMSGreeting.addAll(listGreeting);
        adapterGreeting = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMSGreeting);
        lvSMSGreeting.setAdapter(adapterGreeting);
        registerForContextMenu(this.lvSMSGreeting);

        this.arrSMSLove.addAll(listLove);
        adapterLove = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMSLove);
        lvSMSLove.setAdapter(adapterLove);
        registerForContextMenu(this.lvSMSLove);

        this.arrSMSNewYear.addAll(listNewYear);
        adapterNewYear = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMSNewYear);
        lvSMSNewYear.setAdapter(adapterNewYear);
        registerForContextMenu(this.lvSMSNewYear);

        this.arrSMSBirthday.addAll(listBirthday);
        adapterBirthday = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMSBirthday);
        lvSMSBirthday.setAdapter(adapterBirthday);
        registerForContextMenu(this.lvSMSBirthday);

        this.arrSMSGoodLuck.addAll(listGoodLuck);
        adapterGoodLuck = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMSGoodLuck);
        lvSMSGoodLuck.setAdapter(adapterGoodLuck);
        registerForContextMenu(this.lvSMSGoodLuck);

        this.arrSMSHappy.addAll(listHappy);
        adapterHappy = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMSHappy);
        lvSMSHappy.setAdapter(adapterHappy);
        registerForContextMenu(this.lvSMSHappy);

        this.arrSMSFavorite.addAll(listFavorite);
        adapterFavorite = new ArrayAdapterSMS(this, R.layout.item_sms, arrSMSFavorite);
        lvSMSFavorite.setAdapter(adapterFavorite);
        registerForContextMenu(this.lvSMSFavorite);

        tabHost = getTabHost();

        this.setNewTab(this, tabHost, "tab1", R.string.tab1, R.drawable.valentine, R.id.tab1);
        this.setNewTab(this, tabHost, "tab2", R.string.tab2, R.drawable.christmas, R.id.tab2);
        this.setNewTab(this, tabHost, "tab3", R.string.tab3, R.drawable._20_10, R.id.tab3);
        this.setNewTab(this, tabHost, "tab4", R.string.tab4, R.drawable._08_03, R.id.tab4);
        this.setNewTab(this, tabHost, "tab5", R.string.tab5, R.drawable.night, R.id.tab5);
        this.setNewTab(this, tabHost, "tab6", R.string.tab6, R.drawable.love, R.id.tab6);
        this.setNewTab(this, tabHost, "tab7", R.string.tab7, R.drawable.goodluck_1, R.id.tab7);
        this.setNewTab(this, tabHost, "tab8", R.string.tab8, R.drawable.happy, R.id.tab8);
        this.setNewTab(this, tabHost, "tab9", R.string.tab9, R.drawable.favorite, R.id.tab9);
        this.setNewTab(this, tabHost, "tab10", R.string.tab10, R.drawable.greeting, R.id.tab10);
        this.setNewTab(this, tabHost, "tab11", R.string.tab11, R.drawable.newyear_1, R.id.tab11);
        this.setNewTab(this, tabHost, "tab12", R.string.tab12, R.drawable.birthday, R.id.tab12);

        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyTopic");
        String topic = packageFromCaller.getString("topic");


        switch(topic)
        {
            case "Valentine" :
                tabHost.setCurrentTab(0);
                break;
            case "Christmas" :
                tabHost.setCurrentTab(1);
                break;
            case "20-10" :
                tabHost.setCurrentTab(2);
                break;
            case "08-03" :
                tabHost.setCurrentTab(3);
                break;
            case "Good Night" :
                tabHost.setCurrentTab(4);
                break;
            case "Love" :
                tabHost.setCurrentTab(5);
                break;
            case "Good Luck" :
                tabHost.setCurrentTab(6);
                break;
            case "Happy" :
                tabHost.setCurrentTab(7);
                break;
            case "Favorite" :
                tabHost.setCurrentTab(8);
                break;
            case "Greeting" :
                tabHost.setCurrentTab(9);
                break;
            case "New Year" :
                tabHost.setCurrentTab(10);
                break;
            default :
                tabHost.setCurrentTab(11);
        }

    }

    private void setNewTab(Context context, TabHost tabHost, String tag, int title, int icon, int contentID ){
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getTabIndicator(tabHost.getContext(), title, icon)); // new function to inject our own tab layout
        tabSpec.setContent(contentID);
        tabHost.addTab(tabSpec);
    }

    private View getTabIndicator(Context context, int title, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setImageResource(icon);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(title);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo)    {

        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Select The Action");

        // groupId, itemId, order, title
        menu.add(0, MENU_ITEM_SENT , 0, "Send");
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
        int curTab = tabHost.getCurrentTab();
        final SMS selectedSMS;
        switch(curTab)
        {
            case 0 :
                selectedSMS = (SMS) this.lvSMSValentine.getItemAtPosition(info.position);
                break;
            case 1 :
                selectedSMS = (SMS) this.lvSMSChristmas.getItemAtPosition(info.position);
                break;
            case 2 :
                selectedSMS = (SMS) this.lvSMS2010.getItemAtPosition(info.position);
                break;
            case 3 :
                selectedSMS = (SMS) this.lvSMS0803.getItemAtPosition(info.position);
                break;
            case 4 :
                selectedSMS = (SMS) this.lvSMSGoodNight.getItemAtPosition(info.position);
                break;
            case 5 :
                selectedSMS = (SMS) this.lvSMSLove.getItemAtPosition(info.position);
                break;
            case 6 :
                selectedSMS = (SMS) this.lvSMSGoodLuck.getItemAtPosition(info.position);
                break;
            case 7 :
                selectedSMS = (SMS) this.lvSMSHappy.getItemAtPosition(info.position);
                break;
            case 8 :
                selectedSMS = (SMS) this.lvSMSFavorite.getItemAtPosition(info.position);
                break;
            case 9 :
                selectedSMS = (SMS) this.lvSMSGreeting.getItemAtPosition(info.position);
                break;
            case 10 :
                selectedSMS = (SMS) this.lvSMSNewYear.getItemAtPosition(info.position);
                break;
            default :
                selectedSMS = (SMS) this.lvSMSBirthday.getItemAtPosition(info.position);
        }


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
        if(item.getItemId() == MENU_ITEM_CREATE){
            Intent intent = new Intent(this, AddEditSmsAtivity.class);
            intent.putExtra("curTab", curTab);
            this.startActivityForResult(intent, MY_REQUEST_CODE);

        }
        else if(item.getItemId() == MENU_ITEM_EDIT ){
            Intent intent = new Intent(this, AddEditSmsAtivity.class);
            intent.putExtra("sms", selectedSMS);
            intent.putExtra("curTab", curTab);
            this.startActivityForResult(intent,MY_REQUEST_CODE);
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
        else
        if(item.getItemId() == MENU_ITEM_SENT ){
            Intent sendintent = new Intent(Intent.ACTION_SEND);
//            sendintent.setType("text/plain");
            String stringtoshare = selectedSMS.getContent();
//            sendintent.putExtra(Intent.EXTRA_TEXT, stringtoshare );
//            this.startActivity(Intent.createChooser(sendintent, "share via"));
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.putExtra("sms_body", stringtoshare);
            sendIntent.setType("vnd.android-dir/mms-sms");
            this.startActivity(sendIntent);

        }
        return true;
    }

    // Người dùng đồng ý xóa một SMS.
    private void deleteSMS(SMS sms) {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteSMS(sms);
        int curTab = tabHost.getCurrentTab();
        switch(curTab)
        {
            case 0 :
                this.arrSMSValentine.remove(sms);
                // Refresh ListView.
                this.adapterValentine.notifyDataSetChanged();
                break;
            case 1 :
                this.arrSMSChristmas.remove(sms);
                // Refresh ListView.
                this.adapterChristmas.notifyDataSetChanged();
                break;
            case 2 :
                this.arrSMS2010.remove(sms);
                // Refresh ListView.
                this.adapter2010.notifyDataSetChanged();
                break;
            case 3 :
                this.arrSMS0803.remove(sms);
                // Refresh ListView.
                this.adapter0803.notifyDataSetChanged();
                break;
            case 4 :
                this.arrSMSGoodNight.remove(sms);
                // Refresh ListView.
                this.adapterGoodNight.notifyDataSetChanged();
                break;
            case 5 :
                this.arrSMSLove.remove(sms);
                // Refresh ListView.
                this.adapterLove.notifyDataSetChanged();
                break;
            case 6 :
                this.arrSMSGoodLuck.remove(sms);
                // Refresh ListView.
                this.adapterGoodLuck.notifyDataSetChanged();
                break;
            case 7 :
                this.arrSMSHappy.remove(sms);
                // Refresh ListView.
                this.adapterHappy.notifyDataSetChanged();
                break;
            case 8 :
                this.arrSMSFavorite.remove(sms);
                // Refresh ListView.
                this.adapterFavorite.notifyDataSetChanged();
                break;
            case 9 :
                this.arrSMSGreeting.remove(sms);
                // Refresh ListView.
                this.adapterGreeting.notifyDataSetChanged();
                break;
            case 10 :
                this.arrSMSNewYear.remove(sms);
                // Refresh ListView.
                this.adapterNewYear.notifyDataSetChanged();
                break;
            default :
                this.arrSMSBirthday.remove(sms);
                // Refresh ListView.
                this.adapterBirthday.notifyDataSetChanged();
        }

    }
    // Khi AddEditNoteActivity hoàn thành, nó gửi phản hồi lại.
    // (Nếu bạn đã start nó bằng cách sử dụng startActivityForResult()  )

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE ) {
            boolean needRefresh = data.getBooleanExtra("needRefresh",true);
            // Refresh ListView
            if(needRefresh) {
                MyDatabaseHelper db = new MyDatabaseHelper(this);
                int curTab = tabHost.getCurrentTab();
                List<SMS> list;

                switch(curTab)
                {
                    case 0 :
                        this.arrSMSValentine.clear();
                        list =  db.getSMS("VLT");
                        this.arrSMSValentine.addAll(list);
                        this.adapterValentine.notifyDataSetChanged();
                        break;
                    case 1 :
                        this.arrSMSChristmas.clear();
                        list =  db.getSMS("CH");
                        this.arrSMSChristmas.addAll(list);
                        this.adapterChristmas.notifyDataSetChanged();
                        break;
                    case 2 :
                        this.arrSMS2010.clear();
                        list =  db.getSMS("2010");
                        this.arrSMS2010.addAll(list);
                        this.adapter2010.notifyDataSetChanged();
                        break;
                    case 3 :
                        this.arrSMS0803.clear();
                        list =  db.getSMS("83");
                        this.arrSMS0803.addAll(list);
                        this.adapter0803.notifyDataSetChanged();
                        break;
                    case 4 :
                        this.arrSMSGoodNight.clear();
                        list =  db.getSMS("GN");
                        this.arrSMSGoodNight.addAll(list);
                        this.adapterGoodNight.notifyDataSetChanged();
                        break;
                    case 5 :
                        this.arrSMSLove.clear();
                        list =  db.getSMS("LO");
                        this.arrSMSLove.addAll(list);
                        this.adapterLove.notifyDataSetChanged();
                        break;
                    case 6 :
                        this.arrSMSLove.clear();
                        list =  db.getSMS("GL");
                        this.arrSMSLove.addAll(list);
                        this.adapterGoodLuck.notifyDataSetChanged();
                        break;
                    case 7 :
                        this.arrSMSHappy.clear();
                        list =  db.getSMS("HP");
                        this.arrSMSHappy.addAll(list);
                        this.adapterHappy.notifyDataSetChanged();
                        break;
                    case 8 :
                        this.arrSMSFavorite.clear();
                        list =  db.getSMSLiked();
                        this.arrSMSFavorite.addAll(list);
                        this.adapterFavorite.notifyDataSetChanged();
                        break;
                    case 9 :
                        this.arrSMSGreeting.clear();
                        list =  db.getSMS("GR");
                        this.arrSMSGreeting.addAll(list);
                        this.adapterGreeting.notifyDataSetChanged();
                        break;
                    case 10 :
                        this.arrSMSNewYear.clear();
                        list =  db.getSMS("NY");
                        this.arrSMSNewYear.addAll(list);
                        this.adapterNewYear.notifyDataSetChanged();
                        break;
                    default :
                        this.arrSMSBirthday.clear();
                        list =  db.getSMS("BD");
                        this.arrSMSBirthday.addAll(list);
                        this.adapterBirthday.notifyDataSetChanged();
                }


//                this.SMSList.clear();
//                MyDatabaseHelper db = new MyDatabaseHelper(this);
//                List<SMS> list=  db.getAllSMS();
//                this.SMSList.addAll(list);
//                this.listViewAdapter.notifyDataSetChanged();
            }
        }
    }

}
