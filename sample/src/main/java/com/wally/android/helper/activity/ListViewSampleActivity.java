package com.wally.android.helper.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wally.android.helper.R;
import com.wally.android.helper.base.BaseSampleActivity;
import com.wally.android.helper.util.DataSourseHelper;
import com.wally.android.scroll.helper.ListScrollHelper;
import com.wally.android.scroll.helper.ScrollableListView;
import com.wally.android.scroll.helper.wrapper.ScrollableListViewWrapper;

import java.util.ArrayList;

public class ListViewSampleActivity extends BaseSampleActivity {
    private ArrayList<String> mDataList;
    private ListView mListView;

    @Override
    protected void onFindView() {
        mListView = findViewById(R.id.listView);
    }

    @Override
    protected void onBindContent() {
        super.onBindContent();
        mDataList = DataSourseHelper.loadData(15);
        mListView.setAdapter(new Adapter());
    }

    @Override
    protected String onGetToolBarTitle() {
        return "ListViewSample";
    }

    @Override
    protected int onLayoutId() {
        return R.layout.activity_list_view_sample;
    }

    @Override
    protected ListScrollHelper onGetListScrollHelper() {
        return new ListScrollHelper(new ScrollableListViewWrapper((ScrollableListView) mListView));
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
                convertView = LayoutInflater.from(ListViewSampleActivity.this).inflate(R.layout.item_list_view_sample, null);
                ViewHolder holder = new ViewHolder();
                holder.mTextView = convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mTextView.setText(mDataList.get(position));
            return convertView;
        }

        private class ViewHolder {
            private TextView mTextView;
        }
    }
}