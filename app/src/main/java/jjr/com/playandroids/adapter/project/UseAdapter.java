package jjr.com.playandroids.adapter.project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.Useful_sitessActivity;
import jjr.com.playandroids.beans.fivelistbean.UseListBean;
import jjr.com.playandroids.lt.MyUseView;

public class UseAdapter extends RecyclerView.Adapter {
    public ArrayList<UseListBean.DataBean> list;
    private final Useful_sitessActivity context;

    public UseAdapter(ArrayList<UseListBean.DataBean> dataBeans, Useful_sitessActivity useful_sitessActivity) {
        this.list = dataBeans;
        this.context = useful_sitessActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.useitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        for (UseListBean.DataBean dataBean : list) {
            Log.e("流式布局", dataBean.getName());
            //Button tv = (Button) LayoutInflater.from(context).inflate(R.layout.usebutton, viewHolder.myUseView, false);
            Button button = new Button(context);
            button.setText(dataBean.getName());
            viewHolder.myUseView.addView(button);
            // tv.setText(dataBean.getName());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<UseListBean.DataBean> data) {
        if (list != null) {
            list.clear();
            list.addAll(data);
        }
        notifyDataSetChanged();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.myUseView)
        MyUseView myUseView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
