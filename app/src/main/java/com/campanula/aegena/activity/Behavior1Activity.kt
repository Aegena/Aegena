package com.campanula.aegena.activity

import com.campanula.aegena.R
import com.campanula.base.BaseActivity

/**
 * package com.campanula.aegena.activity
 * @author 000286
 * create 2018-11-07
 * desc
 **/
class Behavior1Activity : BaseActivity() {
    override fun bindData() {

    }

    override fun viewById() {
    }

    override fun getLayoutViewId(): Int {
        return R.layout.behavior1_acitivty
    }

    override fun tag(): String {
        return Behavior1Activity::class.java.simpleName
    }
}