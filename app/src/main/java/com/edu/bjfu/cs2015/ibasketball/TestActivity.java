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
                String url = "http://172.23.100.219:8088/0-BBBS/UserActionApp.action?username=molinli&password=123456";//这里和GET方式不同的是去掉了“？”后面的参数；

                //volleyPost();

                getVolley();
            }
        });
    }

    public void volleyPost() {
        String url = " http://192.168.43.113:2008/0-BBBS/loginApp";//这里和GET方式不同的是去掉了“？”后面的参数；
        /**
         * 第一个参数指定了请求方式，第二个参数指定了url，第三个参数指定了正确访问的返回结果，第四个参数是访问失败后的业务逻辑；
         *
         */
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                textView.setText(result);//返回结果显示在TextView;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError==null)
                    Log.e("tag3", "空");
                else{
                    volleyError.printStackTrace();
                    Log.e("tag3",volleyError.getMessage() );
                }
                textView.setText("未能请求到数据");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {//在这里封装了需要发送的参数；
                Log.e("tag?","Z");
                HashMap<String, String> map = new HashMap<>();
                map.put("UA", "1");
                map.put("userName", "molinli");//以键值对的形式存放；
                map.put("userPassword", "123456");
                return map;
            }
        };
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(request);//加入请求队列；
    }//volleyPost();

    public void getVolley() {
        /**
         * 开始进行网络请求，设置相应的请求参数；
         */
        String url = "http://192.168.43.113:2008/0-BBBS/loginApp?UA=1&userName=molinli&userPassword=123456";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {//请求成功时回调onResponse();
                textView.setText(result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {//请求成功时回调onErrorResponse();
                Log.e("tag2", volleyError.getMessage());
                textView.append("不能成功从服务器获取消息");
            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(request);//把请求放入到请求队列中；
    }

}

