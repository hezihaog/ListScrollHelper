package com.wally.android.helper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wally.android.helper.R;
import com.wally.android.helper.util.ListScrollOutInfoHelper;
import com.wally.android.helper.widget.AppCompatScrollableReplaceAdapter;
import com.wally.android.scroll.helper.ListScrollHelper;
import com.wally.android.scroll.helper.base.ScrollableViewFactory;
import com.wally.android.scroll.helper.listener.IListScrollListener;

/**
 * Package: com.wally.android.helper
 * FileName: BaseSampleActivity
 * Date: on 2018/2/16  上午11:22
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public abstract class BaseSampleActivity extends AppCompatActivity {
    private View mRootLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //必须在onCreate之前替换
        ScrollableViewFactory.create(this, new AppCompatScrollableReplaceAdapter()).install();
        super.onCreate(savedInstanceState);
        mRootLayout = LayoutInflater.from(this).inflate(onLayoutId(), null);
        setContentView(mRootLayout);
        setupToolBar();
        onFindView();
        onBindContent();
        setupScrollListener();
    }

    /**
     * 获取布局id
     */
    protected abstract int onLayoutId();

    /**
     * 查找控件
     */
    protected abstract void onFindView();

    /**
     * 设置相关控件
     */
    protected void onBindContent() {

    }

    protected abstract ListScrollHelper onGetListScrollHelper();

    private void setupToolBar() {
        mToolbar = findViewById(R.id.toolBar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setTitleTextColor(getActivity().getResources().getColor(R.color.white));
    }

    /**
     * 添加滚动监听
     */
    private void setupScrollListener() {
        //获取页面对应的滚动帮助类
        ListScrollHelper scrollHelper = onGetListScrollHelper();
        //创建打印滚动信息帮助类来打印信息
        final ListScrollOutInfoHelper outInfoHelper = new ListScrollOutInfoHelper(getRootLayout());
        scrollHelper.addListScrollListener(new IListScrollListener() {
            @Override
            public void onScrolledUp() {
                outInfoHelper.printInfo("onScrolledToUp --> 上滑");
            }

            @Override
            public void onScrolledDown() {
                outInfoHelper.printInfo("onScrolledToDown --> 下滑");
            }

            @Override
            public void onScrollTop() {
                outInfoHelper.printInfo("onScrollTop --> 到顶啦");
            }

            @Override
            public void onScrollBottom() {
                outInfoHelper.printInfo("onScrollBottom --> 到底啦");
            }
        });
    }

    public ViewGroup getRootLayout() {
        return (ViewGroup) mRootLayout;
    }

    public BaseSampleActivity getActivity() {
        return this;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
