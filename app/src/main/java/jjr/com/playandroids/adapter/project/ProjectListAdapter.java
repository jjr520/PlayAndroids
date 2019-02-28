package jjr.com.playandroids.adapter.project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jjr.com.playandroids.R;
import jjr.com.playandroids.beans.fivelistbean.ProjectListBean;

public class ProjectListAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<ProjectListBean.DataBean.DatasBean> list;

    public ProjectListAdapter(Context context, List<ProjectListBean.DataBean.DatasBean> datas) {
        this.list = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.item_project_list, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.itemProjectListAuthorTv.setText(list.get(position).getAuthor());
        viewHolder.itemProjectListContentTv.setText(list.get(position).getDesc());
        viewHolder.itemProjectListTimeTv.setText(list.get(position).getPublishTime() + "");
        viewHolder.itemProjectListTitleTv.setText(list.get(position).getTitle());
       // Glide.with(context).load(list.get(position).getEnvelopePic()).into(viewHolder.itemProjectListIv);

    }

    @Override
    public int getItemCount() {
        if(list == null){
            return 0;
        }
        return list.size();
    }

    public void setData(List<ProjectListBean.DataBean.DatasBean> data) {
        if(list != null){
            list.clear();
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_project_list_iv)
        ImageView itemProjectListIv;
        @BindView(R.id.item_project_list_title_tv)
        TextView itemProjectListTitleTv;
        @BindView(R.id.item_project_list_content_tv)
        TextView itemProjectListContentTv;
        @BindView(R.id.item_project_list_time_tv)
        TextView itemProjectListTimeTv;
        @BindView(R.id.item_project_list_author_tv)
        TextView itemProjectListAuthorTv;
        @BindView(R.id.item_project_list_install_tv)
        TextView itemProjectListInstallTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
