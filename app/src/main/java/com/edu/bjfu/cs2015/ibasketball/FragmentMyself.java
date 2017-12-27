package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;

import cn.leancloud.leanstoragegettingstarted.ui_wode.base.StatusListActivity;
import cn.leancloud.leanstoragegettingstarted.ui_wode.base.UserListActivity;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class FragmentMyself extends Fragment implements View.OnClickListener{
    @Nullable
    private TextView currentUser;
    private AVUser user;
    private static final int IMAGE_PICK_REQUEST = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wode,container,false);


        LinearLayout button1 = (LinearLayout)view.findViewById(R.id.button_moments_wode);
        LinearLayout button2 = (LinearLayout)view.findViewById(R.id.button_find_wode);
        LinearLayout button3 = (LinearLayout)view.findViewById(R.id.button_other_wode);
        FancyButton button4 = (FancyButton)view.findViewById(R.id.button_logout);


        //用户名
        TextView mUsername  = (TextView) view.findViewById(R.id.username);
        String username = AVUser.getCurrentUser().getUsername();
        mUsername.setText(username);

        //头像
        ImageView mAvator = (ImageView) view.findViewById(R.id.avatarView);
        StatusUtils.displayAvatar(AVUser.getCurrentUser(), mAvator);


        if (button1==null)
            Log.e("wyy","1");
        if (button2==null)
            Log.e("wyy","2");
        if (button2==null)
            Log.e("wyy","3");
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);



        return view;


    }

    @Override
    public void onClick(View v) {
        ActivityMain mActivityMain = (ActivityMain) getActivity();
        switch(v.getId()){
            case R.id.button_logout:
                AVUser.changeCurrentUser(null,true);
                mActivityMain.finish();
                startActivity(new Intent(mActivityMain, LoginActivity.class));
                break;
            case R.id.button_moments_wode:
                Intent intent = new Intent(mActivityMain, StatusListActivity.class);
                startActivity(intent);
                break;
            case R.id.button_find_wode:
                Intent intent1 = new Intent(mActivityMain, UserListActivity.class);
                startActivity(intent1);
                break;
            case R.id.button_other_wode:
                Intent intent2 = new Intent(mActivityMain, List1Activity.class);
                startActivity(intent2);
            default:
                break;
        }

    }

}
