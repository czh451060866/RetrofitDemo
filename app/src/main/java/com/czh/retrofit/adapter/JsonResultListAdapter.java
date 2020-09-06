package com.czh.retrofit.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.czh.retrofit.domain.JsonResult;
import com.squareup.picasso.Picasso;
import com.yl.retrofitdemo.R;

import java.util.ArrayList;
import java.util.List;



public class JsonResultListAdapter extends RecyclerView.Adapter<JsonResultListAdapter.InnerHolder> {
    private List<JsonResult.DataBean> data = new ArrayList<>();
    @NonNull
    @Override
    public JsonResultListAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_json_result, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonResultListAdapter.InnerHolder holder, int position) {
        ImageView converView = (ImageView) holder.itemView.findViewById(R.id.result_cover);
        TextView titleTv = (TextView) holder.itemView.findViewById(R.id.result_title);
        TextView authorTv = (TextView) holder.itemView.findViewById(R.id.result_author);

        JsonResult.DataBean dataBean = data.get(position);
        titleTv.setText(dataBean.getTitle());
        authorTv.setText(dataBean.getUserName());
        Picasso.get().load("http://10.4.140.63:9102" + dataBean.getCover()).into(converView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(JsonResult jsonResult) {
        data.clear();
        data.addAll(jsonResult.getData());
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
