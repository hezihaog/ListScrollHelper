package com.wally.android.scroll.helper.wrapper;

import android.support.v7.widget.RecyclerView;

import com.wally.android.scroll.helper.widget.ScrollableRecyclerView;
import com.wally.android.scroll.helper.base.AbsScrollableViewWrapper;


/**
 * Package: oms.mmc.android.fast.framwork.base
 * FileName: RecyclerViewScrollableViewWapper
 * Date: on 2018/2/9  下午3:54
 * Auther: zihe
 * Descirbe:RecyclerView的滚动控件包裹类
 * Email: hezihao@linghit.com
 */

public class ScrollableRecyclerViewWrapper extends AbsScrollableViewWrapper<ScrollableRecyclerView> {
    //第一次进入界面时也会回调滚动，所以当手动滚动再监听
    private boolean isNotFirst = false;

    public ScrollableRecyclerViewWrapper(ScrollableRecyclerView scrollingView) {
        super(scrollingView);
    }

    @Override
    public void setup(final ScrollDelegate delegate, ScrollableRecyclerView scrollableView) {
        scrollableView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isNotFirst = true;
                if (delegate != null) {
                    //如果滚动到最后一行，RecyclerView.canScrollVertically(1)的值表示是否能向上滚动，false表示已经滚动到底部
                    if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                            !recyclerView.canScrollVertically(1)) {
                        delegate.onScrolledToBottom();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (delegate != null && isNotFirst) {
                    //RecyclerView.canScrollVertically(-1)的值表示是否能向下滚动，false表示已经滚动到顶部
                    if (!recyclerView.canScrollVertically(-1)) {
                        delegate.onScrolledToTop();
                    }
                    //下滑
                    if (dy < 0) {
                        delegate.onScrolledToDown();
                    }
                    //上滑
                    if (dy > 0) {
                        delegate.onScrolledToUp();
                    }
                }
            }
        });
    }

    @Override
    public void moveToTop() {
        RecyclerView recyclerView = getScrollableView();
        if (recyclerView != null) {
            recyclerView.scrollToPosition(0);
        }
    }

    @Override
    public void smoothMoveToTop() {
        RecyclerView recyclerView = getScrollableView();
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }
}
