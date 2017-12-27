package com.edu.bjfu.cs2015.ibasketball;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ChrisYoung on 2017/12/27.
 */

public class FragmentNews extends Fragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] names = {"城市公园", "风景名胜", "森林公园", "湿地公园", "郊野公园", "主题公园"};
    private String[] pics = {"p9", "p10", "p11", "p12", "p13", "p14"};
    private String[] words = {"城市中心亦是风景",
            "周末，挑个山清水秀的地方野餐", "城市中，来一次森呼吸","在这里和野生动物亲密接触","远离喧嚣，偷得浮生半日闲","清晨，还穿着睡衣的你已神游公园"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);


//        MainActivity mActivityMain = (ActivityMain) getActivity();

//RecyclerView的初始化
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        //创建现行LinearLayoutManager
        //mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL); //瀑布流

        //设置LayoutMananger
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置item的动画，可以不设置
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        MyAdapter adapter = new MyAdapter(getActivity(), names, pics, words);  //在Fragment中，this要替换成getActivitiy

        //设置Adapter
        mRecyclerView.setAdapter(adapter);

        return view;
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        String[] mName;
        String[] mPics;
        String[] mWords;
        Context mContext;
        View.OnClickListener mOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = mRecyclerView.getChildLayoutPosition(v);
                        switch(p){
            case 0:
                ((ActivityMain) getActivity()).setCourseType("chengshi");
//                ((ActivityMain) getActivity()).replaceFragment(new FragmentClassList());
                ((ActivityMain) getActivity()).showFragment(ActivityMain.FRAGMENT_CLASSLIST1);
                break;
            case 1:
                ((ActivityMain) getActivity()).setCourseType("fengjing");
//                ((ActivityMain) getActivity()).replaceFragment(new FragmentClassList());
                ((ActivityMain) getActivity()).showFragment(ActivityMain.FRAGMENT_CLASSLIST2);
                break;
            case 2:
                ((ActivityMain) getActivity()).setCourseType("senlin");
//                ((ActivityMain) getActivity()).replaceFragment(new FragmentClassList());
                ((ActivityMain) getActivity()).showFragment(ActivityMain.FRAGMENT_CLASSLIST3);
                break;
            case 3:
                ((ActivityMain) getActivity()).setCourseType("shidi");
//                ((ActivityMain) getActivity()).replaceFragment(new FragmentClassList());
                ((ActivityMain) getActivity()).showFragment(ActivityMain.FRAGMENT_CLASSLIST4);
                break;
            case 4:
                ((ActivityMain) getActivity()).setCourseType("jiaoye");
//                ((ActivityMain) getActivity()).replaceFragment(new FragmentClassList());
                ((ActivityMain) getActivity()).showFragment(ActivityMain.FRAGMENT_CLASSLIST5);
                break;
            case 5:
                ((ActivityMain) getActivity()).setCourseType("zhuti");
//                ((ActivityMain) getActivity()).replaceFragment(new FragmentClassList());
                ((ActivityMain) getActivity()).showFragment(ActivityMain.FRAGMENT_CLASSLIST6);
                break;
            default:
                break;
            }
        }};

        public MyAdapter(Context context, String[] name, String[] pics, String[] words) {
            mContext = context;
            mName = name;
            mPics = pics;
            mWords = words;


        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // 给ViewHolder设置布局文件
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
            v.setOnClickListener(mOnclickListener);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int i) {
            // 给ViewHolder设置元素

            holder.mTextView.setText(mName[i]);
            // 由图片名字获得图片
            holder.mImageView.setImageDrawable(mContext.getDrawable(getResources().getIdentifier(mPics[i], "drawable", mContext.getPackageName())));
            holder.mWordView.setText(mWords[i]);
        }

        @Override
        public int getItemCount() {
            return 6;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ImageView mImageView;
            public TextView mWordView;

            public ViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.name);
                mImageView = (ImageView) itemView.findViewById(R.id.pic);
                mWordView = (TextView) itemView.findViewById(R.id.word);
            }
        }
    }


    @Override
    public void onClick(View v) {
        ActivityMain mActivityMain = (ActivityMain) getActivity();
//        switch(v.getId()){
//            case R.id.chengshi:
//                mActivityMain.setCourseType("chengshi");
//                mActivityMain.replaceFragment(new FragmentClassList());
//                break;
//            case R.id.fengjing:
//                mActivityMain.setCourseType("fengjing");
//                mActivityMain.replaceFragment(new FragmentClassList());
//                break;
//            case R.id.senlin:
//                mActivityMain.setCourseType("senlin");
//                mActivityMain.replaceFragment(new FragmentClassList());
//                break;
//            case R.id.shidi:
//                mActivityMain.setCourseType("shidi");
//                mActivityMain.replaceFragment(new FragmentClassList());
//                break;
//            case R.id.jiaoye:
//                mActivityMain.setCourseType("jiaoye");
//                mActivityMain.replaceFragment(new FragmentClassList());
//                break;
//            case R.id.zhuti:
//                mActivityMain.setCourseType("zhuti");
//                mActivityMain.replaceFragment(new FragmentClassList());
//                break;
//            default:
//                break;
//        }
    }
}




