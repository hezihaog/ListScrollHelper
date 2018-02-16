package com.wally.android.helper.util;

import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wally.android.helper.R;

/**
 * Package: com.wally.android.helper.util
 * FileName: ListScrollOutInfoHelper
 * Date: on 2018/2/16  下午1:37
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class ListScrollOutInfoHelper {
    public static final String TAG = ListScrollOutInfoHelper.class.getName();

    private StringBuilder mStringBuilder = new StringBuilder();
    private TextView mOutInfo;
    private ScrollView mScrollView;
    private final Handler mMainHandler;
    private final Runnable mRunnable;

    public ListScrollOutInfoHelper(ViewGroup rootLayout) {
        mScrollView = rootLayout.findViewById(R.id.printInfoScrollView);
        mOutInfo = rootLayout.findViewById(R.id.outInfo);
        mMainHandler = new Handler(rootLayout.getContext().getMainLooper());
        //滚动到底部
        mRunnable = new Runnable() {
            @Override
            public void run() {
                //滚动到底部
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        };
    }

    /**
     * 打印信息
     */
    public void printInfo(String info) {
        mMainHandler.removeCallbacks(mRunnable);
        Log.d(TAG, info);
        mStringBuilder.append(info);
        mStringBuilder.append("\n");
        mOutInfo.setText(mStringBuilder.toString());
        mMainHandler.post(mRunnable);
    }
}