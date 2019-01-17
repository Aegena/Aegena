package com.campanula.aegena.activity


import android.widget.Button
import android.widget.Toast
import com.campanula.aegena.R
import com.campanula.aegena.service.HttpService
import com.campanula.base.BaseActivity
import com.campanula.https.*

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
                .setObservable(
                    Retrofits.create(HttpService::class.java)
                        .getExpress("yuantong", "200382770316")
                )
                .setObserverListener(null)
                .setProgressListener(DialogRequest(this))
                .setRequestListener(null)
                .with(this)

        }
    }

    override fun getLayoutViewId(): Int {
        return R.layout.activity_request
    }

    override fun getTag(): String {
        return RequestActivity::class.java.simpleName
    }

}