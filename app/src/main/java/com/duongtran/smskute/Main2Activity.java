package com.duongtran.smskute;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends Activity {
    ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Khởi tạo progressBar với đối là Context
        progressBar = new ProgressDialog(Main2Activity.this);
        // Cho phép hủy progressBar nếu ấn nút Back
        progressBar.setCancelable(true);
        // Đặt tiêu đề cho ProgressBar
        progressBar.setMessage("Đang xử lý, vui lòng đợi...");
        // Đặt giao diện cho ProgressBar
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // Đặt giá trị đầu tiên, đây là giá trị thể hiện mức độ hoàn thành công
        // việc có thang từ 0 - > 100
        // do hiện tại công việc chưa hoàn thành được chút nào nên ta đặt là 0
        progressBar.setProgress(0);
        // Đặt cho giá trị lớn nhất thể hiện mức độ hoàn thành công việc là 100
        progressBar.setMax(100);
        // Hiện ProgressBar
        progressBar.show();

        // Tạo một luồng xử lý công việc.
        new MyThread().start();



    }

    class MyThread extends Thread{

        @Override
        public void run(){
            // xử lý công việc cần hoàn thành
            for(int i = 0; i< 2; i++){
                // Tạm dừng 1s, thực tế thì chỗ này là xử lý công việc
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // tính xem công việc đã hoàn thành bao nhiêu phần trăm và đưa lên progressbar
                progressBar.setProgress((i * 100) / 2);
            }
            // đóng brogressbar.
            progressBar.dismiss();

            Intent myIntent=new Intent(Main2Activity.this, MainActivity.class);
            startActivity(myIntent);
        }
    }
}
