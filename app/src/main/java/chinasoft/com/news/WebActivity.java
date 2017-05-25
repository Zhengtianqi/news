package chinasoft.com.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class WebActivity extends AppCompatActivity {

    private String URL = null;
    private CommonProgressDialog loadingDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detail);

        loadingDialog = new CommonProgressDialog(this);
        /*loadingDialog.setIndeterminate(true);*/
        loadingDialog.setTitle("提示");
        loadingDialog.setMessage("正在加载...");
        loadingDialog.setCancelable(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Bundle temp = bundle.getBundle("extra");
            if (temp != null) {
                URL = temp.getString("URL");
            }
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
            toolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        WebView webView = (WebView) findViewById(R.id.webView_msgDetail);
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setJavaScriptEnabled(true);//启用js
        webSettings.setBlockNetworkImage(false);//解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.loadUrl(URL);//load Url

        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                if(!loadingDialog.isShowing()) {
                    loadingDialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                if (null != loadingDialog)
                {
                    //加载完成,dialog销毁
                    loadingDialog.cancel();
                }
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}


