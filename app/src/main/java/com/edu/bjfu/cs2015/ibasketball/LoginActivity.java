package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.bjfu.cs2015.ibasketball.UI.FullScreenVideoView;
import com.edu.bjfu.cs2015.ibasketball.tool.HttpConnection;
import com.edu.bjfu.cs2015.ibasketball.tool.JsonToInstance;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import INTERFACE.Action;
import JSONPO.CurrentUser;
import JSONPO.Userinfo;
import JSONPO.UserinfoMessage;
import mehdi.sakout.fancybuttons.FancyButton;

import Action.ActionLogin;
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
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.tv_username || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // 登陆按钮的listener
        mLoginButton = (FancyButton) findViewById(R.id.b_login);
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                Intent intent = new Intent(LoginActivity.this, TestActivity.class);
                startActivity(intent);
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


    private void attemptLogin() throws InterruptedException {
        mUsernameView.setError(null);
        mPasswordView.setError(null);

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
            final Userinfo[] userinfo = {null};
            String infoMessage = "";

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpConnection.setMap(map);

                    // TODO 用依赖注入实现不同方法的excecute(命令模式) modify by molinli
                    Action userLoginAction = new ActionLogin();

                    //获取到当前contenxt
                    userLoginAction.setContext(getApplicationContext());
                    //你要在这里设置url
                    userLoginAction.setUrl("");
                    //请你指定http请求参数
                    Map mapInfo=new HashMap();
                    mapInfo.put("key","value");
                    //申请http
                    HttpConnection.execute(userLoginAction,mapInfo);
                    //获取响应 json文件
                    String reponse = HttpConnection.getResponse();
                    //请问你要get什么？
                   //infoMessage = HttpConnection.get ??;

                    // TODO 获得用户对象 modify by molinli
                    if (reponse != null) {
                        Type type = new TypeToken<Userinfo>() {
                        }.getType();

                        JsonToInstance<Userinfo> jsonToInstance = new JsonToInstance();
                        //get类型
                        Type typeForParam=new TypeToken<UserinfoMessage>(){}.getType();
                        //
                        Userinfo userinfoGet=jsonToInstance.ToInstance(reponse,typeForParam);

                       //userinfo[0] = jsonToInstance.JsonToInstance ????
                    }

                }
            });
            t1.start();
            t1.join();


            // 有错情况
            if (userinfo[0] == null) {
                Toast.makeText(LoginActivity.this, infoMessage, Toast.LENGTH_SHORT).show();

            } else {
                // 无错情况
                CurrentUser.setCurrentUser(userinfo[0]);
                LoginActivity.this.finish();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }
    }
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


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

