package jjr.com.playandroids.adapter.collect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import jjr.com.playandroids.R;
import jjr.com.playandroids.beans.collect.CollectListBean;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolder> {
    private List<CollectListBean.DataBean.DatasBean> list;
    private Context context;

    public CollectAdapter(List<CollectListBean.DataBean.DatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.collect_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (list.get(position).getAuthor()!=null){
            holder.wx_item_tv_collect.setText(list.get(position).getAuthor());
            holder.wx_name_tv_collect.setText(list.get(position).getAuthor());
        }
        /*holder.wx_name_tv_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickLienter.Click(position);
            }
        });*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickLienter.Click(position);
            }
        });
        holder.wx_item_title_collection.setText(list.get(position).getTitle());
        holder.wx_item_tv_time_collect.setText(list.get(position).getNiceDate());
        //holder.wx_collect.setBackgroundResource(R.drawable.icon_like);
        holder.wx_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coolectLienter.Clickcollect(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView wx_item_tv_collect;
        private final CardView wx_item_collect;
        private final TextView wx_name_tv_collect;
        private final TextView wx_item_title_collection;
        private final ImageView wx_collect;
        private final TextView wx_item_tv_time_collect;

        public ViewHolder(View itemView) {
            super(itemView);
            wx_item_tv_collect = itemView.findViewById(R.id.wx_item_tv_collect);
            wx_item_collect = itemView.findViewById(R.id.wx_item_collect);
            wx_name_tv_collect = itemView.findViewById(R.id.wx_name_tv_collect);
            wx_item_title_collection = itemView.findViewById(R.id.wx_item_title_collection);
            wx_collect = itemView.findViewById(R.id.wx_collect);
            wx_item_tv_time_collect = itemView.findViewById(R.id.wx_item_tv_time_collect);
        }
    }
     //点击事件
         OnclickLienter onclickLienter;

         public void setOnclickLienter(OnclickLienter onclickLienter) {
             this.onclickLienter = onclickLienter;
         }

         public interface OnclickLienter{
             void Click(int position);
         }

    CoolectLienter coolectLienter;

    public void setCoolectLienter(CoolectLienter coolectLienter) {
        this.coolectLienter = coolectLienter;
    }

    public interface CoolectLienter{
        void Clickcollect(int position);
    }
}
