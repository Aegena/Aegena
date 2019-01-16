package com.campanula.aegena.activity


import android.widget.Button
import android.widget.Toast
import com.campanula.aegena.R
import com.campanula.aegena.service.HttpService
import com.campanula.base.BaseActivity
import com.campanula.https.*
import com.campanula.logger.Logger
import java.util.*

/**
 * package com.campanula.aegena.activity
 * @author campanula
 * date 2019/1/16
 * desc
 **/
class RequestActivity : BaseActivity() {
    lateinit var btn: Button
    override fun initialize() {
        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            Toast.makeText(this, "setOnClickListener", Toast.LENGTH_LONG).show()

            Request.getInstance()
//                .with(this)
                .setObservable(
                    AsyncRequest.create(HttpService::class.java)
                        .getExpress("yuantong", "200382770316")
                ).setObserver(
                    this,
                    AsyncObserver(),
                    object : RequestListener<List<Object>> {
                        override fun onSuccees(results: List<Object>?) {
                            Logger.e("result")
                        }

                        override fun onError(message: String?, code: Int) {
                            Logger.e("result  " + message)
                        }

                        override fun onFailure(e: Throwable?, isNetWorkError: Boolean) {
                            Logger.e("result  " + e?.message)
                        }
                    },
                    DialogRequest(this)
                )
        }
    }

    override fun getLayoutViewId(): Int {
        return R.layout.activity_request
    }

    override fun getTag(): String {
        return RequestActivity::class.java.simpleName
    }

}