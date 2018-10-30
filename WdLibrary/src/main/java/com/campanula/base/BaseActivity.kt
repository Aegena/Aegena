package com.campanula.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var TAG: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG = getPackagePath()
        setRequestWindowFeature()
        setContentView(getLayoutViewId())
        findActivityViewById()
        bindViewData()
    }

    /**
     * 取得Activity的包的路径
     */
    protected abstract fun getPackagePath(): String

    /**
     * 绑定初始View数据
     */
    protected abstract fun bindViewData()

    /**
     * findViewId
     */
    protected abstract fun findActivityViewById()

    /**
     * 设置页面的属性，可以重写，在setContentView之前执行
     */
    protected fun setRequestWindowFeature() {

    }

    @LayoutRes
    protected abstract fun getLayoutViewId(): Int


}