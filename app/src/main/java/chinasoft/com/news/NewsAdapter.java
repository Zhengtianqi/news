package chinasoft.com.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


import static android.view.View.GONE;

public class NewsAdapter extends RecyclerView.Adapter {
    private List<NewsBean.ResultBean.DataBean> newsData;
    Context context;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsitem, null, false);
        return (new NewsViewHolder(view));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder && newsData != null) {
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            NewsBean.ResultBean.DataBean data = newsData.get(position);
            //将数据绑定
            newsViewHolder.title.setText(data.getTitle());
            newsViewHolder.author.setText(data.getAuthor_name());
            newsViewHolder.date.setText(data.getDate());

            String pic1path = data.getThumbnail_pic_s();
            if (pic1path != null) {
                Glide.with(context).load(pic1path).placeholder(R.drawable.progress).crossFade().into(newsViewHolder.pic1);
            } else {
                newsViewHolder.pic1.setVisibility(GONE);
            }

            final String url = data.getUrl();
            newsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                //点击item传值，url给webview,跳转到webactivity
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, WebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("URL", url);
                    intent.putExtra("extra", bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (newsData != null) return newsData.size();
        return 0;
    }

    public void setNews(NewsBean newsBean) {
        this.newsData = newsBean.getResult().getData();
        notifyDataSetChanged();
    }


    class NewsViewHolder extends RecyclerView.ViewHolder {
        //填充
        @BindView(R.id.tv_news_title)
        public TextView title;
        @BindView(R.id.tv_news_author)
        public TextView author;
        @BindView(R.id.tv_news_date)
        public TextView date;
        @BindView(R.id.iv_news_pic1)
        public ImageView pic1;


        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
