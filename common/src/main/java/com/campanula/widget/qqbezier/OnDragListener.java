package com.campanula.widget.qqbezier;

/**
 * package com.campanula.library.widget.qqbezier
 *
 * @author 000286
 * create 2018-11-01
 * desc
 **/
public interface OnDragListener {

    /**
     * 拖拽
     */
    void onDrag();

    /**
     * 移动
     */
    void onMove();

    /**
     * 还原
     */
    void onRestore();

    /**
     * 消失
     */
    void onDismiss();
}
