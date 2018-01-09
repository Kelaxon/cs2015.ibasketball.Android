package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import java.util.Map;

import JDBC.Beans.Userinfo;
import mehdi.sakout.fancybuttons.FancyButton;
import mapper.*;
import mapper.mapperImpl.*;

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

        // TODO: 实现Userinfo.getCurrentUser()
         /*----------------------modify by 莫林立---------------------------*/
        // 如果用户已经登陆：
        if (Userinfo.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            LoginActivity.this.finish();
        }
         /*----------------------modify by 莫林立end--------------------------*/

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
//                attemptLogin();

                Intent intent=new Intent(LoginActivity.this,TestActivity.class);

                startActivity(intent);
            }
        });


        // 注册按钮的Listener
        mRegisterButton = (FancyButton) findViewById(R.id.b_register);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                LoginActivity.this.finish();
            }
        });

        mBrowerButton = (TextView) findViewById(R.id.b_browser);
        mBrowerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO currentUser 改成游客
                //游客是什么身份？
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                LoginActivity.this.finish();
            }
        });

    }


    private void attemptLogin() {
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        final String username = mUsernameView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
<<<<<<< Updated upstream
=======

            // TODO post username, password
            // TODO response 封装成 userinfo

            new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO 1. 判断用户名是否存在，否则返回错误信息 2. 判断用户密码是否正确，否则返回错误信息 3. 都正确，intent跳转


            /*----------------------modify by 莫林立---------------------------*/
                    UserinfoMapperImpl userinfoMapperImpl=new UserinfoMapperImpl();
                    //获取用户登录状态，map.get(key)==0（登录成功）；1（密码错误）；2（不存在该用户）

                    Map<String, Integer>map = new Map();
                   1 = new Thread(){
                        public void run() {
                            map[0] = userinfoMapperImpl.userLogin(username,password);
                        }
                    };
>>>>>>> Stashed changes

            // TODO 1. 判断用户名是否存在，否则返回错误信息 2. 判断用户密码是否正确，否则返回错误信息 3. 都正确，intent跳转
            /*----------------------modify by 莫林立---------------------------*/
            UserinfoMapperImpl userinfoMapperImpl=new UserinfoMapperImpl();
            //获取用户登录状态，map.get(key)==0（登录成功）；1（密码错误）；2（不存在该用户）
            Map<String,Integer> map=userinfoMapperImpl.userLogin(username,password);

            String errorMessage = "";
            if (!Userinfo.isExists(username, password)) {

                errorMessage = "用户名不存在";

                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                return;
            }

            // 2.密码错误
            if(map.get("status").equals(1)) {
                errorMessage="密码错误";
             }

            // 3.登录成功
            if(map.get("status").equals(2)){

                LoginActivity.this.finish();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
            /*----------------------modify by 莫林立 end--------------------------*/

        }
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


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

