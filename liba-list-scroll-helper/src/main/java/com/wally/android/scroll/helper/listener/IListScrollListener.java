package com.wally.android.scroll.helper.listener;

/**
 * Package: oms.mmc.android.fast.framwork.base
 * FileName: IListScrollListener
 * Date: on 2018/2/9  下午3:24
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public interface IListScrollListener {
    /**
     * 当前正在向上滚动
     */
    void onScrolledUp();

    /**
     * 当前正在向下滚动
     */
    void onScrolledDown();

    /**
     * 已经滚动到顶部
     */
    void onScrollTop();

    /**
     * 已近滚动到底部
     */
    void onScrollBottom();
}