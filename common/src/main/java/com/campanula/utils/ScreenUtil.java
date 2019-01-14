package com.campanula.utils;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.campanula.logger.Logger;

/**
 * package com.campanula.utils
 *
 * @author campanula
 * create 2019/1/11
 * since
 * desc
 **/
public class ScreenUtil {

    /**
     * dp 转换 px
     *
     * @param mContext 上下文
     * @param dp       被转换的dp数值
     * @return 转换后的px数值
     */
    public static float dp2px(final Context mContext, final float dp) {
        return dp * mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;
    }

    /**
     * px 转换 dp
     *
     * @param mContext 上下文
     * @param px       被转换的px数值
     * @return 转换后的dp数值
     */
    public static float px2dp(final Context mContext, final float px) {
        return px / mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;
    }

    /**
     * 获取系统的状态烂高度
     *
     * @param mContext 上下文
     * @return 状态栏的高度，px单位
     */
    public static float getStatusBarHeight(final Context mContext) {
        int statusBarHeight = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        Logger.i("statusBarHeight", statusBarHeight);
        return statusBarHeight;
    }

    /**
     * 获取系统的状态栏高度
     *
     * @param mContext 上下文
     * @return 状态栏高度，px单位
     */
    public static float getFieldStatusBarHeight(final Context mContext) {
        int statusBarHeight = 0;
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusBarHeight = mContext.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.i("statusBarHeight", statusBarHeight);
        return statusBarHeight;
    }


    /**
     * 获取系统的分辨率
     *
     * @param mContext 上下文
     * @return 分辨率类
     */
    public static Resolution getResolution(final Context mContext) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        Resolution mResolution = new Resolution();
        mResolution.setHeight(mDisplayMetrics.heightPixels);
        mResolution.setWidth(mDisplayMetrics.widthPixels);
        return mResolution;
    }

    /**
     * 屏幕分辨节能率
     */
    public static class Resolution {
        private int width;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

}
