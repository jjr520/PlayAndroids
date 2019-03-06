package jjr.com.playandroids.adapter.lt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.SearchDetailsActivity;
import jjr.com.playandroids.activitys.wechat.WxShowSimpleActivity;
import jjr.com.playandroids.beans.fivelistbean.SearchBean;
import jjr.com.playandroids.utils.litao.CollectData;
import jjr.com.playandroids.utils.litao.CollectUtils;

public class SearchListAdapter extends RecyclerView.Adapter {
    public List<SearchBean.DataBean.DatasBean> list;
    private final SearchDetailsActivity context;
    private OnItemClickListener listener;

    public SearchListAdapter(SearchDetailsActivity searchDetailsActivity, List<SearchBean.DataBean.DatasBean> datas) {
        this.context = searchDetailsActivity;
        this.list = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.wx_rlv_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.wxItemTvAuthorIcon.setText(list.get(position).getChapterName());
        viewHolder.wxItemTvContent.setText(list.get(position).getTitle());
        viewHolder.wxItemTvTime.setText(list.get(position).getNiceDate() + "");
        if (CollectData.getCollectDataInstance().selectSingle(list.get(position).getLink(), list.get(position).getTitle()) != null) {
            if (CollectData.getCollectDataInstance().selectSingle(list.get(position).getLink(), list.get(position).getTitle()).getState() == true) {

               // Glide.with(context).load(R.drawable.icon_like_article_not_selected).into(viewHolder.wxCollection);
                viewHolder.wxCollection.setImageResource(R.drawable.icon_like);
            } else {
                viewHolder.wxCollection.setImageResource(R.drawable.icon_like_article_not_selected);
              //  Glide.with(context).load(R.drawable.icon_like).into(viewHolder.wxCollection);
            }
        }/*else{
            Glide.with(context).load(R.drawable.icon_like_article_not_selected).into(viewHolder.wxCollection);
        }*/
        viewHolder.wxCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectUtils collectUtils = CollectData.getCollectDataInstance().selectSingle(list.get(position).getLink(), list.get(position).getTitle());
                if(collectUtils == null){
                    CollectUtils collectUtils1 = new CollectUtils(null, list.get(position).getLink(), list.get(position).getTitle(), "", "", 0, true);
                    CollectData.getCollectDataInstance().insert(collectUtils1);
                   // Glide.with(context).load(R.drawable.icon_like).into(viewHolder.wxCollection);
                    viewHolder.wxCollection.setImageResource(R.drawable.icon_like);
                }else{
                    if(collectUtils.getState()==false){
                        collectUtils.setState(true);
                        CollectData.getCollectDataInstance().update(collectUtils);
                        viewHolder.wxCollection.setImageResource(R.drawable.icon_like);
                       // Glide.with(context).load(R.drawable.icon_like_article_not_selected).into(viewHolder.wxCollection);
                    }else{
                        collectUtils.setState(false);
                        CollectData.getCollectDataInstance().update(collectUtils);
                       // Glide.with(context).load(R.drawable.icon_like_article_not_selected).into(viewHolder.wxCollection);
                        viewHolder.wxCollection.setImageResource(R.drawable.icon_like_article_not_selected);
                    }
                }
            }
        });
        viewHolder.wxItemTvAuthorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WxShowSimpleActivity.class);
                intent.putExtra("name", list.get(position).getChapterName());
                intent.putExtra("id", list.get(position).getChapterId());
                context.startActivity(intent);
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickListener(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<SearchBean.DataBean.DatasBean> data, int page) {
        if (page != 0) {
            list.clear();
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.wx_author_icon)
        ImageView wxAuthorIcon;
        @BindView(R.id.wx_item_tv_author_icon)
        TextView wxItemTvAuthorIcon;
        @BindView(R.id.wx_item_tv_author_name)
        TextView wxItemTvAuthorName;
        @BindView(R.id.wx_item_tv_content)
        TextView wxItemTvContent;
        @BindView(R.id.wx_collection)
        ImageView wxCollection;
        @BindView(R.id.wx_item_tv_time)
        TextView wxItemTvTime;
        @BindView(R.id.wx_item_cardview)
        CardView wxItemCardview;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onClickListener(View v, int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
