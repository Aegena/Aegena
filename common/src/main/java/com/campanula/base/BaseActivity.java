package com.campanula.base;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.campanula.logger.Logger;

/**
 * package com.campanula.base.BaseActivity
 *
 * @author campanula
 * create 2018-12-05
 * @since
 **/
public abstract class BaseActivity extends AppCompatActivity {
    private String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate ----> " + TAG);
        TAG = getTag();
        setRequestWindowFeature();
        setContentView(getLayoutViewId());
        initialize();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Logger.i("onStart ----> " + TAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.i("onStart ----> " + TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.i("onStart ----> " + TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.i("onStart ----> " + TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.i("onStart ----> " + TAG);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.i("onStart ----> " + TAG);
    }

    /**
     * 初始化控件数据
     */
    protected abstract void initialize();

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
    protected abstract String getTag();


}
