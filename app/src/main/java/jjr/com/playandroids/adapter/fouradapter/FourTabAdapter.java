package jjr.com.playandroids.adapter.fouradapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import jjr.com.playandroids.R;
import jjr.com.playandroids.beans.fourlistbean.NaviListBean;

public class FourTabAdapter extends RecyclerView.Adapter<FourTabAdapter.ViewHolder> {
    private List<NaviListBean.DataBean.ArticlesBean> list2;
    private Context context;
    private int mPosition;
    public FourTabAdapter(List<NaviListBean.DataBean.ArticlesBean> list2) {
        this.list2 = list2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.four_tab_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_tab.setText(list2.get(position).getChapterName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onclickLienter.Click(position);
            }
        });

    }
    public void getColor(int posi){
        mPosition = posi;
    }
    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_tab;
        private final LinearLayout linear_four;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_tab = itemView.findViewById(R.id.tv_tab);
            linear_four = itemView.findViewById(R.id.linear_four);
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
}
