package com.wally.android.helper.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.wally.android.helper.R;
import com.wally.android.helper.base.BaseSampleActivity;
import com.wally.android.helper.util.DataSourseHelper;
import com.wally.android.scroll.helper.ListScrollHelper;
import com.wally.android.scroll.helper.ScrollableGridView;
import com.wally.android.scroll.helper.wrapper.ScrollableGridViewWrapper;

import java.util.ArrayList;

public class GridViewSampleActivity extends BaseSampleActivity {
    private GridView mGridView;
    private ArrayList<String> mDataList;

    @Override
    protected int onLayoutId() {
        return R.layout.activity_grid_view_sample;
    }

    @Override
    protected void onFindView() {
        mGridView = findViewById(R.id.gridView);
    }

    @Override
    protected void onBindContent() {
        super.onBindContent();
        mDataList = DataSourseHelper.loadData(30);
        mGridView.setNumColumns(2);
        mGridView.setAdapter(new Adapter());
    }

    @Override
    protected ListScrollHelper onGetListScrollHelper() {
        return new ListScrollHelper(new ScrollableGridViewWrapper((ScrollableGridView) mGridView));
    }

    private class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(GridViewSampleActivity.this).inflate(R.layout.item_grid_view_sample, null);
                GridViewSampleActivity.Adapter.ViewHolder holder = new GridViewSampleActivity.Adapter.ViewHolder();
                holder.mTextView = convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            }
            GridViewSampleActivity.Adapter.ViewHolder holder = (GridViewSampleActivity.Adapter.ViewHolder) convertView.getTag();
            holder.mTextView.setText(mDataList.get(position));
            return convertView;
        }

        private class ViewHolder {
            private TextView mTextView;
        }
    }
}