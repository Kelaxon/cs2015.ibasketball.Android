package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.bjfu.cs2015.ibasketball.UI.FullScreenVideoView;
import com.edu.bjfu.cs2015.ibasketball.tool.LoadImagesTask;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;

import JSONPO.Userinfo;
import JSONPO.UserinfoMessage;
import mehdi.sakout.fancybuttons.FancyButton;
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
    String str="这是空的";
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

                //存放用户登录信息
                textView.setText(str);

                String jsonStr="";
                //从resource的raw中读取文件数据
                Resources resources=TestActivity.this.getResources();
                try{
                    InputStream is=resources.openRawResource(R.raw.useractionapp);

                    InputStreamReader isr=new InputStreamReader(is,"UTF-8");

                    BufferedReader br=new BufferedReader(isr);

                    String str="";

                    StringBuffer sb=new StringBuffer();
                    while ((str=br.readLine())!=null){
                        sb.append(str);
                        sb.append('\n');
                    }
                    jsonStr=sb.toString();
                    br.close();
                    isr.close();
                    is.close();
                }catch (Exception e){

                    e.printStackTrace();
                }

                Gson gson=new Gson();

                //解析JSON结点

                //1获取json解析者
                JsonParser jsonParser=new JsonParser();
                //2获取根节点元素
                JsonElement jsonElement=jsonParser.parse(jsonStr);
                //3判断结点属于什么类型
                JsonObject jsonObject=jsonElement.getAsJsonObject();
                //4取得结点下每个结点的value
                JsonPrimitive flagJson=jsonObject.getAsJsonPrimitive("message" );
                //5
                String message=flagJson.getAsString();
                //6获取当前对象
                JsonObject object=jsonObject.getAsJsonObject("currentUser");
                //7
                Userinfo userinfo=gson.fromJson(object,Userinfo.class);
                //8
                UserinfoMessage userinfoMessage=new UserinfoMessage();

                userinfoMessage.setMessage(message);
                userinfoMessage.setUserinfo(userinfo);

                if(!userinfoMessage.equals(null)){

                    textView.setText("JSON解析出来了！"+userinfoMessage.toString());
                }else {
                    textView.setText("jsoN解析失败！");
                }

                String url="http://n.sinaimg.cn/sports/2_img/upload/d574b730/20171120/A17i-fynwnty5776525.jpg";
                //异步传输图片
                new LoadImagesTask(imageView).execute(url);

            }
        });

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

    }

}

