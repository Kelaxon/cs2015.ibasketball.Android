package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.edu.bjfu.cs2015.ibasketball.UI.FullScreenVideoView;
import com.edu.bjfu.cs2015.ibasketball.tool.HttpConnection;
import com.edu.bjfu.cs2015.ibasketball.tool.JsonToInstance;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import Action.Action;
import Action.LoginAction;
import Action.ServerCallback;
import JSONPO.CurrentUser;
import JSONPO.Userinfo;
import mehdi.sakout.fancybuttons.FancyButton;

;

/**
 * Created by ChrisYoung on 2017/12/27.
 */


public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private FullScreenVideoView mVideoView;
    private FancyButton mRegisterButton;
    private FancyButton mLoginButton;
    private TextView mBrowerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
        setContentView(R.layout.activity_login);
        initBackgroundUI();


        // 登陆框的listener
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.tv_username);
        mPasswordView = (EditText) findViewById(R.id.et_password);

        // 默认
        mUsernameView.setText("molinli");
        mPasswordView.setText("123456");


        // 登陆按钮的listener
        mLoginButton = (FancyButton) findViewById(R.id.b_login);
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        attemptLogin();

                    }
                }).start();

            }
        });


        // 注册按钮的Listener
        mRegisterButton = (FancyButton) findViewById(R.id.b_register);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // 跳往注册界面
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                LoginActivity.this.finish();
            }
        });

        mBrowerButton = (TextView) findViewById(R.id.b_browser);
        mBrowerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 数据库中建游客账户


                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                LoginActivity.this.finish();
            }
        });

    }


    private void attemptLogin() {

        final String username = mUsernameView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (cancel) {
            focusView.requestFocus();
        } else {


            Map map = new HashMap();
            map.put("userName", username);
            map.put("userPassword", password);

            // 传入参数

            String infoMessage = "错误";

            HttpConnection.setMap(map);
            // TODO 用依赖注入实现不同方法的excecute(命令模式) modify by molinli
            Action userLoginAction = new LoginAction();

            //获取到当前contenxt
            userLoginAction.setContext(getApplicationContext());
            //请你指定http请求参数
            Map mapInfo = new HashMap();
            mapInfo.put("userName", username);
            mapInfo.put("userPassword", password);

            HttpConnection.execute(userLoginAction, mapInfo, new ServerCallback() {
                @Override
                public void onSuccess(JsonObject reponse) {

                }

                @Override
                public void onSuccess(JsonObject reponse) {
                    Userinfo userinfo = null;
                    if (reponse != null) {
                        Log.e("LogJson2", reponse + "");
                        JsonToInstance<Userinfo> jsonToInstance = new JsonToInstance();
                        //get类型
                        Type typeForParam = new TypeToken<Userinfo>() {}.getType();
                        //传入去掉头部的json String 进行解析
                        userinfo = jsonToInstance.ToInstance(reponse.getAsString(), typeForParam);
                    }
                    // 有错情况
                    if (userinfo == null) {
                        Toast.makeText(LoginActivity.this, infoMessage, Toast.LENGTH_SHORT).show();

                    } else {
                        // 无错情况
                        Log.e("userInstance", userinfo.getUserName()+"");
                        CurrentUser.setCurrentUser(userinfo);
                        LoginActivity.this.finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }
                }
            });
//                        HttpConnection.execute(userLoginAction, mapInfo);
//                        if (HttpConnection.getResponse() == null)
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        else
//                            break;
        }
        //获取响应 json文件

    }
//            String reponse = HttpConnection.getResponse();
//            HttpConnection.setResponse(null);
//            Log.e("tag1", reponse);
//            //请问你要get什么？
//            //infoMessage = HttpConnection.get ??;
//
//            // TODO 获得用户对象 modify by molinli
//            if (reponse != null) {
//                JsonToInstance<Userinfo> jsonToInstance = new JsonToInstance();
//                //get类型
//                Type typeForParam = new TypeToken<UserinfoMessage>() {
//                }.getType();
//                //
//                userinfo[0] = jsonToInstance.ToInstance(reponse, typeForParam);
//            }
//
//            // 有错情况
//            if (userinfo[0] == null) {
//                Toast.makeText(LoginActivity.this, infoMessage, Toast.LENGTH_SHORT).show();
//
//            } else {
//                // 无错情况
//                CurrentUser.setCurrentUser(userinfo[0]);
//                LoginActivity.this.finish();
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            }


//    }


    public void initUI() {
        // 沉浸
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        // 取消上下状态栏
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void initBackgroundUI() {
        // 视频背景
        mVideoView = (FullScreenVideoView) findViewById(R.id.vv_background);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.loginvideo;
        mVideoView.setVideoURI(Uri.parse(path));
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mVideoView.start();
            }
        });

    }
}

