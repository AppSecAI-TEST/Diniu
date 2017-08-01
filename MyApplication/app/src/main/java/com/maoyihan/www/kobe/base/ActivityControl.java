package com.maoyihan.www.kobe.base;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** activity管理类
 * Created by Administrator on 2016/9/15.
 */
public class ActivityControl {

    private static final Map<String, Activity> activityMap = new HashMap<>();

    /**
     * 添加activity
     */
    public static void addAty(String key, Activity aty) {
        activityMap.put(key, aty);
    }

    /**
     * 移除activity
     */
    public static boolean removeAty(String key) {
        Activity aty = activityMap.remove(key);
        if (aty != null) {
            return true;
        }
        return false;
    }

    /**
     * 结束所有的activity
     */
    public static void finishAll() {
        Set<String> activities = activityMap.keySet();
        for (String key : activities) {
            activityMap.get(key).finish();
        }
    }

}
