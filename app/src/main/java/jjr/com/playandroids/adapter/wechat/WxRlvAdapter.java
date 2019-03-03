package jjr.com.playandroids.adapter.wechat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jjr.com.playandroids.R;
import jjr.com.playandroids.beans.wechat.WeChatHistoryBean;

/**
 * Created by 甘之如饴 on 2019/3/1.
 */

public class WxRlvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public List<WeChatHistoryBean.DataBean.DatasBean> mWeChatHistoryBean;
    private onClickListener mListener;

    public WxRlvAdapter(Context context, List<WeChatHistoryBean.DataBean.DatasBean> weChatHistoryBean) {
        mContext = context;
        mWeChatHistoryBean = weChatHistoryBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.wx_rlv_item, null);
        return new WxRlvViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        WxRlvViewHolder holder1 = (WxRlvViewHolder) holder;
        holder1.mAuthor_icon.setText(mWeChatHistoryBean.get(position).getAuthor());
        holder1.mName.setText(mWeChatHistoryBean.get(position).getSuperChapterName() + "/" + mWeChatHistoryBean.get(position).getChapterName());
        holder1.mContent.setText(mWeChatHistoryBean.get(position).getTitle());
        holder1.mTime.setText(mWeChatHistoryBean.get(position).getNiceDate());
        if (mWeChatHistoryBean.get(position).isCollect()) {
            //收藏
            holder1.mCollection.setBackgroundResource(R.drawable.icon_like);
        } else {
            //未收藏
            holder1.mCollection.setBackgroundResource(R.drawable.icon_like_article_not_selected);
        }

        holder1.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });

        holder1.mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onNameClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWeChatHistoryBean.size();
    }

    class WxRlvViewHolder extends RecyclerView.ViewHolder {

        private final TextView mAuthor_icon;
        private final TextView mName;
        private final TextView mContent;
        private final TextView mTime;
        private final ImageView mCollection;
        private final CardView mCard;

        public WxRlvViewHolder(View itemView) {
            super(itemView);
            mAuthor_icon = itemView.findViewById(R.id.wx_item_tv_author_icon);
            mName = itemView.findViewById(R.id.wx_item_tv_author_name);
            mContent = itemView.findViewById(R.id.wx_item_tv_content);
            mTime = itemView.findViewById(R.id.wx_item_tv_time);
            mCollection = itemView.findViewById(R.id.wx_collection);
            mCard = itemView.findViewById(R.id.wx_item_cardview);
        }
    }
    public interface onClickListener{
        void onClick(int position);
        void onNameClick(int position);
    }
    public void setOnClickListener(onClickListener listener){
        mListener = listener;
    }
}
