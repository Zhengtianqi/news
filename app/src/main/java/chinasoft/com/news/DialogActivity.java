package chinasoft.com.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by 27049 on 2016/12/21.
 */

public class DialogActivity extends Activity {
    //splash的声明周期
    private final int SPLASH_DISPLAY_LENGHT = 1000;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(DialogActivity.this, MainActivity.class);
                startActivity(intent); } }, SPLASH_DISPLAY_LENGHT);
    }
}
