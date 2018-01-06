package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.bjfu.cs2015.ibasketball.UI.FullScreenVideoView;
import com.edu.bjfu.cs2015.ibasketball.tool.JsonToInstance;
import com.edu.bjfu.cs2015.ibasketball.tool.LoadImagesTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.Timestamp;

import JSONPO.Userinfo;
import JSONPO.UserinfoMessage;
import mehdi.sakout.fancybuttons.FancyButton;

import static com.edu.bjfu.cs2015.ibasketball.tool.JsonToString.*;

/*
test web
 */
public class TestActivity extends AppCompatActivity {
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private FullScreenVideoView mVideoView;
    private FancyButton mRegisterButton;
    private FancyButton mLoginButton;
    private TextView mBrowerButton;
    private TextView textView;
    private ImageView imageView;

    private UserinfoMessage uu;
    //图片地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
         textView= (android.widget.TextView) findViewById(R.id.testView);
         imageView= (ImageView) findViewById(R.id.imageView);
        Button button= (Button) findViewById(R.id.buntton);

        //button1设置json解析
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String str="这是空的";

                //泛型使用
                JsonToInstance<UserinfoMessage> jsonToInstance=new JsonToInstance<UserinfoMessage>();

                Type type=new TypeToken<UserinfoMessage>(){}.getType();

                UserinfoMessage userinfo=jsonToInstance.JsonToInstance(JsonToString(TestActivity.this.getResources()),type);

                if(userinfo!=null){
                    textView.setText("JSON解析成功！");
                    uu=userinfo;
                }else {
                    textView.setText("JSON解析失败了！");
                }

                String url="http://n.sinaimg.cn/sports/2_img/upload/d574b730/20171120/A17i-fynwnty5776525.jpg";
                //异步传输图片
                new LoadImagesTask(imageView).execute(url);

            }

        });

        //显示网络图片
        Button button2= (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str="http://www.sinaimg.cn/dy/slidenews/2_img/2015_05/792_1430400_568388.jpg";
                new LoadImagesTask(imageView).execute(str);
            }
        });

        //注册登录
        Button button3= (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str="http://sports.people.com.cn/NMediaFile/2016/1121/MAIN201611210832000065802083126.jpg";
                new LoadImagesTask(imageView).execute(str);

                Intent intent = new Intent(TestActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });

        Button button4= (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(uu.toString());
            }
        });

    }

}

