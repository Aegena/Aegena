package com.campanula.library.widget.waves;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * package com.campanula.library.widget.waves
 *
 * @author 000286
 * create 2018-10-31
 * desc
 **/
public class Waves extends View {

    private Paint mPaint;
    private Path mPath;
    private float offset = 0f;
    private int waveSize = 2;
    private int waveWidth = 360;
    private int waveHeight = 80;
    private int viewWidth;
    private int viewHeight;
    private int baseLine = 0;
    private int color = Color.BLUE;
    private int alpha = 255;
    private long duration = 1000L;


    public Waves(Context context) {
        super(context);
    }

    public Waves(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Waves(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Waves(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
