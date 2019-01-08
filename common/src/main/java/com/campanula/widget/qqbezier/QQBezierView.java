package com.campanula.widget.qqbezier;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * package com.campanula.library.widget.qqbezier
 *
 * @author 000286
 * create 2018-11-01
 * desc
 **/
public class QQBezierView extends AppCompatTextView {
    private DragView mDragView;
    private float mWidth;
    private float mHeight;
    private com.campanula.widget.qqbezier.OnDragListener mOnDragListener;

    public QQBezierView(Context context) {
        this(context, null);
    }

    public QQBezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public QQBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.mWidth = w;
        this.mHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View rootView = getRootView();
        // 获取触摸位置在全屏所在的位置
        float mRawX = event.getRawX();
        float mRawY = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                int[] location = new int[2];
                getLocationOnScreen(location);
                if (rootView instanceof ViewGroup) {
                    mDragView = new DragView(getContext());
                    mDragView.setView(this);
                    mDragView.setOnDragListener(mOnDragListener);
                    mDragView.setStickyPoint(location[0] + this.mWidth / 2, location[1] + this.mHeight / 2, mRawX, mRawY);
                    // 获取bitmap的缓存，滑动时，直接通过drawBitmap绘制
                    setDrawingCacheEnabled(true);
                    mDragView.setCacheBitmap(getDrawingCache());
                    ((ViewGroup) rootView).addView(mDragView);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // 请求父View不拦截
                getParent().requestDisallowInterceptTouchEvent(true);
                mDragView.setDragLocation(mRawX, mRawY);
                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(true);
                mDragView.setDragUp();
                break;
            default:
        }
        return true;
    }

    public void setOnDragListener(com.campanula.widget.qqbezier.OnDragListener mOnDragListener) {
        this.mOnDragListener = mOnDragListener;
    }
}
