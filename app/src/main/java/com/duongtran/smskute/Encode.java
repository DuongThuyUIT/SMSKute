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
import android.widget.Toast;

public class Encode extends AppCompatActivity {
    private static final String TAG = "daothuy";
    ImageButton btnEncrypt;
    ImageButton btnDecrypt;
    ImageButton btnCopy;
    ImageButton btnSend;
    EditText txt;
    EditText key;
    EditText txtResult;
    String textEncode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode);

        btnEncrypt = (ImageButton) findViewById(R.id.btnEncrypt);
        btnEncrypt.setOnClickListener(new MyEvent());

        btnDecrypt = (ImageButton) findViewById(R.id.btnDecrypt);
        btnDecrypt.setOnClickListener(new MyEvent());

        btnCopy = (ImageButton) findViewById(R.id.btnCopy1);
        btnCopy.setOnClickListener(new MyEvent());

        btnSend = (ImageButton) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new MyEvent());

        txt = (EditText) findViewById(R.id.text);

        Intent intent = getIntent();
        this.textEncode = (String) intent.getSerializableExtra("SMSContent");
        if(textEncode != null)
            txt.setText(textEncode);
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
                if(textString.equals("")){
                    Toast toast=Toast.makeText(Encode.this,"Text cannot be empty. Please try again!" ,   Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                if(keyString.equals("")){
                    Toast toast=Toast.makeText(Encode.this,"Key cannot be empty. Please try again!" ,   Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                    txtResult.setText(vi.vigenereCipherEn(textString, keyString));
            }
            else
            if(v.getId()==R.id.btnDecrypt){
                if(textString.equals("")){
                    Toast toast=Toast.makeText(Encode.this,"Text cannot be empty. Please try again!" ,   Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                if(keyString.equals("")){
                    Toast toast=Toast.makeText(Encode.this,"Key cannot be empty. Please try again!" ,   Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                txtResult.setText(vi.vigenereCipherDe(textString, keyString));
            }
            else
            if(v.getId()==R.id.btnSend)
            {
                Log.i(TAG, "button Copy ");
                String send = resultTxt;
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.putExtra("sms_body", send);
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);
            }
            else{
                txt.setText(resultTxt);
            }

        }
    }
}
