package com.campanula.widget.qqbezier;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * package com.campanula.library.widget.qqbezier
 *
 * @author 000286
 * create 2018-11-01
 * desc
 **/
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        Status.STATUS_INIT,
        Status.STATE_DRAG,
        Status.STATE_MOVE,
        Status.STATE_DISMISS
})
public @interface Status {
    /**
     * 初始状态
     */
    int STATUS_INIT = 0;
    /**
     * 滑动状态
     */
    int STATE_DRAG = 1;
    /**
     * 移动中
     */
    int STATE_MOVE = 2;
    /**
     * 消失
     */
    int STATE_DISMISS = 3;
}
