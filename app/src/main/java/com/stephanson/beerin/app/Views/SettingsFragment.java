package com.stephanson.beerin.app.Views;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.stephanson.beerin.app.R;
import roboguice.activity.RoboActionBarActivity;

import static android.support.v4.app.ActivityCompat.invalidateOptionsMenu;
import static android.widget.FrameLayout.LayoutParams.*;

/**
 * Created by Stephan on 17.04.2016.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    private  LayoutParams layoutParams;
    private int toolbarHeight;
    public static SettingsFragment newInstance()
    {
        SettingsFragment fragment = new SettingsFragment();

        fragment.setHasOptionsMenu(true);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setupContainer(container);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setupContainer(ViewGroup container) {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_actionbar);
        toolbarHeight = toolbar.getHeight();
        layoutParams = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(0,0,0,0);
        container.setLayoutParams(layoutParams);
        ((RoboActionBarActivity) getActivity()).setSupportActionBar(toolbar);
        ((RoboActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        invalidateOptionsMenu(getActivity());
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
      //  menu.findItem(R.id.action_settings).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.settings_fragment);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutParams.setMargins(0,toolbarHeight,0,0);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        layoutParams.setMargins(0,0,0,0);
    }

}
