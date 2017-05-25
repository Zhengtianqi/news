package chinasoft.com.news;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

/**
 * 自定义的弹窗样式
 */
public class CommonProgressDialog extends Dialog {

    public CommonProgressDialog(Context context) {
        super(context,R.style.commonProgressDialog);

        setContentView(R.layout.commonprogressdialog);

        //显示在屏幕中间
        getWindow().getAttributes().gravity = Gravity.CENTER;

    }

    /**
     * 设置加载消息的方法
     * @param s
     */
    public void setMessage(String s){
        TextView textView = (TextView) this.findViewById(R.id.id_tv_loading);
        textView.setText(s);
    }
}