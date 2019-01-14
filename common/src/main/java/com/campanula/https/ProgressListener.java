package com.campanula.https;

/**
 * package com.campanula.https
 *
 * @author campanula
 * create 2019/1/10
 * since
 * desc
 **/
public interface ProgressListener {

    void setMessage(CharSequence sequence);

    void showProgressDialog();

    void closeProgressDialog();

    void setCancel(boolean cancelable);

    void onDismissListener();
    
}
