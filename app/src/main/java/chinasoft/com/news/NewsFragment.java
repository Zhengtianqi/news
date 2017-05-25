package chinasoft.com.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lisa on 2016/12/2.
 * Email: 457420045@qq.com
 */

public class NewsFragment extends BaseFragment {
    private NewsAdapter newsAdapter;
    private String type = null;
    /*
     * new instance 方法 获取JokeFragment
     */
    public static NewsFragment newInstance(String type) {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        args.putString("TYPE", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args!= null) {
            type = args.getString("TYPE");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        newsAdapter = new NewsAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsAdapter);
        return view;
    }

    @Override
    public void pullData() {
        super.pullData();
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
    }
}
