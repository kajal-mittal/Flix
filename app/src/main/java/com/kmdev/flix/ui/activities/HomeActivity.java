package com.kmdev.flix.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.brsoftech.core_utils.base.BaseAppCompatActivity;
import com.kmdev.flix.R;
import com.kmdev.flix.RestClient.ApiHitListener;
import com.kmdev.flix.ui.fragments.FavouriteMainFragment;
import com.kmdev.flix.ui.fragments.HomeFragment;
import com.kmdev.flix.ui.fragments.PeopleFragment;
import com.kmdev.flix.utils.Utils;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends BaseAppCompatActivity implements ApiHitListener,
        NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setNavigationDrawer();
        setTitle();
        setFragmentWithoutBackStack(R.id.home_fragment, HomeFragment.newInstance(), false);
    }


    private void setTitle() {
        Utils.applyFontForToolbarTitle(HomeActivity.this);
        mToolBar.setTitle(R.string.app_name);

    }

    private void init() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);

        }


    }

    private void setNavigationDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onSuccessResponse(int apiId, Object response) {

    }

    @Override
    public void onFailResponse(int apiId, String error) {
        displayErrorDialog(getResources().getString(R.string.error), error);


    }

    @Override
    public void networkNotAvailable() {

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            setFragmentWithoutBackStack(R.id.home_fragment, HomeFragment.newInstance(), false);
        } else if (id == R.id.nav_favourite) {
            setFragmentWithoutBackStack(R.id.home_fragment, FavouriteMainFragment.newInstance(), false);
        } else if (id == R.id.nav_settings) {
            startActivity(SettingsActivity.class);

        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Take a look at \"Flix\"\nhttps://play.google.com/store/apps/details?id=com.kajalmittal.flix");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);


        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_donate) {

            ArrayList<String> skuList = new ArrayList<String>();
            skuList.add("premiumUpgrade");
            skuList.add("gas");
            Bundle querySkus = new Bundle();
            querySkus.putStringArrayList("ITEM_ID_LIST", skuList);


        } else if (id == R.id.nav_people) {
            setFragmentWithoutBackStack(R.id.home_fragment, PeopleFragment.newInstance(), false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
