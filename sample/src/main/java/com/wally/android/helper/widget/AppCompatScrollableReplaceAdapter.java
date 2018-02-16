package com.wally.android.helper.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;

import com.wally.android.scroll.helper.base.ScrollableViewFactory;

/**
 * Package: com.wally.android.helper
 * FileName: ScrollableReplaceAdapter
 * Date: on 2018/2/16  上午11:19
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class AppCompatScrollableReplaceAdapter extends ScrollableViewFactory.Adapter {
    @Override
    public View onReplace(Activity activity, View parent, String name, Context context, AttributeSet attrs) {
        //继承了工厂的内部类，替换了ListView、GridView、ScrollView，RecyclerView,，NestedScrollView
        View replaceView = super.onReplace(activity, parent, name, context, attrs);
        //如果是已经提供的ListView、GridView、ScrollView，RecyclerView,，NestedScrollView，则替换后，不为空，为空则不是这几种类型
        //如果你还有其他列表控件类，则在这里添加
        if (replaceView == null) {
            //如果是AppCompatActivity，由于AppCompatActivity已经占用了替换接口，这里需要兼容AppCompatActivity的替换
            return compat(activity, parent, name, context, attrs);
        } else {
            return replaceView;
        }
    }

    /**
     * 兼容AppCompatActivity的替换
     */
    private View compat(Activity activity, View parent, String name, Context context, AttributeSet attrs) {
        if (activity instanceof AppCompatActivity) {
            AppCompatDelegate delegate = ((AppCompatActivity) activity).getDelegate();
            return delegate.createView(parent, name, context, attrs);
        } else {
            return null;
        }
    }
}