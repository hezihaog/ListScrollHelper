package com.wally.android.helper.util;

import java.util.ArrayList;

/**
 * Package: com.wally.android.helper.util
 * FileName: DataSourseHelper
 * Date: on 2018/2/16  下午3:01
 * Auther: zihe
 * Descirbe:
 * Email: hezihao@linghit.com
 */

public class DataSourseHelper {
    /**
     * 组装指定数量的条目数据
     */
    public static ArrayList<String> loadData(int count) {
        ArrayList<String> dataList = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            dataList.add("View Item".concat(String.valueOf(i)));
        }
        return dataList;
    }
}
