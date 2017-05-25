package chinasoft.com.news;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import com.astuetz.PagerSlidingTabStrip;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.lang.reflect.Field;


public class FragmentNews extends Fragment {
        /**
         * PagerSlidingTabStrip的实例
         */
        private PagerSlidingTabStrip tabs;

        /**
         * 获取当前屏幕的密度
         */
        private DisplayMetrics dm;
    private NewsFragment mNewsFragment;
	/*
	 *
	 */

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_news, null);
            setOverflowShowingAlways();
            dm = getResources().getDisplayMetrics();
            ViewPager pager = (ViewPager)view. findViewById(R.id.pager);
            pager.setOffscreenPageLimit(0);//设置ViewPager的缓存界面数,默认缓存为2
            tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
            pager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
            tabs.setViewPager(pager);
            setTabsValue();
            //初始化Fresco
            Fresco.initialize(getContext());
            return view;
        }

        /**
         * 对PagerSlidingTabStrip的各项属性进行赋值。
         */
        private void setTabsValue() {
            // 设置Tab是自动填充满屏幕的
            tabs.setShouldExpand(false);
            // 设置Tab的分割线是透明的
            tabs.setDividerColor(Color.TRANSPARENT);
            // 设置Tab底部线的高度
            tabs.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm));
            // 设置Tab Indicator的高度
            tabs.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, dm));
            // 设置Tab标题文字的大小
            tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, dm));
            // 设置Tab Indicator的颜色
            tabs.setIndicatorColor(Color.parseColor("#d83737"));//#d83737   #d83737(绿)
            // 取消点击Tab时的背景色
            tabs.setTabBackground(0);
        }

        public class MyPagerAdapter extends FragmentPagerAdapter {

            public MyPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            public  final String[] titles = {"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return NewsFragment.newInstance("top");
                    case 1:
                        return NewsFragment.newInstance("shehui");
                    case 2:
                        return NewsFragment.newInstance("guonei");
                    case 3:
                        return NewsFragment.newInstance("guoji");
                    case 4:
                        return NewsFragment.newInstance("yule");
                    case 5:
                        return NewsFragment.newInstance("tiyu");
                    case 6:
                        return NewsFragment.newInstance("junshi");
                    case 7:
                        return NewsFragment.newInstance("keji");
                    case 8:
                        return NewsFragment.newInstance("caijing");
                    case 9:
                        return NewsFragment.newInstance("shishang");
                    default:
                        break;
                }
                return null;
            }

        }

        private void setOverflowShowingAlways() {
            try {
                ViewConfiguration config = ViewConfiguration.get(getParentFragment().getActivity());
                Field menuKeyField = ViewConfiguration.class
                        .getDeclaredField("sHasPermanentMenuKey");
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
