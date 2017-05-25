package chinasoft.com.news;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentPhoto extends Fragment{

    private ListView listView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //需要返回的View
        View rootView=inflater.inflate(R.layout.activity_photo,null);
        listView = (ListView) rootView.findViewById(R.id.listview_p);
        VideoListAdapter adapter=new VideoListAdapter(getContext());
        listView.setAdapter(adapter);
        return rootView;
    }
    // 方法区
    public void onResume(){
        super.onResume();
    }
//底部
}