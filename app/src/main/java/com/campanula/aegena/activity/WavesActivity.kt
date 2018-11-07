package com.campanula.aegena.activity

import android.widget.SeekBar

import com.campanula.aegena.R
import com.campanula.library.base.BaseActivity
import com.campanula.library.widget.waves.Waves

/**
 * package com.campanula.aegena.activity
 *
 * @author 000286
 * create 2018-11-01
 * desc
 */
class WavesActivity : BaseActivity(), SeekBar.OnSeekBarChangeListener {
    private lateinit var mWaves: Waves
    private lateinit var mRedSeekBar: SeekBar
    private lateinit var mGreenSeekBar: SeekBar
    private lateinit var mBlueSeekBar: SeekBar
    private lateinit var mAlphaSeekBar: SeekBar

    override fun bindData() {

    }

    override fun viewById() {
        mWaves = findViewById(R.id.mWaves)
        mBlueSeekBar = findViewById(R.id.mBlueSeekBar)
        mRedSeekBar = findViewById(R.id.mRedSeekBar)
        mGreenSeekBar = findViewById(R.id.mGreenSeekBar)
        mAlphaSeekBar = findViewById(R.id.mAlphaSeekBar)

        mBlueSeekBar.setOnSeekBarChangeListener(this)
        mRedSeekBar.setOnSeekBarChangeListener(this)
        mGreenSeekBar.setOnSeekBarChangeListener(this)
        mAlphaSeekBar.setOnSeekBarChangeListener(this)
    }

    override fun getLayoutViewId(): Int {
        return R.layout.activity_waves
    }

    override fun tag(): String {
        return WavesActivity::class.java.simpleName
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        when (seekBar.id) {
            R.id.mBlueSeekBar -> mWaves!!.setBlue(progress)
            R.id.mRedSeekBar -> mWaves!!.setRed(progress)
            R.id.mGreenSeekBar -> mWaves!!.setGreenColor(progress)
            R.id.mAlphaSeekBar -> mWaves!!.setAlpha(progress)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }
}
