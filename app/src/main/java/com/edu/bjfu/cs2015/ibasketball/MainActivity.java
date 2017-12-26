package com.edu.bjfu.cs2015.ibasketball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import JDBC.Beans.Userinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获得控件控制权
        Button button = (Button) findViewById(R.id.b_login);
        TextView textView = (TextView) findViewById(R.id.tv_loginID);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 定义变量传参
                final Userinfo[] userinfo = {null};

                // 创建子线程进行数据库操作
                Thread t1 = new Thread() {
                    public void run() {
                        userinfo[0] = Userinfo.getByName("chris");
                    }
                };
                t1.start();
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    Log.e("MA2", e.getMessage());
                }

                // 获得输出参数
                // UI操作要在主线程中
                textView.setText(userinfo[0].getUserTruname());

            }

        });
    }
}