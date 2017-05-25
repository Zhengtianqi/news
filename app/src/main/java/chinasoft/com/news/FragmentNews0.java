package chinasoft.com.news;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class FragmentNews0 extends Fragment {
    private int text;
    private ListView listView;
    private String type = null;
    //导入了一个神器的包，butterknife.BindView
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_content)
    RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    Toast toast ;
    public FragmentNews0(int text) {  //用于初始化的时候传递一个文字显示在textview中便于知道是第几页
        this.text = text;
        Bundle args = new Bundle();
        args.putString("TYPE", type);
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args!= null) {
            type = args.getString("TYPE");//获取当前页面的类型，动态显示
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
    /*    super.onCreateView(inflater, container, savedInstanceState)*/;

        //      create main panel for fragment
        View view = inflater.inflate(R.layout.base_fragment, container, false);
        ButterKnife.bind(this, view);

        toast = Toast.makeText(getContext(), "网络连接错误，请检查网络状态", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);

        pullData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                pullData();
            }
        });
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        newsAdapter = new NewsAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsAdapter);
        return view;
    }

    public void pullData() {//放入数据并显示
        if(!OkhttpUtils.isNetworkAvailable(getContext())) {
            if(swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
            toast.show();
            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    String response = OkhttpUtils.getNews(type);
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
                @Override
                public void onNext(String response) {
                    // swipeRefreshLayout.setRefreshing(true);
                    Gson gson = new Gson();
                    NewsBean newsBean = gson.fromJson(response, NewsBean.class);
                    newsAdapter.setNews(newsBean);
                }

                @Override
                public void onCompleted() {
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onError(Throwable e) {
                    //handle exception
                    e.printStackTrace();
                }
            });
            return;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(toast!= null) {
            toast.cancel();
        }
    }

}
