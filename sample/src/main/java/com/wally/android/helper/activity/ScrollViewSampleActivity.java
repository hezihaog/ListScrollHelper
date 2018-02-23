package com.wally.android.helper.activity;

import android.widget.ScrollView;

import com.wally.android.helper.R;
import com.wally.android.helper.base.BaseSampleActivity;
import com.wally.android.scroll.helper.ListScrollHelper;
import com.wally.android.scroll.helper.widget.ScrollableScrollView;
import com.wally.android.scroll.helper.wrapper.ScrollableScrollViewWrapper;

public class ScrollViewSampleActivity extends BaseSampleActivity {
    private ScrollView mScrollView;

    @Override
    protected String onGetToolBarTitle() {
        return "ScrollViewSample";
    }

    @Override
    protected int onLayoutId() {
        return R.layout.activity_scroll_view_sample;
    }

    @Override
    protected void onFindView() {
        mScrollView = findViewById(R.id.scrollView);
    }

    @Override
    protected ListScrollHelper onGetListScrollHelper() {
        return new ListScrollHelper(new ScrollableScrollViewWrapper((ScrollableScrollView) mScrollView));
    }
}
