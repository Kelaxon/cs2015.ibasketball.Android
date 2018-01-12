package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.bjfu.cs2015.ibasketball.tool.LoadImagesTask;

import JSONPO.CurrentUser;
import JSONPO.Userinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class FragmentMyself extends Fragment implements View.OnClickListener {
    @Nullable
    private Userinfo mUser;
    private Button mButtonMessage;
    private Button mButtonCollection;
    private ImageView mButtonUpdateInfo;
    private ImageView mButtonLogout;
    private ImageView mUserAvatorView;
    private TextView mUsernameView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myself, container, false);

        // TODO 1.赋值 2.ClickListener
        //获取到当前用户信息
        mUser = CurrentUser.getcurrentUser();

        mButtonMessage = (Button) view.findViewById(R.id.b_user_message);
        mButtonCollection = (Button) view.findViewById(R.id.b_user_collection);
        //get 到当前的各种view
        mButtonUpdateInfo = (ImageView) view.findViewById(R.id.iv_user_avatar);
        mButtonLogout = (ImageView) view.findViewById(R.id.iv_logout);
        mUserAvatorView = (ImageView) view.findViewById(R.id.iv_user_avatar);
        mUsernameView = (TextView) view.findViewById(R.id.tv_user_name);
        //设置监听
        mButtonMessage.setOnClickListener(this);
        mButtonCollection.setOnClickListener(this);
        mButtonUpdateInfo.setOnClickListener(this);
        mButtonLogout.setOnClickListener(this);

        // 加载头像
        if(mUser!=null)
        {   Log.e("TagUsername", mUser.getUserName());
            new LoadImagesTask(mUserAvatorView).execute(mUser.getUserAvatar());
            mUsernameView.setText(mUser.getUserName());
        }
        Log.e("userIsNull?", mUser==null?true+"":false+"");
        // TODO 加载其他信息
        // ...


        return view;
    }

    @Override
    public void onClick(View v) {
        MainActivity mainActivity = (MainActivity) getActivity();
        switch (v.getId()) {

            // 退出登录
            case R.id.iv_logout:
                // 退出登录时set null
                CurrentUser.setCurrentUser(null);
                mainActivity.finish();
                startActivity(new Intent(mainActivity, LoginActivity.class));
                break;
                //TODO :

            // 查看我的所有留言
//            case R.id.b_user_message:
//                Intent intent1 = new Intent(mainActivity, UserMessageList.class);
//                startActivity(intent1);

            // 查看我的全部收藏
//            case R.id.b_user_collection:
//                startActivity(new Intent(mainActivity, UserCollectionListActivity.class));
//                break;

            // 更改用户信息
//            case R.id.b_user_update_info:
//                Intent intent2 = new Intent(mainActivity, UserInfoUpdateActivity.class);
//                startActivity(intent2);
//                break;
            default:
                break;
        }

    }

}
