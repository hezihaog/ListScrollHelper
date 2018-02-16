package com.wally.android.helper.activity;

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
import com.wally.android.scroll.helper.ScrollableRecyclerView;
import com.wally.android.scroll.helper.wrapper.ScrollableRecyclerViewWrapper;

import java.util.ArrayList;

public class RecyclerViewSampleActivity extends BaseSampleActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<String> mDataList;

    @Override
    protected int onLayoutId() {
        return R.layout.activity_recycler_view_sample;
    }

    @Override
    protected void onFindView() {
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void onBindContent() {
        super.onBindContent();
        mDataList = DataSourseHelper.loadData(15);
        mRecyclerView.setAdapter(new Adapter());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected ListScrollHelper onGetListScrollHelper() {
        return new ListScrollHelper(new ScrollableRecyclerViewWrapper((ScrollableRecyclerView) mRecyclerView));
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(RecyclerViewSampleActivity.this).inflate(R.layout.item_recycler_view_sample, null));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
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