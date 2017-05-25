package chinasoft.com.news;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class FragmentPerson extends Fragment{

    private View view;// 需要返回的布局
    private ImageView imageView;
    private TextView userName;
    private HintPopupWindow hintPopupWindow;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.person_fragment,container,false);
        userName= (TextView) getActivity().findViewById(R.id.userName1);
        Intent intent=getActivity().getIntent();
        userName.setText(intent.getStringExtra("etName"));
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //侧面
        ImageView button = (ImageView) getActivity().findViewById(R.id.userPhoto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        //person
        ImageView button2 = (ImageView) getActivity().findViewById(R.id.userPhoto1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出选项弹窗
                hintPopupWindow.showPopupWindow(v);
            }
        });
        //下面的操作是初始化弹出数据
        ArrayList<String> strList = new ArrayList<>();
        strList.add("登录");
        strList.add("注册");

        ArrayList<View.OnClickListener> clickList = new ArrayList<>();
        View.OnClickListener login = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
            }
        };
        clickList.add(login);
        View.OnClickListener register = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),RegisterActivity.class);
                startActivity(i);
            }
        };
        clickList.add(register);
        //具体初始化逻辑看下面的图
        hintPopupWindow = new HintPopupWindow(getActivity(), strList, clickList);
    }
}
