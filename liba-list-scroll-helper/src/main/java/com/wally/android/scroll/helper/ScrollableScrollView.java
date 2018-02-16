package com.wally.android.scroll.helper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.wally.android.scroll.helper.base.IScrollableView;

import java.util.ArrayList;


/**
 * Package: oms.mmc.android.fast.framwork.widget.view
 * FileName: ScrollableScrollView
 * Date: on 2018/2/11  下午6:12
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public abstract class ScrollableScrollView extends ScrollView implements IScrollableView {
    public ScrollableScrollView(Context context) {
        super(context);
    }

    public ScrollableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private boolean isScrolledToTop = true;
    private boolean isScrolledToBottom = false;

    private ArrayList<OnScrollChangedListener> mListeners = new ArrayList<OnScrollChangedListener>();

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY == 0) {
            isScrolledToTop = clampedY;
            isScrolledToBottom = false;
        } else {
            isScrolledToTop = false;
            isScrolledToBottom = clampedY;
        }
        notifyScrollChangedListeners();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        for (OnScrollChangedListener listener : mListeners) {
            listener.onScrollChanged(l, t, oldl, oldt);
        }
        if (android.os.Build.VERSION.SDK_INT < 9) {  // API 9及之后走onOverScrolled方法监听
            if (getScrollY() == 0) {    // 小心踩坑1: 这里不能是getScrollY() <= 0
                isScrolledToTop = true;
                isScrolledToBottom = false;
            } else if (getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight()) {
                // 小心踩坑2: 这里不能是 >=
                // 小心踩坑3（可能忽视的细节2）：这里最容易忽视的就是ScrollView上下的padding　
                isScrolledToBottom = true;
                isScrolledToTop = false;
            } else {
                isScrolledToTop = false;
                isScrolledToBottom = false;
            }
            notifyScrollChangedListeners();
        }
        // 有时候写代码习惯了，为了兼容一些边界奇葩情况，上面的代码就会写成<=,>=的情况，结果就出bug了
        // 我写的时候写成这样：getScrollY() + getHeight() >= getChildAt(0).getHeight()
        // 结果发现快滑动到底部但是还没到时，会发现上面的条件成立了，导致判断错误
        // 原因：getScrollY()值不是绝对靠谱的，它会超过边界值，但是它自己会恢复正确，导致上面的计算条件不成立
        // 仔细想想也感觉想得通，系统的ScrollView在处理滚动的时候动态计算那个scrollY的时候也会出现超过边界再修正的情况
    }

    private void notifyScrollChangedListeners() {
        if (isScrolledToTop) {
            for (OnScrollChangedListener listener : mListeners) {
                listener.onScrolledToTop();
            }
        } else if (isScrolledToBottom) {
            for (OnScrollChangedListener listener : mListeners) {
                listener.onScrolledToBottom();
            }
        }
    }

    public boolean isScrolledToTop() {
        return isScrolledToTop;
    }

    public boolean isScrolledToBottom() {
        return isScrolledToBottom;
    }

    /**
     * 定义监听接口
     */
    public interface OnScrollChangedListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);

        void onScrolledToTop();

        void onScrolledToBottom();
    }

    public void addScrollChangedListener(OnScrollChangedListener listener) {
        mListeners.add(listener);
    }

    public void removeScrollChangedListener(OnScrollChangedListener listener) {
        mListeners.remove(listener);
    }

    public void removeAllScrollChangedListener(OnScrollChangedListener listener) {
        mListeners.clear();
    }
}