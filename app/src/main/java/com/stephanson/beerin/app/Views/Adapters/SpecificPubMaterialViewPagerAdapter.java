package com.stephanson.beerin.app.Views.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.stephanson.beerin.app.Model.Broadcast;
import com.stephanson.beerin.app.Model.Pub;
import com.stephanson.beerin.app.R;
import com.stephanson.beerin.app.Views.BroadcastFragment;
import com.stephanson.beerin.app.Views.PubLargeInfoFragment;
import com.stephanson.beerin.app.Views.StockFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Electric Brains on 18.04.2016.
 */
public class SpecificPubMaterialViewPagerAdapter extends FragmentStatePagerAdapter {

    private static Pub _pub;
    private final int _tabCount = 3;
    private List<Fragment> _fragments;

    public SpecificPubMaterialViewPagerAdapter(FragmentManager fm, Pub pub) {
        super(fm);
         _pub = pub;
        _fragments = new ArrayList<Fragment>();
        _fragments.add(StockFragment.newInstance(_pub));
        _fragments.add(BroadcastFragment.newInstance(_pub));
        _fragments.add(PubLargeInfoFragment.newInstance(_pub));

    }

    @Override
    public Fragment getItem(int position) {
       return  _fragments.get(position);
    }


    @Override
    public int getCount() {
        return _fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
    switch (position % _tabCount) {
        case 0:
            return "Ассортимент";
        case 1:
            return "Новости";
        case 2:
            return "О нас";
    }
    return "";
    }

    public static HeaderDesign getHeaderDesign(int page) {
        if( _pub.getBackgroundUrl() != "")
            return HeaderDesign.fromColorResAndUrl(R.color.material_view_pager_background_color, _pub.getBackgroundUrl());
        else
            return null;
    }

}
