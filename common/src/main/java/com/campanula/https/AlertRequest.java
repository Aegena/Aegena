package com.campanula.https;


import android.app.Dialog;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.campanula.library.R;

/**
 * package com.campanula.https
 *
 * @author campanula
 * create 2019/1/10
 * since
 * desc
 **/
public class AlertRequest extends Dialog implements ProgressListener {

    private ProgressBar pb;
    private TextView tip;
    private CharSequence message;

    public AlertRequest(Context context) {
        super(context);
        setContentView(R.layout.dialog_async);
        pb = findViewById(R.id.pb);
        tip = findViewById(R.id.tip);
    }


    @Override
    public void setMessage(CharSequence sequence) {
        this.message = sequence;
        tip.setText(message);
    }


    @Override
    public void showProgressDialog() {
        if (!isShowing()) {
            show();
        }
    }

    @Override
    public void closeProgressDialog() {
        if (isShowing()) {
            dismiss();
            onDismissListener();
        }
    }

    @Override
    public void setCancel(boolean cancelable) {
        setCancelable(cancelable);
    }


    @Override
    public void onDismissListener() {
        closeProgressDialog();
    }
}
