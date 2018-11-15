package com.campanula.library.widget.qqbezier;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.*;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
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
    // 拖拽圆的圆点
    private PointF mDragPointF;
    // 固定圆的圆点
    private PointF mStickyPointF;
    // 二阶贝塞尔曲线控制点
    private PointF mControlPointF;
    // 拖拽的距离
    private float mDragDistance;
    // 最大的距离
    private float maxDistance = 300;
    // View的宽
    private float mViewWidth;
    // View的高
    private float mViewHeight;
    // 初始状态
    @Status
    private int status = Status.STATUS_INIT;
    // 固定圆的半径
    private int mStickyRadius;
    // 固定小圆的默认半径
    private int mDefRadius = 30;
    // 拖拽圆的半径
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
        for (int i = 0; i < pointRes.length; i++) {
            bitmaps[i] = BitmapFactory.decodeResource(getResources(), pointRes[i]);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isInsideRange() && status == Status.STATE_DRAG) {
            // 绘制固定的圆
            canvas.drawCircle(mStickyPointF.x, mStickyPointF.y, mStickyRadius, mPaint);
            // 线获取两个圆的斜率
            float link = MathUtil.getLineSlope(mDragPointF, mStickyPointF);
            // 通过两个圆心和半径，获取斜率的外切点
            PointF[] mStickyPoints = MathUtil.getIntersectionPoints(mStickyPointF, mStickyRadius, link);
            mDragRadius = (int) (Math.min(mViewWidth, mViewHeight) / 2);
            PointF[] mDragPoints = MathUtil.getIntersectionPoints(mDragPointF, mDragRadius, link);
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
     * @param mCacheBitmap 缓存的bitmap
     */
    public void setCacheBitmap(Bitmap mCacheBitmap) {
        this.mCacheBitmap = mCacheBitmap;
        this.mViewWidth = this.mCacheBitmap.getWidth();
        this.mViewHeight = this.mCacheBitmap.getHeight();
    }


    /**
     * 设置固定点的坐标
     *
     * @param stickyX 固定点X
     * @param stickyY 固定点y
     * @param touchX  触摸点X
     * @param touchY  触摸点Y
     */
    public void setStickyPoint(float stickyX, float stickyY, float touchX, float touchY) {
        // 设置固定点和拖拽点的坐标
        mStickyPointF.set(stickyX, stickyY);
        mDragPointF.set(touchX, touchY);
        // 通过两个点算出圆心距，也就是拖拽距离
        mDragDistance = MathUtil.getTwoPointDistance(mDragPointF, mStickyPointF);
        if (mDragDistance <= maxDistance) {
            mStickyRadius = (mDefRadius - mDragDistance / 10) < 10 ? 10 : (int) (mDefRadius - mDragDistance / 10);
            status = Status.STATE_DRAG;
        } else {
            status = Status.STATUS_INIT;
        }

    }

    /**
     * 设置拖拽点的坐标
     *
     * @param touchX 触摸点X
     * @param touchY 触摸点Y
     */
    public void setDragLocation(float touchX, float touchY) {
        mDragPointF.set(touchX, touchY);
        mDragDistance = MathUtil.getTwoPointDistance(mDragPointF, mStickyPointF);
        if (status == Status.STATE_DRAG) {
            if (isInsideRange()) {
                mStickyRadius = (mDefRadius - mDragDistance / 10) < 10 ? 10 : (int) (mDefRadius - mDragDistance / 10);
            } else {
                status = Status.STATE_MOVE;
                if (mOnDragListener != null) {
                    mOnDragListener.onMove();
                }
            }
        }
        invalidate();
    }


    /**
     * 拖拽抬起
     */
    public void setDragUp() {
        if (status == Status.STATE_DRAG && isInsideRange()) {
            // 拖拽状态在范围内
            reAnimator();
        } else if (status == Status.STATE_MOVE) {
            // 拖拽状态在范围内
            if (isInsideRange()) {
                reAnimator();
            } else {
                // 在范围外，拖拽消失
                status = Status.STATE_DISMISS;
                explodeAnimator();
            }
        }
    }

    /**
     * 爆炸动画
     */
    public void explodeAnimator() {
        ValueAnimator mValueAnimator = ValueAnimator.ofInt(0, pointRes.length);
        mValueAnimator.setDuration(300);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                index = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (mOnDragListener != null) {
                    mOnDragListener.onDismiss();
                }
            }
        });
        mValueAnimator.start();
    }

    /**
     * 拖拽还原
     */
    private void reAnimator() {
        switch (status) {
            case Status.STATE_DRAG:
                ValueAnimator mValueAnimator = ValueAnimator.ofObject(new PointEvaluator(),
                        new PointF(mDragPointF.x, mDragPointF.y), new PointF(mStickyPointF.x, mStickyPointF.y));
                mValueAnimator.setDuration(300);
                mValueAnimator.setInterpolator(new TimeInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        //http://inloop.github.io/interpolator/
                        float f = 0.571429f;
                        return (float) (Math.pow(2.0, -4.0 * input) * Math.sin((input - f / 4) * (2 * Math.PI) / f) + 1);
                    }
                });

                mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        PointF currentPointF = (PointF) animation.getAnimatedValue();
                        mDragPointF.set(currentPointF.x, currentPointF.y);
                        invalidate();
                    }
                });

                mValueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        clearDragView();
                        if (mOnDragListener != null) {
                            mOnDragListener.onDrag();
                        }
                    }
                });
                mValueAnimator.start();
                break;
            case Status.STATE_MOVE:
                mDragPointF.set(mStickyPointF.x, mStickyPointF.y);
                invalidate();
                clearDragView();
                if (mOnDragListener != null) {
                    mOnDragListener.onRestore();
                }
                break;
            default:
        }
    }

    /**
     * 清理当前显示的View
     */
    private void clearDragView() {
        ViewGroup mViewGroup = (ViewGroup) getParent();
        mViewGroup.removeView(this);
        if (this.mView != null) {
            this.mView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置父级View
     *
     * @param mView View
     */
    public void setView(View mView) {
        this.mView = mView;
        this.mView.setVisibility(View.INVISIBLE);
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


}
