package com.duongtran.smskute;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditSmsAtivity extends AppCompatActivity {

    SMS sms;
    int curTab;
    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;
    private static final String TAG = "daothuy";

    private int mode;
   // private EditText textTitle;
    private EditText textContent;

    private boolean needRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_sms_ativity);

     //   this.textTitle = (EditText)this.findViewById(R.id.text_note_title);
        this.textContent = (EditText)this.findViewById(R.id.content);

        Intent intent = this.getIntent();
        this.sms = (SMS) intent.getSerializableExtra("sms");
        curTab = (int) intent.getSerializableExtra("curTab");
        if(sms == null)  {
            this.mode = MODE_CREATE;
        } else  {
            Log.i(TAG, "MyDatabaseHelper.edittttttttttt ... ");
            this.mode = MODE_EDIT;
          //  this.textTitle.setText(sms.getTitle());
            this.textContent.setText(sms.getContent());
        }
    }

    // Người dùng Click vào nút Save.
    public void buttonSaveClicked(View view)  {
        MyDatabaseHelper db = new MyDatabaseHelper(this);

     //   String title = this.textTitle.getText().toString();
        String content = this.textContent.getText().toString();

      //  if(title.equals("") || content.equals("")) {
        if(content.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter content", Toast.LENGTH_LONG).show();
            return;
        }

        if(mode==MODE_CREATE ) {
            switch(curTab)
            {
                case 0 :
                    this.sms= new SMS(content, "VLT", false);
                    break;
                case 1 :
                    this.sms= new SMS(content, "CH", false);
                    break;
                case 2 :
                    this.sms= new SMS(content, "2010", false);
                    break;
                case 3 :
                    this.sms= new SMS(content, "83", false);
                    break;
                case 4 :
                    this.sms= new SMS(content, "GN", false);
                    break;
                case 5 :
                    this.sms= new SMS(content, "LO", false);
                    break;
                case 6 :
                    this.sms= new SMS(content, "GL", false);
                    break;
                case 7 :
                    this.sms= new SMS(content, "HP", false);
                    break;
                case 8 :
                    this.sms= new SMS(content, "NY", false);
                    break;
                case 9 :
                    this.sms= new SMS(content, "BD", false);
                    break;
                case 10 :
                    this.sms= new SMS(content, "NY", false);
                    break;
                default :
                    this.sms= new SMS(content, "VLT", true);
            }
            db.addSMS(sms);
        } else  {
           // this.sms.settitle(title);
            this.sms.setContent(content);
            db.updateSMS(sms);
        }

        this.needRefresh = true;
        // Trở lại MainActivity.
        this.onBackPressed();
    }

    // Khi người dùng Click vào button Cancel.
    public void buttonCancelClicked(View view)  {
        // Không làm gì, trở về MainActivity.
        this.onBackPressed();
    }

    // Khi Activity này hoàn thành,
    // có thể cần gửi phản hồi gì đó về cho Activity đã gọi nó.
    @Override
    public void finish() {

        // Chuẩn bị dữ liệu Intent.
        Intent data = new Intent();
        // Yêu cầu MainActivity refresh lại ListView hoặc không.
        data.putExtra("needRefresh", needRefresh);

        // Activity đã hoàn thành OK, trả về dữ liệu.
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }

}
