package com.wally.android.scroll.helper.wrapper;

import android.support.v4.widget.NestedScrollView;

import com.wally.android.scroll.helper.ScrollableNestedScrollView;
import com.wally.android.scroll.helper.base.AbsScrollableViewWrapper;


/**
 * Package: oms.mmc.android.fast.framwork.base
 * FileName: NestedScrollViewScrollableViewWrapper
 * Date: on 2018/2/9  下午4:43
 * Auther: zihe
 * Descirbe:NestedScrollView的滚动包裹类
 * Email: hezihao@linghit.com
 */

public class ScrollableNestedScrollViewWrapper extends AbsScrollableViewWrapper<ScrollableNestedScrollView> {
    public ScrollableNestedScrollViewWrapper(ScrollableNestedScrollView scrollingView) {
        super(scrollingView);
    }

    @Override
    public void setup(final ScrollDelegate delegate, ScrollableNestedScrollView scrollableView) {
        scrollableView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (delegate != null) {
                    //向下滑动
                    if (scrollY > oldScrollY) {
                        delegate.onScrolledToUp();
                    }
                    //向上滑动
                    if (scrollY < oldScrollY) {
                        delegate.onScrolledToDown();
                    }
                    //滑动到了顶部
                    if (scrollY == 0) {
                        delegate.onScrolledToTop();
                    }
                    //滑动到了底部
                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                        delegate.onScrolledToBottom();
                    }
                }
            }
        });
    }

    @Override
    public void moveToTop() {
        NestedScrollView nestedScrollView = getScrollableView();
        if (nestedScrollView != null) {
            nestedScrollView.scrollTo(0, 0);
        }
    }

    @Override
    public void smoothMoveToTop() {
        NestedScrollView nestedScrollView = getScrollableView();
        if (nestedScrollView != null) {
            nestedScrollView.smoothScrollTo(0, 0);
        }
    }
}
