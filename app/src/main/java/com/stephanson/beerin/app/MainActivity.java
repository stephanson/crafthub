package com.stephanson.beerin.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.stephanson.beerin.app.Views.MainWindowMaterialViewPagerFragment;
import com.stephanson.beerin.app.Views.SettingsFragment;
import roboguice.activity.RoboActionBarActivity;

public class MainActivity extends RoboActionBarActivity {

    private Fragment _activityContent;
    private String TAG = "ACTIVITY_CONTENT";

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if(fragment == null)
        {
            _activityContent = MainWindowMaterialViewPagerFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentHolder, _activityContent, TAG)
                    .commit();
        }
        else
        {
            _activityContent = fragment;
        }
    }

    public void changeFragment(Fragment frg, boolean anim, boolean addToBS)
    {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, frg)
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
//            case R.id.action_settings:
//                changeFragment(SettingsFragment.newInstance(), true, true);
//                return true;
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent)
    {

        super.onNewIntent(intent);
    }

}
