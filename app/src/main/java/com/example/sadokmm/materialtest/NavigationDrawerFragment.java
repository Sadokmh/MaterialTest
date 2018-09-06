package com.example.sadokmm.materialtest;


import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

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

    private VivzAdapter adapter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        //get Data for Recycler View list
        recyclerView = (RecyclerView)layout.findViewById(R.id.drawerList);
        adapter=new VivzAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
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

    public void settUp(int fragmentId, DrawerLayout drawerLayout , android.support.v7.widget.Toolbar toolbar) {

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
