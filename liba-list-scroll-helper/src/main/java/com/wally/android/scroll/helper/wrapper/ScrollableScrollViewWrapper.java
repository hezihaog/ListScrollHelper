package com.wally.android.scroll.helper.wrapper;


import com.wally.android.scroll.helper.widget.ScrollableScrollView;
import com.wally.android.scroll.helper.base.AbsScrollableViewWrapper;

/**
 * Package: oms.mmc.android.fast.framwork.base
 * FileName: ScrollViewScrollableViewWrapper
 * Date: on 2018/2/11  下午10:05
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class ScrollableScrollViewWrapper extends AbsScrollableViewWrapper<ScrollableScrollView> {
    /**
     * 最小滚动距离
     */
    private static final int SCROLL_LIMIT = 40;

    public ScrollableScrollViewWrapper(ScrollableScrollView scrollingView) {
        super(scrollingView);
    }

    @Override
    public void setup(final ScrollDelegate delegate, ScrollableScrollView scrollableView) {
        scrollableView.addScrollChangedListener(new ScrollableScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (delegate != null) {
                    if (oldt > t && oldt - t > SCROLL_LIMIT) {
                        //下滑
                        delegate.onScrolledToDown();
                    } else if (oldt < t && t - oldt > SCROLL_LIMIT) {
                        //上滑动
                        delegate.onScrolledToUp();
                    }
                }
            }

            @Override
            public void onScrolledToBottom() {
                if (delegate != null) {
                    delegate.onScrolledToBottom();
                }
            }

            @Override
            public void onScrolledToTop() {
                if (delegate != null) {
                    delegate.onScrolledToTop();
                }
            }
        });
    }

    @Override
    public void moveToTop() {
        getScrollableView().scrollTo(0, 0);
    }

    @Override
    public void smoothMoveToTop() {
        getScrollableView().smoothScrollBy(0, 0);
    }
}