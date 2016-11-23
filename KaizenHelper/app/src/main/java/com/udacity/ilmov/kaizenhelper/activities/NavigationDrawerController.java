package com.udacity.ilmov.kaizenhelper.activities;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.udacity.ilmov.kaizenhelper.R;
import com.udacity.ilmov.kaizenhelper.fragments.AboutKaizenFragment;
import com.udacity.ilmov.kaizenhelper.fragments.SelectProcessFragment;

public class NavigationDrawerController{

    private SelectProcessFragment selectProcessFragment;
    private AboutKaizenFragment aboutKaizenFragment;


    private Context mContext;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;

    public NavigationDrawerController(final DrawerLayout mDrawerLayout, NavigationView navigationView,
                                      Toolbar mToolBar, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;

        this.selectProcessFragment = new SelectProcessFragment();
        this.aboutKaizenFragment = new AboutKaizenFragment();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.process:
                        showFragment(selectProcessFragment);
                        return true;
                    case R.id.about:
                        showFragment(aboutKaizenFragment);
                        return true;
                }

                return false;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle((MainActivity)mContext,
                mDrawerLayout,
                mToolBar,
                R.string.drawer_open,
                R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        //select first item in drawer list by default
        showFragment(selectProcessFragment);

    }

    /**
     *
     * Insert the fragment by replacing any existing fragment
     *
     * @param fragment will replace existing fragment
     */
    private void showFragment(Fragment fragment){
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
