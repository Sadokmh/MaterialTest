package com.example.sadokmm.materialtest.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sadokmm.materialtest.RecyclerTouchListener;
import com.example.sadokmm.materialtest.activities.ActivityUsingTabLibrary;
import com.example.sadokmm.materialtest.activities.MainActivity;
import com.example.sadokmm.materialtest.adapters.AdapterDrawer;
import com.example.sadokmm.materialtest.objects.Information;
import com.example.sadokmm.materialtest.R;
import com.example.sadokmm.materialtest.adapters.VivzAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String PREF_FILE_Name="testpref";
    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";
    private View containerView;

    private RecyclerView recyclerView;

    private RecyclerView mRecyclerDrawer;

    private VivzAdapter adapter;
    private AdapterDrawer mAdapter;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    //for setting the DL for the first run of app
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer=Boolean.valueOf(readFromPreferences(getContext(),KEY_USER_LEARNED_DRAWER,"false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState=true;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        //get Data for Recycler View list




        return layout;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        recyclerView = (RecyclerView)view.findViewById(R.id.drawerList);
        adapter=new VivzAdapter(getActivity(),getData());

        mAdapter = new AdapterDrawer(getActivity(),getData());

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                ((MainActivity) getActivity()).OnDrawerItemClicked(position-1);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/


    }

    public static List<Information> getData(){
        List<Information> data=new ArrayList<>();
        int[] icons = {R.drawable.ic_home , R.drawable.ic_profile , R.drawable.ic_about , R.drawable.ic_logout };
        String[] titles= {"Home" , "Profile" , "About" , "Logout" };

        for (int i=0 ; i<icons.length && i<titles.length ; i++)
        {
            Information current = new Information();
            current.iconId=icons[i];
            current.title=titles[i];
            data.add(current);
        }
        return data;
    }

    public void settUp(int fragmentId, DrawerLayout drawerLayout , final android.support.v7.widget.Toolbar toolbar) {

        containerView=getActivity().findViewById(fragmentId);
        mDrawerLayout=drawerLayout;
        mDrawerToggle= new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose){

            @Override
            public void onDrawerOpened(View drawerView) {

                if (!mUserLearnedDrawer){
                    mUserLearnedDrawer=true;
                    saveToPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,mUserLearnedDrawer+""); // we add "" because mUserLearnedDrawer is Boolean and the param needs String

                }
                getActivity().invalidateOptionsMenu();

            }


            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (getActivity() instanceof ActivityUsingTabLibrary){
                ((ActivityUsingTabLibrary) getActivity()).onDrawerSlide(slideOffset);
                toolbar.setAlpha(1 - slideOffset/2);}
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState){

            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }


    //for setting the DL for the first run of app

    public static void saveToPreferences(Context context, String preferenceName , String preferenceValue){

        SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_FILE_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.commit();
    }

    public static String readFromPreferences(Context context, String preferenceName , String defaultValue){

        SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_FILE_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);

    }
}
