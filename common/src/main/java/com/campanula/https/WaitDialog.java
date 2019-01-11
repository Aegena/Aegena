package com.campanula.https;


import android.app.Dialog;
import android.content.Context;

/**
 * package com.campanula.https
 *
 * @author campanula
 * create 2019/1/10
 * since
 * desc
 **/
public class WaitDialog extends Dialog implements ProgressListener {

    public WaitDialog(Context context) {
        super(context);

    }


    @Override
    public void setMessage(CharSequence sequence) {

    }

    @Override
    public void showProgressDialog() {
        show();
    }

    @Override
    public void closeProgressDialog() {
        if (isShowing()) {
            dismiss();
        }
    }
}
