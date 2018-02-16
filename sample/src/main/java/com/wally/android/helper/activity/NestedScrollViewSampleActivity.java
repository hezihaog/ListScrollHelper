package com.wally.android.helper.activity;

import android.support.v4.widget.NestedScrollView;

import com.wally.android.helper.R;
import com.wally.android.helper.base.BaseSampleActivity;
import com.wally.android.scroll.helper.ListScrollHelper;
import com.wally.android.scroll.helper.ScrollableNestedScrollView;
import com.wally.android.scroll.helper.wrapper.ScrollableNestedScrollViewWrapper;

public class NestedScrollViewSampleActivity extends BaseSampleActivity {
    private NestedScrollView mNestedScrollView;

    @Override
    protected String onGetToolBarTitle() {
        return "NestedScrollViewSample";
    }

    @Override
    protected void onFindView() {
        mNestedScrollView = findViewById(R.id.nestedScrollView);
    }

    @Override
    protected int onLayoutId() {
        return R.layout.activity_nested_scroll_view_sample;
    }

    @Override
    protected ListScrollHelper onGetListScrollHelper() {
        return new ListScrollHelper(new ScrollableNestedScrollViewWrapper((ScrollableNestedScrollView) mNestedScrollView));
    }
}