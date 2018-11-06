package com.campanula.library.widget.qqbezier;

import android.graphics.PointF;

/**
 * package com.campanula.library.widget.qqbezier
 *
 * @author 000286
 * create 2018-11-01
 * desc
 **/
public final class MathUtil {
    /**
     * 获取两个点的直线距离
     *
     * @param p1 点1
     * @param p2 点2
     * @return 距离
     */
    public static float getTwoPointDistance(PointF p1, PointF p2) {
        return (float) Math.sqrt(
                Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2)
        );
    }


    /**
     * 计算两个点的斜率
     *
     * @param x1 点1的x坐标值
     * @param x2 点2的x坐标值
     * @param y1 点1的y坐标值
     * @param y2 点2的y坐标值
     * @return 斜率
     */
    public static float getLineSlope(float x1, float x2, float y1, float y2) {
        if (x2 - x1 == 0) {
            return 0;
        }
        return (y2 - y1) / (x2 - x1);
    }

    /**
     * 计算两个点的斜率
     *
     * @param p1 点1
     * @param p2 点2
     * @return 斜率
     */
    public static float getLineSlope(PointF p1, PointF p2) {
        if (p2.x - p1.x == 0) {
            return 0;
        }
        return (p2.y - p1.y) / (p2.x - p1.x);
    }


    /**
     * 获取两个点的中点
     *
     * @param p1 点1
     * @param p2 点2
     * @return 中点
     */
    public static PointF getMiddlePoint(PointF p1, PointF p2) {
        return new PointF((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    /**
     * 获取通过指定的圆心，斜率为link的直线与原的交点
     *
     * @param pMiddle 圆心
     * @param radius  弧度
     * @param link    斜率
     * @return 交点
     */
    public static PointF[] getIntersectionPoints(PointF pMiddle, float radius, float link) {
        PointF[] points = new PointF[2];
        float radian;
        float xOffset = radius;
        float yOffset = 0;
        if (link != 0) {
            radian = (float) Math.atan(link);
            xOffset = (float) (Math.sin(radian) * radius);
            yOffset = (float) (Math.cos(radian) * radius);
        }
        points[0] = new PointF(pMiddle.x + xOffset, pMiddle.y - yOffset);
        points[1] = new PointF(pMiddle.x - xOffset, pMiddle.y + yOffset);
        return points;
    }
}
