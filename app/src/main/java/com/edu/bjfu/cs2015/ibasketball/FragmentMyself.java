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

import JSONPO.Userinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class FragmentMyself extends Fragment implements View.OnClickListener {
    @Nullable
    private Userinfo mUser;
    private LinearLayout mButtonMessage;
    private LinearLayout mButtonCollection;
    //private LinearLayout mButtonUpdateInfo;
    private ImageView mButtonLogout;
    private ImageView mUserAvatorView;
    private TextView mUsernameView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myself, container, false);

        // TODO 1.赋值 2.ClickListener
        // mUserAvator = .. mUsername= ..
        // mUser = Userinfo.getCurrentUser();


        mButtonMessage = (LinearLayout) view.findViewById(R.id.b_user_message);
        mButtonCollection = (LinearLayout) view.findViewById(R.id.b_user_collection);
        //mButtonUpdateInfo = (LinearLayout) view.findViewById(R.id.b_user_update_info);
        mButtonLogout = (ImageView) view.findViewById(R.id.iv_logout);

        mUserAvatorView = (ImageView) view.findViewById(R.id.iv_user_avatar);
        mUsernameView = (TextView) view.findViewById(R.id.tv_user_name);


        mButtonMessage.setOnClickListener(this);
        mButtonCollection.setOnClickListener(this);
       // mButtonUpdateInfo.setOnClickListener(this);
        mButtonLogout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        MainActivity mainActivity = (MainActivity) getActivity();
        switch (v.getId()) {
            case R.id.iv_logout:

                //TODO Userinfo.changeCurrentUser(null);
                mainActivity.finish();
                startActivity(new Intent(mainActivity, LoginActivity.class));
                break;
                //TODO :
//            case R.id.b_user_message:
//                Intent intent1 = new Intent(mainActivity, UserMessageList.class);
//                startActivity(intent1);
//            case R.id.b_user_collection:
//                startActivity(new Intent(mainActivity, UserCollectionListActivity.class));
//                break;
//            case R.id.b_user_update_info:
//                Intent intent2 = new Intent(mainActivity, UserInfoUpdateActivity.class);
//                startActivity(intent2);
//                break;
            default:
                break;
        }

    }

}
