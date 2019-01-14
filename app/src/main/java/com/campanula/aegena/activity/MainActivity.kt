package com.campanula.aegena.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.campanula.aegena.R
import com.campanula.base.BaseActivity
import com.campanula.https.DialogRequest

/**
 * @author campanula
 * create 2018-10-30
 * @since
 */
class MainActivity : BaseActivity() {
    override fun initialize() {
        mRecyclerView = findViewById(R.id.mRecyclerView)
        mItemsAdapter = ItemsAdapter()
        mRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        mRecyclerView.adapter = mItemsAdapter
        mRecyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
        val waitDialog: DialogRequest = DialogRequest(this)

    }

    override fun getTag(): String {
        return MainActivity::class.java.simpleName
    }

    lateinit var mRecyclerView: RecyclerView
    lateinit var mItemsAdapter: ItemsAdapter


    override fun getLayoutViewId(): Int {
        return R.layout.activity_main
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mImageView: ImageView = itemView.findViewById(R.id.mImageView)
        var mTextView: TextView = itemView.findViewById(R.id.mTextView)
    }

    class ItemsAdapter : RecyclerView.Adapter<ViewHolder>() {
        private val args = arrayOf("", "")
        private val names = arrayOf("QQ红点", "水波纹")
        private val icons = intArrayOf(R.mipmap.ic_launcher)

        override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_activity, parent, false))
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
            viewHolder.itemView.setOnClickListener {
                when (i) {
                    0 -> viewHolder.itemView.context.startActivity(
                        Intent(
                            viewHolder.itemView.context,
                            QQBezierActivity::class.java
                        )
                    )
                    1 -> viewHolder.itemView.context.startActivity(
                        Intent(
                            viewHolder.itemView.context,
                            WavesActivity::class.java
                        )
                    )
                }
            }
            viewHolder.mTextView.text = names[i]
            viewHolder.mImageView.setImageResource(icons[0])
        }

        override fun getItemCount(): Int {
            return names.size
        }
    }


}
