package com.campanula.angena;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.campanula.library.base.BaseActivity;

/**
 * @author campanula
 * create 2018-10-30
 * @since
 */
public class MainActivity extends BaseActivity {

    RecyclerView mRecyclerView;
    ItemsAdapter mItemsAdapter;

    @Override
    protected void bindViewData() {
        mItemsAdapter = new ItemsAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mItemsAdapter);

    }

    @Override
    protected void findActivityViewById() {
        mRecyclerView = findViewById(R.id.mRecyclerView);
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.main_activity;
    }

    @Override
    protected String tag() {
        return MainActivity.class.getSimpleName();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.mImageView);
            mTextView = itemView.findViewById(R.id.mTextView);
        }
    }

    static class ItemsAdapter extends RecyclerView.Adapter<ViewHolder> {
        String[] args = new String[]{
                "",
                ""
        };
        String[] names = new String[]{
                "QQ红点",
                "水波纹"
        };
        int[] icons = new int[]{
                R.mipmap.ic_launcher
        };

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int itemType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_activity, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            viewHolder.mTextView.setText(names[i]);
            viewHolder.mImageView.setImageResource(icons[0]);
        }

        @Override
        public int getItemCount() {
            return args.length;
        }
    }


}
