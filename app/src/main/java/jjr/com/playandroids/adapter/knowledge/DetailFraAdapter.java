package jjr.com.playandroids.adapter.knowledge;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jjr.com.playandroids.R;
import jjr.com.playandroids.beans.knowbean.KnowDetailsBean;


/**
 * Created by jjr on 2019/3/1.
 */

public class DetailFraAdapter extends RecyclerView.Adapter {

    public ArrayList<KnowDetailsBean.DataBean.DatasBean> mDatas;
    private final Activity mActivity;
    private String mSuperChapterName;
    private OnClickListener mOnClick;
    private OnClickListener mOnLike;

    public DetailFraAdapter(ArrayList<KnowDetailsBean.DataBean.DatasBean> datasBeans, Activity activity, String superChapterName) {

        mDatas = datasBeans;
        mActivity = activity;
        mSuperChapterName = superChapterName;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_know_pager, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.mAuthor.setText(mDatas.get(position).getAuthor());
        holder1.mChapterName.setText(mSuperChapterName + "/" + mDatas.get(position).getChapterName());
        holder1.mTitle.setText(mDatas.get(position).getTitle());
        holder1.mNiceDate.setText(mDatas.get(position).getNiceDate());

        if (mDatas.get(position).collect) {
            holder1.mLikeIv.setImageResource(R.drawable.icon_like);
        } else {
            holder1.mLikeIv.setImageResource(R.drawable.icon_like_article_not_selected);
        }

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClick != null) {
                    mOnClick.onClickListener(v, position);
                }
            }
        });
        holder1.mLikeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLike != null) {
                    mOnLike.onLikeClickListener(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
            return;
        }
        if (holder instanceof ViewHolder) {
            if ((Boolean) payloads.get(0)) {
                ((ViewHolder) holder).mLikeIv.setImageResource(R.drawable.icon_like);
            } else {
                ((ViewHolder) holder).mLikeIv.setImageResource(R.drawable.icon_like_article_not_selected);
            }

        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_search_pager_author)
        TextView mAuthor;
        @BindView(R.id.item_search_pager_chapterName)
        TextView mChapterName;
        @BindView(R.id.item_search_pager_title)
        TextView mTitle;
        @BindView(R.id.item_search_pager_tag_red_tv)
        TextView mTagRedTv;
        @BindView(R.id.item_search_pager_tag_green_tv)
        TextView mTagGreenTv;
        @BindView(R.id.item_search_tag_group)
        LinearLayout mTagGroup;
        @BindView(R.id.item_search_pager_like_iv)
        ImageView mLikeIv;
        @BindView(R.id.item_search_pager_niceDate)
        TextView mNiceDate;
        @BindView(R.id.item_search_pager_group)
        LinearLayout mGroup;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //mAdapter.setOnClickListener((GankItemAdapter.OnClickListener) mActivity);
    public interface OnClickListener {
        void onClickListener(View v, int position);

        void onLikeClickListener(View v, int position);
    }

    public void setOnClickListener(OnClickListener OnClick) {
        mOnClick = OnClick;
        mOnLike = OnClick;
    }


}
