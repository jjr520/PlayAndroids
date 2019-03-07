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

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.SearchDetailsActivity;
import jjr.com.playandroids.activitys.wechat.WxShowSimpleActivity;
import jjr.com.playandroids.beans.fivelistbean.Demo;
import jjr.com.playandroids.beans.fivelistbean.SearchBean;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.utils.litao.CollectData;
import jjr.com.playandroids.utils.litao.CollectUtils;

public class SearchListAdapter extends RecyclerView.Adapter {
    public List<SearchBean.DataBean.DatasBean> list;
    private final SearchDetailsActivity context;
    private OnItemClickListener listener;
    private ViewHolder mViewHolders;
    private String ids;
    private boolean state;

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

        mViewHolders = (ViewHolder) holder;
        mViewHolders.wxItemTvAuthorIcon.setText(list.get(position).getChapterName());
        mViewHolders.wxItemTvContent.setText(list.get(position).getTitle());
        mViewHolders.wxItemTvTime.setText(list.get(position).getNiceDate() + "");
        if (list.get(position).isCollect() == true) {
            mViewHolders.wxCollection.setImageResource(R.drawable.icon_like);
            Log.e("leixing1", "" + list.get(position).isCollect() + list.get(position));
        } else {
            mViewHolders.wxCollection.setImageResource(R.drawable.icon_like_article_not_selected);
            Log.e("leixing2", "" + list.get(position).isCollect() + list.get(position));
        }

        mViewHolders.wxCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean collect = list.get(position).isCollect();
                if (collect == true) {
                    mViewHolders.wxCollection.setImageResource(R.drawable.icon_like_article_not_selected);
                    list.get(position).setCollect(false);
                    /*   notifyItemChanged(position, false);*/
                    notifyDataSetChanged();
                    context.presenter.getDataFiveP(OnlyFive.CANCELCONTENT, list.get(position).getId());
                    // EventBus.getDefault().postSticky(new Demo(list.get(position).getId() + "", false));
                } else {
                    mViewHolders.wxCollection.setImageResource(R.drawable.icon_like);
                    list.get(position).setCollect(true);
                    notifyDataSetChanged();
                    context.presenter.getDataFiveP(OnlyFive.CONTENT, list.get(position).getId());
                    // EventBus.getDefault().postSticky(new Demo(list.get(position).getId() + "", true));
                }


            }
        });
        mViewHolders.wxItemTvAuthorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WxShowSimpleActivity.class);
                intent.putExtra("name", list.get(position).getChapterName());
                intent.putExtra("id", list.get(position).getChapterId());
                context.startActivity(intent);
            }
        });
        mViewHolders.itemView.setOnClickListener(new View.OnClickListener() {
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
        if (page == 0) {
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

        void onlistener(int postion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
