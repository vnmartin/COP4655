package com.example.vianelis.project4;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.vianelis.project4.Fragments.Admin;
import com.example.vianelis.project4.Fragments.Gameboard;
import com.example.vianelis.project4.Fragments.Player;
import com.example.vianelis.project4.Fragments.Scoreboard;


public class MainActivity extends AppCompatActivity {

    private String firstPlayer;
    private String secondPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.firstPlayer = "none";
        this.secondPlayer = "none";
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }
    
    public void setPlayer(int p, String n) {
        if (p == 1) {
            this.firstPlayer = n;
        } else {
            this.secondPlayer = n;
        }
    }
    
    public String getPlayer(int p) {
        if (p == 1) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }
    
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Player();
                case 1:
                    return new Gameboard();
                case 2:
                    return new Scoreboard();
                case 3:
                    return new Admin();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Game Emulator";
                case 1:
                    return "Score Board";
                case 2:
                    return "Player Select";
                case 3:
                    return "Admin";
                default:
                    return null;
            }
        }
    }
}