package jjr.com.playandroids.adapter.fouradapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;

import java.util.List;
import java.util.Random;

import jjr.com.playandroids.R;
import jjr.com.playandroids.beans.fourlistbean.NaviListBean;

public class FourconentAdapter extends RecyclerView.Adapter<FourconentAdapter.ViewHolder> {
    private List<NaviListBean.DataBean> list;
    private Context context;

    public FourconentAdapter(List<NaviListBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.four_conent_item,null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    Random myRandom=new Random();
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
      holder.tv_node_title.setText(list.get(position).getName());
        holder.flow_four.removeAllViews();


        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getArticles().size(); j++) {
                if (holder.tv_node_title.getText().toString().equals(list.get(i).getArticles().get(j).getChapterName())){
                    int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
                    final String chapterName = list.get(i).getArticles().get(j).getTitle();
                    final String link = list.get(i).getArticles().get(j).getLink();
                    final int id = list.get(i).getArticles().get(j).getId();
                    final String author = list.get(i).getArticles().get(j).getAuthor();
                    final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.layout_tv_item, holder.flow_four, false);
                    tv.setText(chapterName);
                    tv.setTextColor(ranColor);
                    holder.flow_four.addView(tv);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(context, chapterName, Toast.LENGTH_SHORT).show();
                            onclickLienter.Click(position,chapterName,link,author,id);
                        }
                    });
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final FlowLayout flow_four;
        private final TextView tv_node_title;

        public ViewHolder(View itemView) {
            super(itemView);
            flow_four = itemView.findViewById(R.id.flow_four);
            tv_node_title = itemView.findViewById(R.id.tv_content);
        }
    }
     //点击事件
         OnclickLienter onclickLienter;

         public void setOnclickLienter(OnclickLienter onclickLienter) {
             this.onclickLienter = onclickLienter;
         }

         public interface OnclickLienter{
             void Click(int position, String name, String url,String author,int id);
         }
}
