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
import com.jiyun.zuoye1.bean.AndroidBean;

import java.util.ArrayList;
import java.util.List;

public class Adapterfragfourrecyc extends RecyclerView.Adapter<Adapterfragfourrecyc.ViewHolder> {
    Context context;

    public Adapterfragfourrecyc(Context context) {

        this.context = context;
    }
    List<AndroidBean> resultsBeans=new ArrayList<>();

    public List<AndroidBean> getResultsBeans() {
        return resultsBeans;
    }

    public void initData(List<AndroidBean> resultsBeans){
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
        final AndroidBean resultsBean = resultsBeans.get(i);
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(resultsBean.getEnvelopePic()).apply(requestOptions).into(viewHolder.fragtwo_iv);
        viewHolder.fragtwo_tv.setText(resultsBean.getTitle());
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                on.onItemLong(resultsBean,i);
                return false;
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
        void onItemLong(AndroidBean androidBean, int i);

    }
}
