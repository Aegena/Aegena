package com.campanula.https;

/**
 * package com.campanula.https
 *
 * @author campanula
 * create 2019/1/10
 * since
 * desc
 **/
public interface RequestListener {

    /**
     * 成功时返回数据
     *
     * @param results 返回的数据集合
     * @throws Exception 异常处理
     */
    void onSuccess(Object results) throws Exception;

    /**
     * 失败时的消息体
     *
     * @param message 错误消息体
     * @throws Exception 异常处理
     */
    void onError(String message) throws Exception;

    /**
     * 其他失败处理
     *
     * @param e              异常
     * @param isNetWorkError 是否是网络错误
     * @throws Exception 异常处理
     */
    void onFailure(Throwable e, boolean isNetWorkError) throws Exception;

}
