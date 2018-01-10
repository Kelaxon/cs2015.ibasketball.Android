package com.edu.bjfu.cs2015.ibasketball.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.bjfu.cs2015.ibasketball.R;

import java.util.List;

import JSONPO.Gameinfo;
import JSONPO.Newsinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class ListGamesAdapter extends RecyclerView.Adapter<ListGamesAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<Gameinfo> mGameinfoList;

    public ListGamesAdapter(List<Gameinfo> list, Context context) {
        this.mGameinfoList = list;
        this.mContext = context;
    }

    // Innerclass ViewHolder 用来操控UI控件
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mGameTime;
        private ImageView mTeam1Logo;
        private ImageView mTeam2Logo;
        private TextView mTeam1Name;
        private TextView mTeam2Name;
        private TextView mTeam1result;
        private TextView mTeam2result;

        public ViewHolder(View itemView) {
            super(itemView);
            mGameTime = (TextView) itemView.findViewById(R.id.tv_game_time);
            mTeam1Logo = (ImageView) itemView.findViewById(R.id.iv_team1_logo);
            mTeam2Logo = (ImageView) itemView.findViewById(R.id.iv_team2_logo);
            mTeam1Name = (TextView) itemView.findViewById(R.id.tv_team1_name);
            mTeam2Name = (TextView) itemView.findViewById(R.id.tv_team2_name);
            mTeam1result = (TextView) itemView.findViewById(R.id.tv_team1_score);
            mTeam2result = (TextView) itemView.findViewById(R.id.tv_team2_score);
        }
    }

    //定义点击item接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Newsinfo bean);
    }

    //添加接口
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    //设置Adapter接口的方法
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnRecyclerViewItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnRecyclerViewItemClickListener != null) {
            mOnRecyclerViewItemClickListener.onItemClick(v, (Newsinfo) v.getTag());
        }
        Log.e("Adapter", v.getId() + "");  // 被点击的控件的id
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_score_board, parent, false);
        ViewHolder holder = new ViewHolder(view);

        //为创建的每个view注册点击事件
        view.setOnClickListener(this);
        return holder;
    }


    // 为每一个view
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // TODO  holder的各种view set
        holder.mGameTime.setText((CharSequence) mGameinfoList.get(position).getGameTime());
        holder.mTeam1Name.setText((CharSequence) mGameinfoList.get(position).getTeaminfoByGameTeam1Id().getTeamName());
        holder.mTeam2Name.setText((CharSequence) mGameinfoList.get(position).getTeaminfoByGameTeam2Id().getTeamName());

//
        //holder.mTeam1result.setText((CharSequence) mGameinfoList.get(position).getGameResult());
        //  holder.mNewsShortContent.setText((CharSequence) mGameinfoList.get(position).get("pname"));
//        Picasso.with( mContext).load(mGameinfoList.get(position).getAVFile("TitleImage") == null ? "NO PICTURE" : mGameinfoList.get(position).getAVFile("TitleImage").getUrl()).transform(new RoundedTransformation(9,0)).into(holder.mNewsPic);
//        holder.itemView.setTag(mGameinfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mGameinfoList.size();
    }

}
