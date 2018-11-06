package com.campanula.library.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * package ${package_name}
 *
 * @author ${user}
 * create ${date}
 * @since
 **/
public abstract class BaseActivity extends AppCompatActivity {
    private String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = tag();
        setRequestWindowFeature();
        setContentView(getLayoutViewId());
        viewById();
        bindData();
    }

    /**
     * 初始化控件数据
     */
    protected abstract void bindData();

    /**
     * 寻找当前页面的View
     */
    protected abstract void viewById();

    /**
     * 获取当前页面layout id
     *
     * @return layout id
     */
    @LayoutRes
    protected abstract int getLayoutViewId();

    /**
     * 在setContentView之前执行
     */
    protected void setRequestWindowFeature() {
    }

    /**
     * 打印日志需要的TAG信息
     *
     * @return Tag
     */
    protected abstract String tag();


}
