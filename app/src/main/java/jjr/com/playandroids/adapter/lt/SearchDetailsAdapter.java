package jjr.com.playandroids.adapter.lt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.SearchActivity;
import jjr.com.playandroids.beans.fivelistbean.SearchDetails;

public class SearchDetailsAdapter extends RecyclerView.Adapter {
    public List<SearchDetails> list;
    private final SearchActivity context;
    private OnItemClickListener listener;

    public SearchDetailsAdapter(SearchActivity searchActivity, List<SearchDetails> searchDetails) {
        this.context = searchActivity;
        this.list = searchDetails;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.item_search_history_list, null);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.itemSearchHistoryTv.setText(list.get(position).getName());
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

    public void setData(List<SearchDetails> data) {
        if (data != null) {
            list.clear();
            list.addAll(data);
        }
        notifyDataSetChanged();

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_search_history_tv)
        TextView itemSearchHistoryTv;

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
