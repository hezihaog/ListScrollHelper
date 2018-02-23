package com.wally.android.scroll.helper.wrapper;

import android.widget.AbsListView;

import com.wally.android.scroll.helper.widget.ScrollableListView;
import com.wally.android.scroll.helper.base.AbsScrollableViewWrapper;


/**
 * Package: oms.mmc.android.fast.framwork.base
 * FileName: ListViewScrollableViewWrapper
 * Date: on 2018/2/11  下午6:30
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class ScrollableListViewWrapper extends AbsScrollableViewWrapper<ScrollableListView> {
    private int oldVisibleItem = 0;
    //第一次进入界面时也会回调滚动，所以当手动滚动再监听
    private boolean isNotFirst = false;

    public ScrollableListViewWrapper(ScrollableListView scrollingView) {
        super(scrollingView);
    }

    @Override
    public void setup(final ScrollDelegate delegate, ScrollableListView scrollableView) {
        scrollableView.addOnListViewScrollListener(new ScrollableListView.OnListViewScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView listView, int scrollState) {
                isNotFirst = true;
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (delegate != null) {
                        if (listView.getLastVisiblePosition() + 1 == listView.getCount()) {
                            delegate.onScrolledToBottom();
                        } else if (listView.getFirstVisiblePosition() == 0) {
                            delegate.onScrolledToTop();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView listView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (delegate != null) {
                    if (firstVisibleItem > oldVisibleItem && isNotFirst) {
                        //上滑
                        delegate.onScrolledToUp();
                    }
                    if (oldVisibleItem > firstVisibleItem && isNotFirst) {
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
