package com.wally.android.scroll.helper.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.wally.android.scroll.helper.base.IScrollableView;


/**
 * Package: oms.mmc.android.fast.framwork.widget.view
 * FileName: ScrollableRecyclerView
 * Date: on 2018/2/11  下午6:32
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public abstract class ScrollableRecyclerView extends RecyclerView implements IScrollableView {
    public ScrollableRecyclerView(Context context) {
        super(context);
    }

    public ScrollableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
