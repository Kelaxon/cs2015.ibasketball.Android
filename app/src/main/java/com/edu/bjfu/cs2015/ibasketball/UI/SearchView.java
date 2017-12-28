package com.edu.bjfu.cs2015.ibasketball.UI;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.bjfu.cs2015.ibasketball.R;

/**
 * Created by ChrisYoung on 2017/12/27.
 */

public class SearchView extends LinearLayout implements View.OnClickListener {

    private EditText etInput;       //输入框
    //private ImageView ivDelete;     //清空
    //private TextView tvCancel;      //取消
    private Context mContext;       //上下文
    private ListView mListView;     //提示列表

    private ArrayAdapter<String> mAutoCompleteAdapter;      //自动补全Adapter

    private SearchViewListener mListener;       //搜索回调接口

    public void setSearchViewListener(SearchViewListener listener){
        mListener = listener;
    }


    public SearchView(Context context, AttributeSet attrs){
        super(context, attrs);
        mContext = context;
        //LayoutInflater.from(context).inflate(R.layout.view_search, this);
        LayoutInflater.from(context).inflate(R.layout.item_search, this);

        initViews();
    }

    private void initViews(){
        etInput = (EditText) findViewById(R.id.edittext_input_search);
        //ivDelete = (ImageView) findViewById(R.id.image_delete_search);
        //tvCancel = (TextView) findViewById(R.id.textview_cancel_search);
        mListView = (ListView) findViewById(R.id.listview_tips_search);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                AVObject hint = (AVObject) mListView.getAdapter().getItem(position);
//                String text = hint.getString("pname");
//                etInput.setText(text);
//                etInput.setSelection(text.length());
//
//                mListView.setVisibility(View.GONE);
//                notifyStartSearching(text);
            }
        });

        //ivDelete.setOnClickListener(this);
        //tvCancel.setOnClickListener(this);

        etInput.addTextChangedListener(new EditChangedListener());
        etInput.setOnClickListener(this);
        etInput.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    mListView.setVisibility(View.GONE);
                    notifyStartSearching(etInput.getText().toString());
                }
                return true;
            }
        });
    }

    //监听搜索，开始搜索
    // @param text
    private void notifyStartSearching(String text){
        if (mListener != null){
            mListener.onSearch(text);
        }
        //隐藏软键盘
        InputMethodManager inputMethodManager = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //设置推荐Adapter
//    public void setmTipsHintAdapter(AdapterHintList adapter) {
//        mTipsHintAdapter = adapter;
//        if (mListView.getAdapter() == null){
//            mListView.setAdapter(mTipsHintAdapter);
//        }
//    }

    //设置自动补全Adapter
    public void setmAutoCompleteAdapter(ArrayAdapter<String> adapter) {
        mAutoCompleteAdapter = adapter;
    }


    private class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!"".equals(s.toString())){
                //ivDelete.setVisibility(VISIBLE);
                mListView.setVisibility(VISIBLE);
                if (mAutoCompleteAdapter != null && mListView.getAdapter() != mAutoCompleteAdapter) {
                    mListView.setAdapter(mAutoCompleteAdapter);
                }
                //更新autocomplete数据
                if (mListener != null){
                    mListener.onRefreshAutoComplete(s + "");
                }
            } else {
                //ivDelete.setVisibility(GONE);
//                if (mTipsHintAdapter != null){
//                    mListView.setAdapter(mTipsHintAdapter);
//                }
                mListView.setVisibility(GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edittext_input_search:
                mListView.setVisibility(VISIBLE);
                break;
//            case R.id.image_delete_search:
//                etInput.setText("");
//                //ivDelete.setVisibility(GONE);
//                break;
//            case R.id.textview_cancel_search:
//                mListView.setVisibility(GONE);
//                etInput.setText("");
                //ivDelete.setVisibility(GONE);

                //隐藏软键盘
//                InputMethodManager inputMethodManager = (InputMethodManager) mContext
//                        .getSystemService(Context.INPUT_METHOD_SERVICE);
//                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                break;
            default:
                break;
        }
    }

    public interface SearchViewListener {
        //更新自动补全内容
        //@param text 传入补全后的文本
        void onRefreshAutoComplete(String text);

        //开始搜索
        //@param text 传入输入框的文本
        void onSearch(String text);

        //提示列表项被点击时回调方法（提示／补全）
        void onListItemClick(String text);
    }
}
