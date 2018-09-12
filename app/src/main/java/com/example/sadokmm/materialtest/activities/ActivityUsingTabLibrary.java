package com.example.sadokmm.materialtest.activities;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sadokmm.materialtest.fragments.FragmentSearch;
import com.example.sadokmm.materialtest.fragments.FragmentUpcoming;
import com.example.sadokmm.materialtest.fragments.FragmentView;
import com.example.sadokmm.materialtest.fragments.MyFragment;
import com.example.sadokmm.materialtest.R;
import com.example.sadokmm.materialtest.fragments.NavigationDrawerFragment;
import com.example.sadokmm.materialtest.fragments.myhomeFrag;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class ActivityUsingTabLibrary extends AppCompatActivity implements View.OnClickListener,MaterialTabListener , FragmentSearch.OnFragmentInteractionListener , FragmentUpcoming.OnFragmentInteractionListener ,FragmentView.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;

    //Tags for FAB menu Click Listenner
    private static final String TAG_IMAGE="imageClick";
    private static final String TAG_Video="videoClick";

    FloatingActionButton actionButton;
    FloatingActionMenu actionMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_tab_library);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationDrawerFragment drawerFragment=(NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.settUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawerLayout2),toolbar);

        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapater adapter = new ViewPagerAdapater(getSupportFragmentManager());


        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);

                //add later
            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            //setText(adapter.getPageTitle(i))
                            .setIcon(adapter.getIcon(i))
                            .setTabListener(this)
            );
        }
        viewPager.setAdapter(adapter);


        //SETUP the Foalting Action Button
        setUpFab();


    }


    //Two methods for hide the FAB when the DrawerMenu is open

    private void toggleTranslateFAB(float slideOffset){

        if (actionMenu != null) {
            if (actionMenu.isOpen()){
                actionMenu.close(true);
            }
            actionButton.setTranslationX(slideOffset * 200);
        }

    }


    public void onDrawerSlide(float slideOffset){

        toggleTranslateFAB(slideOffset);

    }



    //SETUP the FAB
    private void setUpFab(){


        //SET UP the Floating Action Button
        // in Activity Context
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.ic_add_white);
        actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setBackgroundDrawable(R.drawable.selector_button_pressed)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        //menu items
        ImageView itemImage = new ImageView(this);
        itemImage.setImageResource(R.drawable.ic_photo);
        SubActionButton button1 = itemBuilder.setContentView(itemImage).build();
        //item 2
        final ImageView itemVideo = new ImageView(this);
        itemVideo.setImageResource(R.drawable.ic_video);
        SubActionButton button2 = itemBuilder.setContentView(itemVideo).build();



        //Create the menu with the items:
         actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                // ...
                .attachTo(actionButton)
                .build();

        //set the TAGs
        button1.setTag(TAG_IMAGE);
        button2.setTag(TAG_Video);

        //Create Click listenners
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View view) {

        if (view.getTag().equals(TAG_IMAGE)) {
            Toast.makeText(getApplicationContext(),"Hello bb Images",Toast.LENGTH_SHORT).show();
        }

         if (view.getTag().equals(TAG_Video)) {
             Toast.makeText(getApplicationContext(),"Hello bb Videos",Toast.LENGTH_SHORT).show();
         }
    }


    // Pager Adapter Class for View pager


    public class ViewPagerAdapater extends FragmentStatePagerAdapter {


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

            //return MyFragment.getInstance(i);
            Fragment fragment=null;

            switch (i){

                case 0: {

                    fragment= myhomeFrag.newInstance();
                    break;
                }
                case 1: {
                    fragment= FragmentUpcoming.newInstance("","");
                    break;
                }
                case 2: {
                    fragment= FragmentView.newInstance("","");

                    break;
                }

            }

            return fragment;
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
