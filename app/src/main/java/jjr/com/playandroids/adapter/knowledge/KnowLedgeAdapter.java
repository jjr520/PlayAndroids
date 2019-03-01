package jjr.com.playandroids.adapter.knowledge;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jjr.com.playandroids.R;
import jjr.com.playandroids.beans.knowbean.KonwDataBean;
import jjr.com.playandroids.utils.CommonUtils;
import jjr.com.playandroids.utils.SystemUtil;

/**
 * Created by jjr on 2019/2/28.
 */

public class KnowLedgeAdapter extends RecyclerView.Adapter {
    private final Activity mActivity;
    public List<KonwDataBean.DataBean> mData;
    private OnClickListener mOnClick;


    public KnowLedgeAdapter(Activity activity, List<KonwDataBean.DataBean> data) {

        mActivity = activity;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_knowledge, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.mKnowTitle.setText(mData.get(position).getName());
        holder1.mKnowTitle.setTextColor(CommonUtils.randomColor());

        StringBuilder content = new StringBuilder();
        List<KonwDataBean.DataBean.ChildrenBean> children = mData.get(position).getChildren();
        for (KonwDataBean.DataBean.ChildrenBean child : children) {
            content.append(child.getName()).append("   ");
        }
        holder1.mKnowContent.setText(content);

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClick != null) {
                    mOnClick.onClickListener(v, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.know_iv)
        ImageView mKnowIv;
        @BindView(R.id.know_title)
        TextView mKnowTitle;
        @BindView(R.id.know_content)
        TextView mKnowContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickListener {
        void onClickListener(View v, int position);
    }

    public void setOnClickListener(OnClickListener OnClick) {
        mOnClick = OnClick;
    }


}
