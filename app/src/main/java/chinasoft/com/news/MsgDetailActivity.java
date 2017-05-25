package chinasoft.com.news;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MsgDetailActivity extends AppCompatActivity {
    WebView webView;
    private HintPopupWindow hintPopupWindow;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detail);

        webView = (WebView) findViewById(R.id.webView_msgDetail);

        //打开页面时，自适应屏幕
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true); //可任意比例缩放

        String url = getUrlByPosition();

        webView.loadUrl(url);
    //浮动返回键
       final ImageView toolbar  = (ImageView) findViewById(R.id.toolbar_msg);
        final int screenWidth,screenHeight;
        final int[] lastX = new int[1];
        final int[] lastY = new int[1];
        Display dis=this.getWindowManager().getDefaultDisplay();
        screenWidth=dis.getWidth();
        screenHeight=dis.getHeight();
        toolbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        lastX[0] =(int)event.getRawX();
                        lastY[0] =(int)event.getRawY();

                        break;

                    case MotionEvent.ACTION_MOVE:
                        int dx=(int)event.getRawX()- lastX[0];
                        int dy=(int)event.getRawY()- lastY[0];

                        int top=v.getTop()+dy;

                        int left=v.getLeft()+dx;


                        if(top<=0)
                        {
                            top=0;
                        }
                        if(top>=screenHeight-toolbar.getHeight())
                        {
                            top=screenHeight-toolbar.getHeight();
                        }
                        if(left>=screenWidth-toolbar.getWidth())
                        {
                            left=screenWidth-toolbar.getWidth();
                        }

                        if(left<=0)
                        {
                            left=0;
                        }


                        v.layout(left, top, left+toolbar.getWidth(), top+toolbar.getHeight());
                        lastX[0] =(int)event.getRawX();
                        lastY[0] =(int)event.getRawY();

                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return false;
            }
        });
       //设置返回键可用
       /* getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        //设置回退按钮的事件
                //  longClick模仿3D效果
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* //弹出选项弹窗
                hintPopupWindow.showPopupWindow(view);*/
                finish();
            }
        });
/*        //下面的操作是初始化弹出数据
        ArrayList<String> strList = new ArrayList<>();
        strList.add("webview");

        ArrayList<View.OnClickListener> clickList = new ArrayList<>();
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MsgDetailActivity.this, "点击事件触发", Toast.LENGTH_SHORT).show();
            }
        };
        clickList.add(clickListener);
        clickList.add(clickListener);
        clickList.add(clickListener);
        clickList.add(clickListener);

        //具体初始化逻辑看下面的图
        hintPopupWindow = new HintPopupWindow(this, strList, clickList);*/
    }

    private String getUrlByPosition() {
        String oldUrl = getIntent().getStringExtra("url");
        StringBuilder url = new StringBuilder();

        //获取网页形式路径中的数字
        String num = getMainNum(oldUrl);
        url = url.append("http://inews.ifeng.com/").append(num).append("/news.shtml");
        return url.toString();
    }

    //截取中间数字的方法
    private String getMainNum(String oldUrl) {
        oldUrl = oldUrl.substring(oldUrl.lastIndexOf("/")+1,oldUrl.lastIndexOf("."));
        if (oldUrl.contains("_")) {
            oldUrl = oldUrl.substring(0,oldUrl.lastIndexOf("_"));
        }
        return oldUrl;
    }
}
