package chinasoft.com.news;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class OkhttpUtils {


    private static final OkHttpClient okHttpClient = new OkHttpClient();
        /*获取新闻头条信息*/
    public static String getNews(String type) {
        String URL = "http://v.juhe.cn/toutiao/index?type=" + type + "&key=74053a5ea6190a0272de39c5562921fe";
        return getContentByURL(URL);
    }

    /*通过URL获取对应的内容信息*/
    private static String getContentByURL(String url) {
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();//只能调用一次
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ( (ni != null && ni.isConnected()));
    }

}
