package com.edu.bjfu.cs2015.ibasketball;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class MainActivity extends AppCompatActivity {
    public static final int FRAGMENT_GAMES = 0;
    public static final int FRAGMENT_NEWS = 1;
    public static final int FRAGMENT_MYSELF = 2;
    private Fragment games;
    private Fragment news;
    private Fragment myself;
    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBottomNavigationUI();

        // 默认Fragment
        fragmentManager = getSupportFragmentManager();
        showFragment(FRAGMENT_NEWS);

    }

    public void initBottomNavigationUI() {
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_maps_tab1, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_maps_tab2, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_maps_tab3, R.color.color_tab_3);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(true);

        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        // Display color under navigation bar (API 21+)
        // Don't forget these lines in your style-v21
        // <item name="android:windowTranslucentNavigation">true</item>
        // <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(true);

        // Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(1);

        // Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (position == 0) {
                    showFragment(FRAGMENT_GAMES);
                }
                if (position == 1) {
                    showFragment(FRAGMENT_NEWS);
                }
                if (position == 2) {
                    showFragment(FRAGMENT_MYSELF);
                }
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
    }

    public void showFragment(int index) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideFragment(ft);
        /**
         * 如果Fragment为空，就新建一个实例
         * 如果不为空，就将它从栈中显示出来
         */
        switch (index) {
            case FRAGMENT_GAMES:
                if (games == null) {
                    games = new FragmentGames();
                    ft.add(R.id.fragment_layout_main, games);
                } else {
                    ft.show(games);
                }
                break;
            case FRAGMENT_NEWS:
                if (news == null) {
                    news = new FragmentNews();
                    ft.add(R.id.fragment_layout_main, news);
                } else {
                    // 刷新newsFragment
//                    ft.detach(news);
//                    ft.attach(news);
//                    ft.commit();
                    ft.show(news);
                }
                break;

            case FRAGMENT_MYSELF:
                if (myself == null) {
                    myself = new FragmentMyself();
                    ft.add(R.id.fragment_layout_main, myself);
                } else {
                    ft.show(myself);
                }
                break;
        }
        ft.commit();
    }

    public void hideFragment(FragmentTransaction ft) {
        //如果不为空，就先隐藏起来
        if (games != null) {
            ft.hide(games);
        }
        if (news != null) {
            ft.hide(news);
        }
        if (myself != null) {
            ft.hide(myself);
        }

    }
}