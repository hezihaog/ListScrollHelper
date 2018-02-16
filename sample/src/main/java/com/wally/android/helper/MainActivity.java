package com.wally.android.helper;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.wally.android.helper.activity.GridViewSampleActivity;
import com.wally.android.helper.activity.ListViewSampleActivity;
import com.wally.android.helper.activity.NestedScrollInnerRecyclerViewActivity;
import com.wally.android.helper.activity.NestedScrollViewSampleActivity;
import com.wally.android.helper.activity.RecyclerViewSampleActivity;
import com.wally.android.helper.activity.ScrollViewSampleActivity;

import java.util.ArrayList;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {
    private ArrayList<SampleInfo> mSampleInfoMap = new ArrayList<SampleInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
        String[] arr = new String[mSampleInfoMap.size()];
        for (int i = 0; i < mSampleInfoMap.size(); i++) {
            arr[i] = mSampleInfoMap.get(i).getTitle();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, arr);
        //设置该窗口显示列表
        setListAdapter(arrayAdapter);
        //设置点击事件
        getListView().setOnItemClickListener(this);
    }

    private void setup() {
        mSampleInfoMap.add(new SampleInfo("ListView", ListViewSampleActivity.class));
        mSampleInfoMap.add(new SampleInfo("GridView", GridViewSampleActivity.class));
        mSampleInfoMap.add(new SampleInfo("ScrollView", ScrollViewSampleActivity.class));
        mSampleInfoMap.add(new SampleInfo("RecyclerView", RecyclerViewSampleActivity.class));
        mSampleInfoMap.add(new SampleInfo("NestedScrollView", NestedScrollViewSampleActivity.class));
        mSampleInfoMap.add(new SampleInfo("NestedScrollView嵌套RecyclerView", NestedScrollInnerRecyclerViewActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SampleInfo info = mSampleInfoMap.get(position);
        startActivity(new Intent(this, info.getActivityClass()));
    }

    private static class SampleInfo {
        private String title;
        private Class activityClass;

        public SampleInfo(String title, Class activityClass) {
            this.title = title;
            this.activityClass = activityClass;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Class getActivityClass() {
            return activityClass;
        }

        public void setActivityClass(Class activityClass) {
            this.activityClass = activityClass;
        }
    }
}