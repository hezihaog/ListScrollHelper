package com.wally.android.scroll.helper.base;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Package: com.wally.android.scroll.helper.base
 * FileName: ScrollableLayoutReplaceFactory
 * Date: on 2018/2/16  上午10:15
 * Auther: zihe
 * Descirbe:Scrollable控件替换接口
 * Email: hezihao@linghit.com
 */

public interface IScrollableReplaceAdapter {
    /**
     * 当替换时回调
     */
    View onReplace(Activity activity, View parent, String name, Context context, AttributeSet attrs);
}