package chinasoft.com.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    /*声明变量*/
    // 布局管理器
    private FragmentManager fManager;

    private FragmentHome fragment_home;
    private FragmentPerson fragment_me;
    private FragmentNews fragment_news;
    private FragmentPhoto fragment_photo;
    // 首页
    private ImageView iv_menu_home;
    private TextView tv_menu_home;

    // 新闻
    private ImageView iv_menu_news;
    private TextView tv_menu_news;

    // 视频
    private ImageView iv_menu_photo;
    private TextView tv_menu_photo;

    // 我的
    private ImageView iv_menu_me;
    private TextView tv_menu_me;
    //头像
    private ImageView userPhoto;
    private FragmentHome mfragmentHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        // 初始化组件
        initViews();
        // 默认先点中第一个“首页”
        clickMenu(findViewById(R.id.ll_menu_home));
        //初始化Fresco
        Fresco.initialize(this);

    }
    /*---------------------贱贱的分割线---------------------*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override /*左侧Navigation*/
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_one) {
            // Handle the action
            Intent intent=new Intent(MainActivity.this,DialogActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_two) {

        } else if (id == R.id.nav_three) {

        } else if (id == R.id.nav_forth) {

        } else if (id == R.id.nav_five) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initViews() {
        // 布局管理器
        fManager = getSupportFragmentManager();

        iv_menu_home = (ImageView)findViewById(R.id.iv_menu_home);
        tv_menu_home = (TextView)findViewById(R.id.tv_menu_home);

        iv_menu_news = (ImageView)findViewById(R.id.iv_menu_news);
        tv_menu_news = (TextView)findViewById(R.id.tv_menu_news);

        iv_menu_photo = (ImageView)findViewById(R.id.iv_menu_photo);
        tv_menu_photo = (TextView)findViewById(R.id.tv_menu_photo);

        iv_menu_me = (ImageView)findViewById(R.id.iv_menu_me);
        tv_menu_me = (TextView)findViewById(R.id.tv_menu_me);
    }
    /**
     * 点击底部菜单事件
     * @param v
     */
    public void clickMenu(View v){
        FragmentTransaction trans = fManager.beginTransaction();
        int vID = v.getId();
        // 设置menu样式
        setMenuStyle(vID);
        // 隐藏所有的fragment
        hideFrament(trans);
        // 设置Fragment
        setFragment(vID,trans);
        trans.commit();
    }
    /**
     * 隐藏所有的fragment(编程初始化状态)
     * @param trans
     */
    private void hideFrament(FragmentTransaction trans) {
        if(fragment_home!=null){
            trans.hide(fragment_home);
        }
        if(fragment_news!=null){
            trans.hide(fragment_news);
        }
        if(fragment_photo!=null){
            trans.hide(fragment_photo);
        }
        if(fragment_me!=null){
            trans.hide(fragment_me);
        }
    }
    /**
     * 设置menu样式
     * @param vID
     * @param trans
     */
    private void setMenuStyle(int vID) {
        // 首页
        if(vID==R.id.ll_menu_home){
            iv_menu_home.setImageDrawable(getResources().getDrawable(R.mipmap.ic_home_red));
            tv_menu_home.setTextColor(getResources().getColor(R.color.red));
        }else {
            iv_menu_home.setImageDrawable(getResources().getDrawable(R.mipmap.ic_home_white));
            tv_menu_home.setTextColor(getResources().getColor(R.color.gray));
        }
       // 新闻
        if(vID==R.id.ll_menu_news){
            iv_menu_news.setImageDrawable(getResources().getDrawable(R.mipmap.ic_news_red));
            tv_menu_news.setTextColor(getResources().getColor(R.color.red));
        }else {
            iv_menu_news.setImageDrawable(getResources().getDrawable(R.mipmap.ic_news_white));
            tv_menu_news.setTextColor(getResources().getColor(R.color.gray));
        }
        // 视频
        if(vID==R.id.ll_menu_photo){
            iv_menu_photo.setImageDrawable(getResources().getDrawable(R.mipmap.ic_photo_red));
            tv_menu_photo.setTextColor(getResources().getColor(R.color.red));
        }else {
            iv_menu_photo.setImageDrawable(getResources().getDrawable(R.mipmap.ic_photo_white));
            tv_menu_photo.setTextColor(getResources().getColor(R.color.grey));
        }
        // 我的
        if(vID==R.id.ll_menu_me){
            iv_menu_me.setImageDrawable(getResources().getDrawable(R.mipmap.ic_person_red));
            tv_menu_me.setTextColor(getResources().getColor(R.color.red));
        }else {
            iv_menu_me.setImageDrawable(getResources().getDrawable(R.mipmap.ic_person_white));
            tv_menu_me.setTextColor(getResources().getColor(R.color.gray));
        }
    }

    /**
     * 设置Fragment
     * @param vID
     * @param trans
     */
    private void setFragment(int vID,FragmentTransaction trans) {
        switch (vID) {
            //四个Fragment
            case R.id.ll_menu_home:
                if(fragment_home==null){
                    fragment_home = new FragmentHome();
                    trans.add(R.id.content, fragment_home);
                }else{
                    trans.show(fragment_home);
                }
                break;
            case R.id.ll_menu_photo:
                if(fragment_photo==null){
                    fragment_photo = new FragmentPhoto();
                    trans.add(R.id.content, fragment_photo);
                }else{
                    trans.show(fragment_photo);
                }
                break;
            case R.id.ll_menu_news:
                if(fragment_news==null){
                    fragment_news = new FragmentNews();
                    trans.add(R.id.content, fragment_news);
                }else{
                    trans.show(fragment_news);
                }
                break;
            case R.id.ll_menu_me:
                if(fragment_me==null){
                    fragment_me = new FragmentPerson();
                    trans.add(R.id.content, fragment_me);
                }else{
                    trans.show(fragment_me);
                }
                break;
            default:
                break;
        }
    }
}

