package com.duongtran.smskute;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Encode extends AppCompatActivity {
    private static final String TAG = "daothuy";
    Button btnEncrypt;
    Button btnDecrypt;
    Button btnCopy;
    EditText txt;
    EditText key;
    EditText txtResult;
    String SMSEncode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode);

        btnEncrypt = (Button) findViewById(R.id.btnEncrypt);
        btnEncrypt.setOnClickListener(new MyEvent());

        btnDecrypt = (Button) findViewById(R.id.btnDecrypt);
        btnDecrypt.setOnClickListener(new MyEvent());

        btnCopy = (Button) findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(new MyEvent());

        txt = (EditText) findViewById(R.id.text);
//        Intent intent = this.getIntent();
//        SMSEncode = (String) intent.getSerializableExtra("SMSEncode");
//        if(SMSEncode != null){
//            txt.setText(SMSEncode);
//        }

        Intent callerIntent=getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("SMSEncode");
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        String SMSContent = packageFromCaller.getString("SMSContent");
        if(SMSContent != null)
            txt.setText(packageFromCaller.getString("SMSContent"));
    }

    private class MyEvent implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            VigenereCipher vi = new VigenereCipher();
            txt = (EditText) findViewById(R.id.text);
            String textString = txt.getText().toString();
            key = (EditText) findViewById(R.id.key);
            String keyString = key.getText().toString();
            txtResult = (EditText) findViewById(R.id.result);
            String resultTxt = txtResult.getText().toString();
            if(v.getId()==R.id.btnEncrypt)
            {
                Log.i(TAG, "button Encrypt ");
                txtResult.setText(vi.vigenereCipherEn(textString, keyString));
            }
            else
            if(v.getId()==R.id.btnDecrypt){
                Log.i(TAG, "button Decrypt ");
                txtResult.setText(vi.vigenereCipherDe(textString, keyString));
            }
            else{
                Log.i(TAG, "button Copy ");
                String send = resultTxt;
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.putExtra("sms_body", send);
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);
            }
        }
    }
}
