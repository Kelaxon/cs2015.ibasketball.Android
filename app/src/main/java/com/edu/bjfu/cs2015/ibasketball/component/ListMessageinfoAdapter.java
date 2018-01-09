package com.edu.bjfu.cs2015.ibasketball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.bjfu.cs2015.ibasketball.R;

import java.util.List;

import JDBC.Beans.Usermessagenews;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class ListMessageinfoAdapter extends RecyclerView.Adapter<ListMessageinfoAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<Usermessagenews> mMessageList;

    public ListMessageinfoAdapter(List<Usermessagenews> list, Context context){
        this.mMessageList = list;
        this.mContext = context;
    }

    // Innerclass ViewHolder 用来操控UI控件
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mUserAvator;
        private TextView mUserName;
        private TextView mMessageTime;
        private TextView mMessageContent;

        public ViewHolder(View itemView){
            super(itemView);
            mUserAvator = (ImageView) itemView.findViewById(R.id.iv_user_avatar);
            mUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
            mMessageTime = (TextView) itemView.findViewById(R.id.tv_message_time);
            mMessageContent = (TextView) itemView.findViewById(R.id.tv_message_content);
        }
    }

    //定义点击item接口
    public static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, Usermessagenews bean);
    }

    //添加接口
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    //设置Adapter接口的方法
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener){
        this.mOnRecyclerViewItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if(mOnRecyclerViewItemClickListener != null){
            mOnRecyclerViewItemClickListener.onItemClick(v, (Usermessagenews)v.getTag());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false);
        ViewHolder holder = new ViewHolder(view);

        //为创建的每个view注册点击事件
        view.setOnClickListener(this);
        return holder;
    }


    // 为每一个view
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // TODO  holder的各种view要通过JDBC操作来set
//        holder.mNewsShortContent.setText((CharSequence) mMessageList.get(position).get("pname"));
//        Picasso.with( mContext).load(mMessageList.get(position).getAVFile("TitleImage") == null ? "NO PICTURE" : mMessageList.get(position).getAVFile("TitleImage").getUrl()).transform(new RoundedTransformation(9,0)).into(holder.mNewsPic);
//        holder.itemView.setTag(mMessageList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

}
