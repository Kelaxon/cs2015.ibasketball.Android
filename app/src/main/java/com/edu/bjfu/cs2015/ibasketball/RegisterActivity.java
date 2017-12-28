package com.edu.bjfu.cs2015.ibasketball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import JDBC.Beans.Userinfo;

public class RegisterActivity extends AppCompatActivity {
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.tv_register_name);
        mPasswordView = (EditText) findViewById(R.id.tv_register_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });

        mRegisterButton = (Button) findViewById(R.id.b_register);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });


    }

    private void attemptRegister() {
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

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

            Userinfo user = new Userinfo();
            user.setUserName(username);
            user.setUserPassword(password);


            // TODO isExists的参数应该只是username
//            if(Userinfo.isExists(username)){
//                Toast.makeText(RegisterActivity.this, "oh, 该用户名已经被使用", Toast.LENGTH_SHORT).show();
//                return;
//            }

            // TODO implement 1.Boolean Userinfo.addUser() method, 注册成功，把用户对象赋值给当前用户 Userinfo.getCurrentUser()
            //if(Userinfo.addUser(user)){
            //  注册成功，把用户对象赋值给当前用户 Userinfo.getCurrentUser()
            //  startActivity(new Intent(RegisterActivity.this, ActivityMain.class));
            //  RegisterActivity.this.finish();
            //  return;
            // }

            Toast.makeText(RegisterActivity.this, "未知错误", Toast.LENGTH_SHORT).show();

        }
    }

    private boolean isusernameValid(String username) {
        //TODO: Replace this with your own logic
        return username.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}

