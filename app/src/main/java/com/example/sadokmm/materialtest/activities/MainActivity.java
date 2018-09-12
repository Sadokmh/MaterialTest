package com.example.sadokmm.materialtest.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.sadokmm.materialtest.fragments.FragmentSearch;
import com.example.sadokmm.materialtest.fragments.FragmentUpcoming;
import com.example.sadokmm.materialtest.fragments.MyFragment;
import com.example.sadokmm.materialtest.fragments.NavigationDrawerFragment;
import com.example.sadokmm.materialtest.R;

import tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

           toolbar=(Toolbar)findViewById(R.id.app_bar);
           setSupportActionBar(toolbar);
           getSupportActionBar().setDisplayShowHomeEnabled(true);

           NavigationDrawerFragment drawerFragment=(NavigationDrawerFragment)
                   getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
           drawerFragment.settUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawerLayout),toolbar);


           //Pager for tabs
            mPager=(ViewPager)findViewById(R.id.pager);

            mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
            mTabs=(SlidingTabLayout)findViewById(R.id.tabs);

        //Make all fragment and icons in 1 page
        mTabs.setDistributeEvenly(true);

            //Put the icons title
            mTabs.setCustomTabView(R.layout.custom_tab_view,R.id.tabText);

            //Change bottom bar color (under icons)
           /* mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                @Override
                public int getIndicatorColor(int position) {
                    return getResources().getColor(R.color.backColor);
                }
            });*/





           mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
               @Override
               public int getIndicatorColor(int position) {
                   return getResources().getColor(R.color.colorAccent);
               }
           });


            mTabs.setViewPager(mPager);



    }


    //works with the addTouchListener of a recyclerView in NavigationDrawerFragment
    public void OnDrawerItemClicked(int index){

        mPager.setCurrentItem(index);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if (id == R.id.action_settings){

        }

        if ( id == R.id.navigate){
            startActivity(new Intent(this,subActivity.class));
        }

        if (id == R.id.tab ){
            startActivity(new Intent(this,ActivityUsingTabLibrary.class));
        }

        return super.onOptionsItemSelected(item);
    }





    // Pager Adapter Class for View pager


    public class MyPagerAdapter extends FragmentPagerAdapter {


        String[] tabs=getResources().getStringArray(R.array.tabs);
        int[] icons= {R.drawable.ic_photo_white , R.drawable.ic_video_white , R.drawable.ic_location_white};


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            //GET ICONS tabs
            Drawable drawable=getResources().getDrawable(icons[position]);
            drawable.setBounds(0,0,36,36);
            ImageSpan imageSpan=new ImageSpan(drawable);
            SpannableString spannableString=new SpannableString(" ");
            spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            return spannableString;
        }

        @Override
        public Fragment getItem(int i) {
            MyFragment myFragment=MyFragment.getInstance(i);

            return myFragment;


        }

        @Override
        public int getCount() {
            return 3;
        }
    }




    // Fragment class




}
