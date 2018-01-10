package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edu.bjfu.cs2015.ibasketball.UI.FullScreenVideoView;
import com.edu.bjfu.cs2015.ibasketball.tool.HttpConnection;
import com.edu.bjfu.cs2015.ibasketball.tool.JsonToInstance;
import com.edu.bjfu.cs2015.ibasketball.tool.LoadImagesTask;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import JSONPO.UserinfoMessage;
import mehdi.sakout.fancybuttons.FancyButton;

import static com.edu.bjfu.cs2015.ibasketball.tool.JsonToString.JsonToString;

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

    private static android.content.Context context;

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

        //button2显示网络图片
        Button button2= (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str="http://www.sinaimg.cn/dy/slidenews/2_img/2015_05/792_1430400_568388.jpg";
                new LoadImagesTask(imageView).execute(str);
            }
        });

        //button3注册登录
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

        //button4测试网络连接
        Button button4= (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://apis.juhe.cn/mobile/get";//这里和GET方式不同的是去掉了“？”后面的参数；
                //1.设置参数
                Map map=new HashMap();
                map.put("phone","13429667914");
                map.put("key","9719c91bd4ac2647c67c6cd067b5cb8e");
                HttpConnection.setMap(map);
                //2.设置组件
                HttpConnection.setContext(getApplicationContext());
                //3.设置url
                HttpConnection.setUrl(url);
                //4.执行连接
                HttpConnection.volleyPost();
                //5.打印结果--响应有延迟，延迟使用getResponse()
                textView.setText(HttpConnection.getResponse());
            }
        });
    }


}

