package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private LinearLayout mButtonMessage;
    private LinearLayout mButtonCollection;
    private ImageView mButtonUpdateInfo;
    private ImageView mButtonLogout;
    private ImageView mUserAvatorView;
    private TextView mUsernameView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myself, container, false);

        // TODO 1.赋值 2.ClickListener

        mUser = CurrentUser.getcurrentUser();

        mButtonMessage = (LinearLayout) view.findViewById(R.id.b_user_message);
        mButtonCollection = (LinearLayout) view.findViewById(R.id.b_user_collection);
        mButtonUpdateInfo = (ImageView) view.findViewById(R.id.iv_user_avatar);
        mButtonLogout = (ImageView) view.findViewById(R.id.iv_logout);
        mButtonMessage.setOnClickListener(this);
        mButtonCollection.setOnClickListener(this);
        mButtonUpdateInfo.setOnClickListener(this);
        mButtonLogout.setOnClickListener(this);

        mUserAvatorView = (ImageView) view.findViewById(R.id.iv_user_avatar);
        mUsernameView = (TextView) view.findViewById(R.id.tv_user_name);

        // 加载头像
        new LoadImagesTask(mUserAvatorView).execute(mUser.getUserAddr());
        mUsernameView.setText(mUser.getUserName());
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
