package chinasoft.com.news;


import android.os.Handler;
import android.os.Message;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonUtils {


    private CallBackListener listener;

    public JsonUtils(){}

    //将被观察者和观察者产生关系
    public JsonUtils(CallBackListener listener){
        this.listener = listener;
    }


    //建立网络框架，获取网络数据
    public void getResult(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建一个OKHttpClient对象，一个app里最好实例化一个此对象
                OkHttpClient client = new OkHttpClient();

                //新建一个请求
                Request request = new Request.Builder().url("http://news.ifeng.com/").build();

                //执行请求获得响应结果
                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            //执行请求成功的操作
                            String str = response.body().string();

//                            Log.i("", "onResponse: "+str);
                            Message msg = new Message();
                            msg.obj = str;
                            msg.what = 0x001;
                            mHandler.sendMessage(msg);

                        }else{
                            throw new IOException(""+response);

                        }
                    }
                });

            }
        }).start();
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0x001){
                String json = msg.obj.toString();
                getJson(json);
            }
        }
    };

    //获取返回字符串中的json数据
    private void getJson(String json) {
        String result = null;
        if(json!=null){
            //截取到json数据
            result = json.substring(json.indexOf("[[{"),json.indexOf("}]]")+3);
        }

        //截取后进行解析
        initMessageList(result);
    }


    private List<Data>[] mDataList;
    private void initMessageList(String result) {

        //[[{},{}],[{},{}],[{},{}],[{},{}]]
        JSONArray arr,array = null;
        JSONObject obj = null;

        try {
            arr = new JSONArray(result);
            mDataList = new ArrayList[arr.length()];

            for(int i = 0;i<arr.length();i++){
                //获取到每个位置对应的数组
                array = arr.getJSONArray(i);
                mDataList[i] = new ArrayList<Data>();
                for (int j = 0; j < array.length(); j++) {
                    obj = array.getJSONObject(j);
                    Data data = new Data();
                    data.setThumbnail(obj.getString("thumbnail"));
                    data.setTitle(obj.getString("title"));
                    data.setUrl(obj.getString("url"));
                    mDataList[i].add(data);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //通知主界面更新UI
        listener.updateUI(mDataList);
    }

    //监听加载数据完成后调用的接口
    public interface CallBackListener{
        void updateUI(List<Data>[] mDataList);
    }
}
