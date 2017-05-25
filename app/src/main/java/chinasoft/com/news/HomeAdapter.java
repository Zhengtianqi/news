package chinasoft.com.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter{

    private final int TYPE_HEAD = 0;//表示首个位置，直接显示轮播图片
    private final int TYPE_NORMAL = 1; //表示正常的item布局
    private final int TYPE_FOOT =2;//底部item

    private Context mContext;
    private List<Data> item_data; //轮播图片的路径
    private BannerBean bannerBean;

    public HomeAdapter(Context context, List<Data> item_data, BannerBean bannerBean){
        this.mContext = context;
        this.item_data = item_data;
        this.bannerBean = bannerBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;

        if(viewType == TYPE_HEAD){
            //此处要创建顶部banner的viewHolder
            holder = new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.itemheader_banner,
                    parent,false));
        }else if(viewType == TYPE_NORMAL){
            holder = new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home,parent
                    ,false));
        }else  if(viewType == TYPE_FOOT){
            holder = new FootViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.itemfooter,parent,false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  BannerViewHolder){
            BannerViewHolder bannerViewHolder = (BannerViewHolder)holder;

            bannerViewHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            bannerViewHolder.banner.setIndicatorGravity(BannerConfig.RIGHT);
            bannerViewHolder.banner.setBannerTitle(bannerBean.getTitle());
            bannerViewHolder.banner.setImages(bannerBean.getImg());
        }else if(holder instanceof  ItemViewHolder){
            //处理每个item里的布局
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            itemViewHolder.simpleView.setImageURI(item_data.get(position-1).getThumbnail());
            itemViewHolder.textVeiw.setText(item_data.get(position-1).getTitle());

            if (listener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getLayoutPosition();
//                        Log.i("first", "onClick: ++++++++++++++++++++");
                        listener.onClick(pos);
                    }
                });
            }
        }else if(holder instanceof FootViewHolder){
            //没有数据是不显示底部加载动画
            if (item_data.size() >0) {
                ((FootViewHolder) holder).foot_lin.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return item_data.size()+1+1;
    }


    //告诉创建什么类型的viewHolder
    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEAD;
        }else if(position+1 == getItemCount()){
            return TYPE_FOOT;
        }
        else{
            return  TYPE_NORMAL;
        }
    }

    //正常的item的布局管理
    class ItemViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView simpleView;
        TextView textVeiw;

        public ItemViewHolder(View itemView) {
            super(itemView);

            simpleView = (SimpleDraweeView) itemView.findViewById(R.id.simpleView);
            textVeiw = (TextView) itemView.findViewById(R.id.news_text);
        }
    }

    //首位的banner
    class BannerViewHolder extends  RecyclerView.ViewHolder{

        Banner banner;
        public BannerViewHolder(View itemView) {
            super(itemView);

            banner = (Banner) itemView.findViewById(R.id.banner);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder{
        LinearLayout foot_lin;
        public FootViewHolder(View itemView) {
            super(itemView);
            foot_lin = (LinearLayout) itemView.findViewById(R.id.foot_lin);
        }
    }

    private OnMyItemClickListener listener;

    //单击事件
    public interface  OnMyItemClickListener{
        void onClick(int position);
    }

    public void setMyItemClickListener(OnMyItemClickListener listener){
        this.listener = listener;
    }
}
