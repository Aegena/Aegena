package com.campanula.aegena.activity;

import android.widget.SeekBar;

import com.campanula.aegena.R;
import com.campanula.library.base.BaseActivity;
import com.campanula.library.widget.waves.Waves;

/**
 * package com.campanula.angena.activity
 *
 * @author 000286
 * create 2018-11-01
 * desc
 **/
public class WavesActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {
    private Waves mWaves;
    private SeekBar mRedSeekBar;
    private SeekBar mGreenSeekBar;
    private SeekBar mBlueSeekBar;
    private SeekBar mAlphaSeekBar;

    @Override
    protected void bindData() {

    }

    @Override
    protected void viewById() {
        mWaves = findViewById(R.id.mWaves);
        mBlueSeekBar = findViewById(R.id.mBlueSeekBar);
        mRedSeekBar = findViewById(R.id.mRedSeekBar);
        mGreenSeekBar = findViewById(R.id.mGreenSeekBar);
        mAlphaSeekBar = findViewById(R.id.mAlphaSeekBar);

        mBlueSeekBar.setOnSeekBarChangeListener(this);
        mRedSeekBar.setOnSeekBarChangeListener(this);
        mGreenSeekBar.setOnSeekBarChangeListener(this);
        mAlphaSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_waves;
    }

    @Override
    protected String tag() {
        return WavesActivity.class.getSimpleName();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.mBlueSeekBar:
                mWaves.setBlue(progress);
                break;
            case R.id.mRedSeekBar:
                mWaves.setRed(progress);
                break;
            case R.id.mGreenSeekBar:
                mWaves.setGreenColor(progress);
                break;
            case R.id.mAlphaSeekBar:
                mWaves.setAlpha(progress);
                break;
            default:
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
