package com.wally.android.scroll.helper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

import com.wally.android.scroll.helper.base.IScrollableView;

import java.util.ArrayList;

/**
 * Package: oms.mmc.android.fast.framwork.widget.view
 * FileName: ScrollableListView
 * Date: on 2018/2/11  下午6:10
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public abstract class ScrollableListView extends ListView implements IScrollableView {
    private final ArrayList<OnListViewScrollListener> mListener
            = new ArrayList<OnListViewScrollListener>();

    public ScrollableListView(Context context) {
        super(context);
        init();
    }

    public ScrollableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //内部持有监听器，对外开放监听器组
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                for (OnListViewScrollListener listener : mListener) {
                    listener.onScrollStateChanged(view, scrollState);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                for (OnListViewScrollListener listener : mListener) {
                    listener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }
            }
        });
    }

    public interface OnListViewScrollListener {
        void onScrollStateChanged(AbsListView view, int scrollState);

        void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
    }

    public void addOnListViewScrollListener(OnListViewScrollListener listener) {
        mListener.add(listener);
    }

    public void removeOnListViewScrollListener(OnListViewScrollListener listener) {
        mListener.remove(listener);
    }

    public void removeAllOnListViewScrollListener() {
        mListener.clear();
    }
}
