package com.example.sadokmm.materialtest;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

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

        return super.onOptionsItemSelected(item);
    }
}
