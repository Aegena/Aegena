package com.campanula.widget.waves;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import com.campanula.library.R;

/**
 * package com.campanula.library.widget.waves
 *
 * @author 000286 create 2018-10-31 desc
 */
public class Waves extends View {

    private Paint mPaint;
    private Path mPath;
    private float offset = 0f;
    private float waveSize = 2;
    private int waveWidth = 360;
    private int waveHeight = 80;
    private int viewWidth;
    private int viewHeight;
    private int baseLine = 0;

    @ColorInt
    private int color = Color.BLUE;
    @IntRange(from = 0, to = 255)
    private int alpha = 255;
    private long duration = 1000L;
    @IntRange(from = 0, to = 255)
    private int redColor = 0;
    @IntRange(from = 0, to = 255)
    private int blueColor = 0;
    @IntRange(from = 0, to = 255)
    private int greenColor = 0;


    /**
     * Instantiates a new Waves.
     *
     * @param context the context
     */
    public Waves(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Waves.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public Waves(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Instantiates a new Waves.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public Waves(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }


    /**
     * Instantiates a new Waves.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     * @param defStyleRes  the def style res
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Waves(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Waves, defStyleAttr, defStyleRes);
        this.alpha = typedArray.getInteger(R.styleable.Waves_wave_alpha, this.alpha);
        this.color = typedArray.getColor(R.styleable.Waves_wave_color, this.color);
        this.waveHeight = typedArray.getInteger(R.styleable.Waves_wave_height, this.waveHeight);
        this.waveWidth = typedArray.getInteger(R.styleable.Waves_wave_width, this.waveWidth);
        this.duration = typedArray.getInteger(R.styleable.Waves_wave_duration, 1000);
        typedArray.recycle();

        this.alpha = this.alpha > 255 ? 255 : this.alpha < 0 ? 0 : this.alpha;
        this.duration = this.duration > 10 * 1000 ? 10 * 1000 : this.duration < 500 ? 500 : this.duration;

        mPaint = new Paint();
        mPaint.setColor(this.color);
        mPaint.setStrokeWidth(10f);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(this.alpha);

        mPath = new Path();
        startAnimation();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.viewHeight = getMeasuredHeight();
        this.viewWidth = getMeasuredWidth();
        initWave();
    }

    private void initWave() {
        float size = Math.round(this.viewWidth / this.waveWidth / 2);
        size = resetSize(size);
        this.waveSize = Math.round(size + 1.5) + 1;
        this.baseLine = this.viewHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        // 半个波长
        float itemWave = Math.round(this.waveWidth / 2);
        mPath.moveTo(-itemWave * 3, baseLine);
        for (int i = -3; i < waveSize; i++) {
            float startX = i * itemWave;
            mPath.quadTo(startX + Math.round(itemWave / 2) + offset,
                    i % 2 == 0 ? baseLine + waveHeight : baseLine - waveHeight,
                    startX + itemWave + offset,
                    baseLine
            );
        }
        mPath.lineTo(this.viewWidth, this.viewHeight);
        mPath.lineTo(0, this.viewHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

    }

    private float resetSize(float size) {
        if (size * this.waveWidth < this.viewWidth) {
            return resetSize(size + 1);
        } else {
            return size;
        }
    }

    private void startAnimation() {
        ValueAnimator mValueAnimator = ValueAnimator.ofFloat(0, waveWidth);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setDuration(this.duration);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.start();
    }

    /**
     * Sets red.
     *
     * @param redColor the red color
     */
    public void setRed(@IntRange(from = 0, to = 255) int redColor) {
        this.redColor = redColor;
        color();
    }

    /**
     * Sets blue.
     *
     * @param blueColor the blue color
     */
    public void setBlue(@IntRange(from = 0, to = 255) int blueColor) {
        this.blueColor = blueColor;
        color();
    }

    /**
     * Sets green color.
     *
     * @param greenColor the green color
     */
    public void setGreenColor(@IntRange(from = 0, to = 255) int greenColor) {
        this.greenColor = greenColor;
        color();
    }

    private void color() {
        this.color = Color.rgb(redColor, greenColor, blueColor);
        mPaint.setColor(this.color);
        invalidate();
    }


    /**
     * Sets wave width.
     *
     * @param waveWidth the wave width
     */
    public void setWaveWidth(int waveWidth) {
        this.waveWidth = waveWidth;
        initWave();
        invalidate();
    }

    /**
     * Sets wave height.
     *
     * @param waveHeight the wave height
     */
    public void setWaveHeight(int waveHeight) {
        this.waveHeight = waveHeight;
        initWave();
        invalidate();
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(@ColorInt int color) {
        this.color = color;
        mPaint.setColor(this.color);
        invalidate();
    }

    /**
     * Sets alpha.
     *
     * @param alpha the alpha
     */
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        this.alpha = alpha;
        mPaint.setAlpha(this.alpha);
        invalidate();
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(@IntRange(from = 0, to = 10 * 1000) long duration) {
        this.duration = duration;
        startAnimation();
    }
}
