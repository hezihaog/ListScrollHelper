package com.wally.android.scroll.helper.wrapper;

import android.widget.AbsListView;

import com.wally.android.scroll.helper.widget.ScrollableGridView;
import com.wally.android.scroll.helper.base.AbsScrollableViewWrapper;


/**
 * Package: oms.mmc.android.fast.framwork.widget.view.wrapper
 * FileName: ScrollableGridViewWrapper
 * Date: on 2018/2/11  下午11:17
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class ScrollableGridViewWrapper extends AbsScrollableViewWrapper<ScrollableGridView> {
    private int oldVisibleItem = 0;
    //第一次进入界面时也会回调滚动，所以当手动滚动再监听
    private boolean isFirst = true;

    public ScrollableGridViewWrapper(ScrollableGridView scrollingView) {
        super(scrollingView);
    }

    @Override
    public void setup(final ScrollDelegate delegate, ScrollableGridView scrollableView) {
        scrollableView.addOnListViewScrollListener(new ScrollableGridView.OnListViewScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (delegate != null) {
                    if (view.getLastVisiblePosition() + 1 == view.getCount()) {
                        delegate.onScrolledToBottom();
                    } else if (view.getFirstVisiblePosition() == 0) {
                        delegate.onScrolledToTop();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (delegate != null) {
                    if (firstVisibleItem > oldVisibleItem && isFirst) {
                        //上滑
                        delegate.onScrolledToUp();
                    }
                    if (oldVisibleItem > firstVisibleItem && isFirst) {
                        //下滑
                        delegate.onScrolledToDown();
                    }
                    oldVisibleItem = firstVisibleItem;
                }
            }
        });
    }

    @Override
    public void moveToTop() {
        getScrollableView().setSelection(0);
    }

    @Override
    public void smoothMoveToTop() {
        getScrollableView().smoothScrollToPosition(0);
    }
}