package com.campanula.aegena.activity


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.campanula.aegena.R
import com.campanula.library.base.BaseActivity
import com.campanula.library.widget.qqbezier.QQBezierView
import com.google.android.material.tabs.TabLayout

/**
 * package com.campanula.aegena.activity
 * @author 000286
 * create 2018-11-06
 * desc
 **/
class QQBezierActivity : BaseActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mTabLayout: TabLayout
    private lateinit var items: MutableList<String>
    private lateinit var mBezierAdapter: BezierAdapter

    override fun bindData() {

        items = arrayListOf()

        for (i in 0 until 100) {
            items.add(i, i.toString())
        }

        mBezierAdapter = BezierAdapter()
        mRecyclerView.adapter = mBezierAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }

    override fun viewById() {
        mRecyclerView = findViewById(R.id.mRecyclerView)
        mTabLayout = findViewById(R.id.mTabLayout)
    }

    override fun getLayoutViewId() = R.layout.activity_qq_bezier


    override fun tag(): String {
        return QQBezierActivity::class.java.simpleName
    }

    class BezierHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTextView: TextView = itemView.findViewById(R.id.mTextView)
        val mQQBezierView: QQBezierView = itemView.findViewById(R.id.mQQBezierView)
    }

    inner class BezierAdapter : RecyclerView.Adapter<BezierHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): BezierHolder {
            return BezierHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bezier_qq_activity, parent, false))
        }

        override fun getItemCount(): Int {
            return this@QQBezierActivity.items.size
        }

        override fun onBindViewHolder(holder: BezierHolder, position: Int) {
            holder.mTextView.text = position.toString()
            holder.mQQBezierView.text = position.toString()
        }

    }
}