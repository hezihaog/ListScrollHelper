package com.wally.android.helper.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wally.android.helper.R;
import com.wally.android.helper.base.BaseSampleActivity;
import com.wally.android.helper.util.DataSourseHelper;
import com.wally.android.scroll.helper.ListScrollHelper;
import com.wally.android.scroll.helper.widget.ScrollableNestedScrollView;
import com.wally.android.scroll.helper.wrapper.ScrollableNestedScrollViewWrapper;

import java.util.ArrayList;

public class NestedScrollInnerRecyclerViewActivity extends BaseSampleActivity {
    private NestedScrollView mNestedScrollView;
    private RecyclerView mRecyclerView;

    private ArrayList<String> mDataList;

    @Override
    protected String onGetToolBarTitle() {
        return "NestedScroll嵌套RecyclerViewSample";
    }

    @Override
    protected int onLayoutId() {
        return R.layout.activity_nested_scroll_inner_recycler_view;
    }

    @Override
    protected void onFindView() {
        mNestedScrollView = findViewById(R.id.nestedScrollView);
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void onBindContent() {
        super.onBindContent();
        mDataList = DataSourseHelper.loadData(15);
        mRecyclerView.setAdapter(new NestedScrollInnerRecyclerViewActivity.Adapter());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置放弃自身滚动，将滚动交给NestedScrollView，解决滑动粘滞
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected ListScrollHelper onGetListScrollHelper() {
        return new ListScrollHelper(new ScrollableNestedScrollViewWrapper((ScrollableNestedScrollView) mNestedScrollView));
    }

    private class Adapter extends RecyclerView.Adapter<NestedScrollInnerRecyclerViewActivity.ViewHolder> {

        @Override
        public NestedScrollInnerRecyclerViewActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NestedScrollInnerRecyclerViewActivity.ViewHolder(LayoutInflater.from(NestedScrollInnerRecyclerViewActivity.this).inflate(R.layout.item_recycler_view_sample, null));
        }

        @Override
        public void onBindViewHolder(NestedScrollInnerRecyclerViewActivity.ViewHolder holder, int position) {
            holder.mTextView.setText(mDataList.get(position));
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
        }
    }
}
