package com.jiyun.zuoye1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.jiyun.zuoye1.R;
import com.jiyun.zuoye1.bean.ProjectBean;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class AdapterRecyc extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;

    public AdapterRecyc(Context context) {
        this.context = context;
    }

    List<ProjectBean.DataBean.DatasBean> datasBeans=new ArrayList<>();

    public List<ProjectBean.DataBean.DatasBean> getDatasBeans() {
        return datasBeans;
    }

    public void initData(List<ProjectBean.DataBean.DatasBean> datasBeans){
        this.datasBeans.clear();
        this.datasBeans.addAll(datasBeans);
        notifyDataSetChanged();
    }
    public void loadMoreData(List<ProjectBean.DataBean.DatasBean> datasBeans){
        this.datasBeans.addAll(datasBeans);
        notifyDataSetChanged();
    }
    public void remove(int i){
        datasBeans.remove(i);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==0){
            View view = View.inflate(context, R.layout.recyc_item, null);
            return new ViewHolder1(view);
        }else {
            View view = View.inflate(context, R.layout.recyc_item2, null);
            return new ViewHolder2(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int type = getItemViewType(i);
        final ProjectBean.DataBean.DatasBean datasBean = datasBeans.get(i);
        if (type==0){
            ViewHolder1 holder1= (ViewHolder1) viewHolder;
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(context).load(datasBean.getEnvelopePic()).apply(requestOptions).into(holder1.recyc_iv1);
            holder1.recyc_tv1.setText(datasBean.getTitle());
        }else {
            ViewHolder2 holder2= (ViewHolder2) viewHolder;
            RoundedCorners roundedCorners = new RoundedCorners(20);
            RequestOptions override = RequestOptions.bitmapTransform(roundedCorners)
                    .override(100, 100);
            Glide.with(context).load(datasBean.getEnvelopePic()).apply(override).into(holder2.recyc_iv2);
            holder2.recyc_tv2.setText(datasBean.getTitle());
        }
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                on.onItem(i);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return datasBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return 0;
        }else {
            return 1;
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{
        ImageView recyc_iv1;
        TextView recyc_tv1;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            recyc_iv1=itemView.findViewById(R.id.recyc_iv1);
            recyc_tv1=itemView.findViewById(R.id.recyc_tv1);
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder{
        ImageView recyc_iv2;
        TextView recyc_tv2;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            recyc_iv2=itemView.findViewById(R.id.recyc_iv2);
            recyc_tv2=itemView.findViewById(R.id.recyc_tv2);
        }
    }
    private onItemLong on;

    public void setOn(onItemLong on) {
        this.on = on;
    }

    public interface  onItemLong{
        void onItem(int i);
    }
}
