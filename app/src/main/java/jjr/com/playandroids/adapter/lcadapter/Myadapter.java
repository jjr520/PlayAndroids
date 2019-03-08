package jjr.com.playandroids.adapter.lcadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jjr.com.playandroids.R;
import jjr.com.playandroids.beans.one.Articlebean;
import jjr.com.playandroids.beans.one.Bannerbean;

public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Articlebean.DataBean.DatasBean> artilist;
    private List<Bannerbean.DataBean> bannerlist;
    private List<String> iamgs= new ArrayList<>();
    private List<String> names= new ArrayList<>();
    private Litao litao;

    public void setLitao(Litao litao) {
        this.litao = litao;
    }

    public Myadapter(List<Articlebean.DataBean.DatasBean> artilist, List<Bannerbean.DataBean> bannerlist) {
        this.artilist = artilist;
        this.bannerlist = bannerlist;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.bannneritem, null);
            return new ViewHolderB(inflate);
        } else {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.wx_rlv_item, null);
            return new ViewHolderA(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolderB){
            if (iamgs.size()<7){
                for (Bannerbean.DataBean bean :bannerlist){
                    iamgs.add(bean.getImagePath());
                    names.add(bean.getTitle());
                }
                ((ViewHolderB) holder).banner.setImages(iamgs)
                        .setImageLoader(new image())
                        .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                        .setBannerAnimation(Transformer.DepthPage)
                        .isAutoPlay(true)
                        .setDelayTime(4000)
                        .setIndicatorGravity(BannerConfig.CENTER)
                        .setBannerTitles(names);
                if (bannerlist.size()!=0){
                    ((ViewHolderB) holder).banner.start();
                }
            }

            ((ViewHolderB) holder).banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    litao.LitaoISSB(position);
                }
            });
        }else if (holder instanceof ViewHolderA){
            final int pson = position-1;
            Articlebean.DataBean.DatasBean mWeChatHistoryBean = artilist.get(pson);
            Log.e("showDataOne","artilist"+artilist);
           ViewHolderA holder1 = (ViewHolderA) holder;
            holder1.mAuthor_icon.setText(mWeChatHistoryBean.getAuthor());
            holder1.mAuthor_icon.setTextSize(15);
            holder1.mName.setText(mWeChatHistoryBean.getSuperChapterName() + "/" + mWeChatHistoryBean.getChapterName());
            holder1.mName.setTextSize(15);
            ((ViewHolderA) holder).mName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    litao.Myname(pson);
                }
            });
            holder1.mContent.setText(Html.fromHtml(mWeChatHistoryBean.getTitle()));
            holder1.mContent.setTextSize(15);
            holder1.mTime.setText(mWeChatHistoryBean.getNiceDate());
            holder1.mTime.setTextSize(15);

            if (mWeChatHistoryBean.getSuperChapterName().contains("项目")){
                ((ViewHolderA) holder).red.setVisibility(View.VISIBLE);
                ((ViewHolderA) holder).red.setText("项目");
                ((ViewHolderA) holder).red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        litao.Mypoject();
                    }
                });
            }else {
                ((ViewHolderA) holder).red.setVisibility(View.GONE);
            }
            if (mWeChatHistoryBean.getNiceDate()
                    .contains("分钟")||mWeChatHistoryBean.getNiceDate().contains("小时")||
                    mWeChatHistoryBean.getNiceDate().contains("1天")){
                ((ViewHolderA) holder).greed.setVisibility(View.VISIBLE);
                ((ViewHolderA) holder).greed.setText("新");
            }else {
                ((ViewHolderA) holder).greed.setVisibility(View.GONE);
            }
            if (mWeChatHistoryBean.isCollect()) {
                //收藏
                Log.e("李涛","true");
                holder1.mCollection.setImageResource(R.drawable.icon_like);
            } else {
                //未收藏
                Log.e("李涛","false");
                holder1.mCollection.setImageResource(R.drawable.icon_like_article_not_selected);
            }
            ((ViewHolderA) holder).mCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    litao.YISSB(pson);
                }
            });
            holder1.mCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    litao.IISSB(pson);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return artilist.size()+1;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()){
            super.onBindViewHolder(holder, position, payloads);
            return;
        }

        if (holder instanceof ViewHolderA){
            if ((Boolean) payloads.get(0)){
                ((ViewHolderA) holder).mCollection.setImageResource(R.drawable.icon_like);
            }else {
                ((ViewHolderA) holder).mCollection.setImageResource(R.drawable.icon_like_article_not_selected);
            }

        }
    }
    static class ViewHolderA extends RecyclerView.ViewHolder {
        private final TextView mAuthor_icon;
        private final TextView mName;
        private final TextView mContent;
        private final TextView mTime;
        private final ImageView mCollection;
        private final CardView mCard;
        private final TextView red;
        private final TextView greed;
        ViewHolderA(View view) {
            super(view);
            red = itemView.findViewById(R.id.Mynew);
            greed = itemView.findViewById(R.id.Myproject);
            mAuthor_icon = itemView.findViewById(R.id.wx_item_tv_author_icon);
            mName = itemView.findViewById(R.id.wx_item_tv_author_name);
            mContent = itemView.findViewById(R.id.wx_item_tv_content);
            mTime = itemView.findViewById(R.id.wx_item_tv_time);
            mCollection = itemView.findViewById(R.id.wx_collection);
            mCard = itemView.findViewById(R.id.wx_item_cardview);
        }
    }

    static class ViewHolderB extends RecyclerView.ViewHolder{
        @BindView(R.id.banner)
        Banner banner;
        ViewHolderB(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    private class image extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
    public interface Litao{
        void IISSB(int position);
        void LitaoISSB(int position);
        void YISSB(int position);
        void Myname(int position);
        void Mypoject();
    }
}
