package com.jiyun.zhangyuchenqi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.zhangyuchenqi.R;
import com.jiyun.zhangyuchenqi.bean.Infos;

import java.util.ArrayList;

public class RecycAdapter extends RecyclerView.Adapter<RecycAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Infos.RecentBean> list;

    public RecycAdapter(Context context, ArrayList<Infos.RecentBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取布局
        View view = View.inflate(context, R.layout.recycitem, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //设置属性
        viewHolder.tvname.setText(list.get(i).getTitle());
        //加载图片
        Glide.with(context).load(list.get(i).getThumbnail()).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.ItemShow(v,i);
            }
        });

        //长按方法
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showLong.ItemLong(i);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            tvname=itemView.findViewById(R.id.tvname);
        }
    }

    private ItemshowClick show;

    public void setShow(ItemshowClick show) {
        this.show = show;
    }

    public interface  ItemshowClick{
        void ItemShow(View v,int position);
    }

    private itemshowLongClick showLong;

    public void setShowLong(itemshowLongClick showLong) {
        this.showLong = showLong;
    }

    public interface  itemshowLongClick{
        void ItemLong(int position);
    }
}
