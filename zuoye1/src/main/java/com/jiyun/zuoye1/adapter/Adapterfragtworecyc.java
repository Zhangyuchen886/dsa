package com.jiyun.zuoye1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jiyun.zuoye1.R;
import com.jiyun.zuoye1.bean.FuliBean;

import java.util.ArrayList;
import java.util.List;

public class Adapterfragtworecyc extends RecyclerView.Adapter<Adapterfragtworecyc.ViewHolder> {
    Context context;

    public Adapterfragtworecyc(Context context) {

        this.context = context;
    }
    List<FuliBean.ResultsBean> resultsBeans=new ArrayList<>();

    public List<FuliBean.ResultsBean> getResultsBeans() {
        return resultsBeans;
    }

    public void initData(List<FuliBean.ResultsBean> resultsBeans){
        this.resultsBeans=resultsBeans;
        notifyDataSetChanged();
    }
     public void remove(int i){
        resultsBeans.remove(i);
        notifyDataSetChanged();
     }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.fragtwo_recyc_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        FuliBean.ResultsBean resultsBean = resultsBeans.get(i);
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(resultsBean.getUrl()).apply(requestOptions).into(viewHolder.fragtwo_iv);
        viewHolder.fragtwo_tv.setText(resultsBean.get_id());
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                on.onItemLong(i);
                return false;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on.onItem(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fragtwo_iv;
        TextView fragtwo_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fragtwo_iv=itemView.findViewById(R.id.fragtwo_iv);
            fragtwo_tv=itemView.findViewById(R.id.fragtwo_tv);
        }
    }
    private onItemLongClick on;

    public void setOn(onItemLongClick on) {
        this.on = on;
    }

    public interface  onItemLongClick{
        void onItemLong(int i);
        void onItem(int i);
    }
}
