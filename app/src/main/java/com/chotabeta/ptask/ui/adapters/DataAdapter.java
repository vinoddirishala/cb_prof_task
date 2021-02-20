package com.chotabeta.ptask.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chotabeta.ptask.R;
import com.chotabeta.ptask.beans.Data;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinod Dirishala on 20-02-2021 11:55
 **/

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private Context mContext;
    private ArrayList<Data.DataBean> data;

    public DataAdapter(Context mContext, ArrayList<Data.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_data,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Data.DataBean d =  data.get(position);
        holder.title.setText(d.getTitle());
        holder.description.setText(d.getDescription());

        Log.d("imageHref:",position+":"+d.getImageHref());

        Glide.with(mContext)
                .load(d.getImageHref() != null ? d.getImageHref():R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.thumbNail);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addData(ArrayList<Data.DataBean> dataBeans) {
        data.addAll(dataBeans);
        notifyDataSetChanged();
    }


    public class DataViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title) TextView title;
        @BindView(R.id.descrption) TextView description;
        @BindView(R.id.thumbNail) ImageView thumbNail;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
