package com.stephanson.beerin.app.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.stephanson.beerin.app.R;
import com.stephanson.beerin.app.Views.Interface.IMaterialView;

import org.apache.commons.lang.NotImplementedException;

import java.lang.reflect.Field;
import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

import static android.support.v4.app.ActivityCompat.invalidateOptionsMenu;

/**
 * Created by Electric Brains on 25.04.2016.
 */

public abstract class MaterialViewPagerBase extends RoboFragment implements IMaterialView {
    @InjectView(R.id.materialViewPager)
    protected MaterialViewPager _materialViewPager;
    @InjectView(R.id.logo_text)
    protected TextView _logoText;
    @InjectView(R.id.logo_image)
    protected ImageView _logoImage;


    private FragmentManager retainedChildFragmentManager;
    private Field mHostField;
    private Class fragmentImplClass;
    private FragmentHostCallback currentHost;
    {

        try {
            fragmentImplClass = Class.forName("android.support.v4.app.FragmentManagerImpl");
            mHostField = fragmentImplClass.getDeclaredField("mHost");
            mHostField.setAccessible(true);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("FragmentManagerImpl is renamed due to the " +
                    "change of Android SDK, this workaround doesn't work any more. " +
                    "See the issue at " +
                    "https://code.google.com/p/android/issues/detail?id=74222", e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("FragmentManagerImpl.mHost is found due to the " +
                    "change of Android SDK, this workaround doesn't work any more. " +
                    "See the issue at " +
                    "https://code.google.com/p/android/issues/detail?id=74222", e);
        }
    }

    protected FragmentManager childFragmentManager() {
        if (retainedChildFragmentManager == null) {
            retainedChildFragmentManager = getChildFragmentManager();
        }
        return retainedChildFragmentManager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (retainedChildFragmentManager != null) {
            //restore the last retained child fragment manager to the new
            //created fragment
            try {
                //Copy the mHost(Activity) to retainedChildFragmentManager
                currentHost = (FragmentHostCallback) mHostField.get(getFragmentManager());

                Field childFMField = Fragment.class.getDeclaredField("mChildFragmentManager");
                childFMField.setAccessible(true);
                childFMField.set(this, retainedChildFragmentManager);

                refreshHosts(getFragmentManager());
            } catch (Exception e) {

            }
            //Refresh children fragment's hosts
        } else {
            //If the child fragment manager has not been retained yet, let it hold the internal
            //child fragment manager as early as possible. This can prevent child fragment
            //manager from missing to be set and then retained, which could happen when
            //OS kills activity and restarts it. In this case, the delegate fragment restored
            //but childFragmentManager() may not be called so mRetainedChildFragmentManager is
            //yet set. If the fragment is rotated, the state of child fragment manager will be
            //lost since mRetainedChildFragmentManager hasn't set to be retained by the OS.
            retainedChildFragmentManager = getChildFragmentManager();
        }
    }

    private void refreshHosts(FragmentManager fragmentManager) throws IllegalAccessException {
        if (fragmentManager != null) {
            replaceFragmentManagerHost(fragmentManager);
        }

        //replace host(activity) of fragments already added
        List<Fragment> frags = fragmentManager.getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null) {
                    try {
                        //Copy the mHost(Activity) to retainedChildFragmentManager
                        Field mHostField = Fragment.class.getDeclaredField("mHost");
                        mHostField.setAccessible(true);
                        mHostField.set(f, currentHost);
                    } catch (Exception e) {

                    }
                    if (f.getChildFragmentManager() != null) {
                        refreshHosts(f.getChildFragmentManager());
                    }
                }
            }
        }
    }
    private void replaceFragmentManagerHost(FragmentManager fragmentManager) throws IllegalAccessException {
        if (currentHost != null) {
            mHostField.set(fragmentManager, currentHost);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.material_view_pager_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        applyMaterialToolbar();
        setHeaderContent();

    }

    public void setHeaderContent(){
        throw new NotImplementedException();
    }

    private void applyMaterialToolbar() {
        Toolbar toolbar = _materialViewPager.getToolbar();
        toolbar.setTitle("");
        ((RoboActionBarActivity) getActivity()).setSupportActionBar(toolbar);
        invalidateOptionsMenu(getActivity());
    }
}
