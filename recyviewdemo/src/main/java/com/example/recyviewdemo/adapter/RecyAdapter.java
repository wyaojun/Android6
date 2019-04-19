package com.example.recyviewdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyviewdemo.R;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {

    private Context context;
    public String[] str;

    public RecyAdapter(Context context, String[] str) {
        this.context = context;
        this.str = str;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_item.setText(str[i]);
    }

    @Override
    public int getItemCount() {
        return str.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.tv_item);
        }
    }
}
