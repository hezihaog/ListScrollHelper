package com.wally.android.scroll.helper.base;


import com.wally.android.scroll.helper.listener.IListScrollListener;

import java.util.List;

/**
 * Package: oms.mmc.android.fast.framwork.base
 * FileName: IScrollHelper
 * Date: on 2018/2/9  下午4:36
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public interface IScrollHelper {
    /**
     * 获取当前使用滚动控件包裹类
     *
     * @return 当前滚动控件包裹类对象
     */
    IScrollableViewWrapper getScrollableViewWrapper();

    /**
     * 添加滚动监听器
     *
     * @param listener 监听对象
     */
    void addListScrollListener(IListScrollListener listener);

    /**
     * 移除滚动监听器
     *
     * @param listener 监听对象
     */
    void removeListScrollListener(IListScrollListener listener);

    /**
     * 移除所有滚动监听器
     */
    void removeAllScrollListener();

    /**
     * 获取所有滚动监听器
     */
    List<IListScrollListener> getScrollListeners();

    /**
     * 瞬时滚动到顶部
     */
    void moveToTop();

    /**
     * 缓缓滚动到顶部
     */
    void smoothMoveToTop();
}