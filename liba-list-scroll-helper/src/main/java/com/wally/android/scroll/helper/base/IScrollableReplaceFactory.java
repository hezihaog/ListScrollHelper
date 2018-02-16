package com.wally.android.scroll.helper.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Package: com.wally.android.scroll.helper.base
 * FileName: IScrollableLayoutFactory
 * Date: on 2018/2/16  上午10:23
 * Auther: zihe
 * Descirbe:IScrollableView实现类替换工厂类接口
 * Email: hezihao@linghit.com
 */

public interface IScrollableReplaceFactory {
    /**
     * 开始安装
     */
    void install();

    /**
     * 执行替换调用实现类的onReplace()方法
     */
    View performReplace(View parent, String name, Context context, AttributeSet attrs);
}