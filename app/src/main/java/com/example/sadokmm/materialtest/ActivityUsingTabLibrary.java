package com.example.sadokmm.materialtest;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class ActivityUsingTabLibrary extends AppCompatActivity implements MaterialTabListener {

    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_tab_library);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapater adapter =new ViewPagerAdapater(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i=0 ; i<adapter.getCount() ; i++){
            tabHost.addTab(
                    tabHost.newTab()
                    //setText(adapter.getPageTitle(i))
                            .setIcon(adapter.getIcon(i))
                    .setTabListener(this)
            );
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if ( id == R.id.main ){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab tab) {

        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }


    // Pager Adapter Class for View pager


    public class ViewPagerAdapater extends FragmentPagerAdapter {


        String[] tabs = getResources().getStringArray(R.array.tabs);
        int[] icons = {R.drawable.ic_photo_white, R.drawable.ic_video_white, R.drawable.ic_location_white};


        public ViewPagerAdapater(FragmentManager fm) {
            super(fm);
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {




            return getResources().getStringArray(R.array.tabs)[position];
        }

        @Override
        public Fragment getItem(int i) {

            return MyFragment.getInstance(i);
        }

        @Override
        public int getCount() {
            return 3;
        }

        private Drawable getIcon(int position){
            return getResources().getDrawable(icons[position]);
        }
    }





}
