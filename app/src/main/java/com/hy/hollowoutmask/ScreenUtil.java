package com.hy.hollowoutmask;

import android.content.Context;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;

/**
 * Author: hy
 * Date: 2022/10/12
 * Description:
 */
public class ScreenUtil {
    public static int statusbarheight;
    public static int getStatusBarHeight(Context context) {
        if (statusbarheight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer)field.get(o);
                statusbarheight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        return statusbarheight;
    }
}
