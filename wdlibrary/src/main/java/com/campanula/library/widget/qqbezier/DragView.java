package com.campanula.library.widget.qqbezier;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.*;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.campanula.library.R;

/**
 * package com.campanula.library.widget.qqbezier
 *
 * @author 000286
 * create 2018-11-01
 * desc
 **/
public class DragView extends View {

    private Path mPath;
    private Paint mPaint;
    private Bitmap mCacheBitmap;
    private PointF mDragPointF;
    private PointF mStickyPointF;
    private PointF mControlPointF;
    private float mDragDistance;
    private float maxDistance = 500;
    private float mViewWidth;
    private float mViewHeight;
    @Status
    private int status = Status.STATUS_INIT;
    private int mStickRadius;
    private int mDefRadius = 30;
    private int mDragRadius = 30;
    private int index = 0;
    private int[] pointRes = new int[]{
            R.drawable.point1,
            R.drawable.point2,
            R.drawable.point3,
            R.drawable.point4,
            R.drawable.point5
    };
    private Bitmap[] bitmaps;

    private com.campanula.library.widget.qqbezier.OnDragListener mOnDragListener;
    private View mView;


    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        mDragPointF = new PointF();
        mStickyPointF = new PointF();
        bitmaps = new Bitmap[pointRes.length];
        for (int res : pointRes) {
            bitmaps[res] = BitmapFactory.decodeResource(getResources(), res);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isInsideRange() && status == Status.STATE_DRAG) {
            // 绘制固定的圆
            canvas.drawCircle(mStickyPointF.x, mStickyPointF.y, mStickRadius, mPaint);
            // 线获取两个圆的斜率
            float link = MathUtil.getLineSlope(mDragPointF, mStickyPointF);
            // 通过两个圆心和半径，获取斜率的外切点
            PointF[] mStickyPoints = MathUtil.getInsersectionPoints(mStickyPointF, mStickRadius, link);
            mDragRadius = (int) (Math.min(mViewWidth, mViewHeight) / 2);
            PointF[] mDragPoints = MathUtil.getInsersectionPoints(mDragPointF, mDragRadius, link);
            // 二阶贝塞尔曲线的控制点去两个圆心的中点
            mControlPointF = MathUtil.getMiddlePoint(mDragPointF, mStickyPointF);
            // 绘制贝塞尔曲线
            mPath.reset();
            mPath.moveTo(mStickyPoints[0].x, mStickyPoints[0].y);
            mPath.quadTo(mControlPointF.x, mControlPointF.y, mDragPoints[0].x, mDragPoints[0].y);
            mPath.lineTo(mDragPoints[1].x, mDragPoints[1].y);
            mPath.quadTo(mControlPointF.x, mControlPointF.y, mStickyPoints[1].x, mStickyPoints[1].y);
            mPath.lineTo(mStickyPoints[0].x, mStickyPoints[0].y);
            canvas.drawPath(mPath, mPaint);
        }

        if (mCacheBitmap != null && status != Status.STATE_DISMISS) {
            // 绘制缓存的bitmap
            canvas.drawBitmap(mCacheBitmap, mDragPointF.x - mViewWidth / 2, mDragPointF.y - mViewWidth / 2, mPaint);

        }
        if (status == Status.STATE_DISMISS && index < pointRes.length) {
            // 绘制爆炸效果
            canvas.drawBitmap(bitmaps[index], mDragPointF.x - mViewWidth / 2, mDragPointF.y - mViewHeight / 2, mPaint);
        }


    }

    /**
     * 设置缓存的bitmap
     *
     * @param mCacheBitmap bitmap
     */
    public void setCacheBitmap(Bitmap mCacheBitmap) {
        this.mCacheBitmap = mCacheBitmap;
    }

    /**
     * 是否在最大范围内
     *
     * @return true 在，false 不在
     */
    private boolean isInsideRange() {
        return this.mDragDistance <= this.maxDistance;
    }

    /**
     * 设置手势滑动监听事件
     *
     * @param mOnDragListener 监听事件
     */
    public void setOnDragListener(com.campanula.library.widget.qqbezier.OnDragListener mOnDragListener) {
        this.mOnDragListener = mOnDragListener;
    }

    /**
     * 设置父级View
     *
     * @param mView View
     */
    public void setView(View mView) {
        this.mView = mView;
    }
}
