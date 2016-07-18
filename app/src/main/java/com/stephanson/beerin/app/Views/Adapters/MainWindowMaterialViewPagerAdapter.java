package com.stephanson.beerin.app.Views.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.stephanson.beerin.app.R;
import com.stephanson.beerin.app.Views.PubsListFragment;
import com.stephanson.beerin.app.Views.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Electric Brains on 18.04.2016.
 */
public class MainWindowMaterialViewPagerAdapter extends FragmentStatePagerAdapter {

    private final int _tabCount = 1;
    private List<Fragment> _fragments;

    public MainWindowMaterialViewPagerAdapter(FragmentManager fm) {
        super(fm);
        _fragments = new ArrayList<>();
        _fragments.add(PubsListFragment.newInstance());
        //для более павного интерфейса думаю можно создать один раз и все
        //да и свичей сраных не надо
    }

    @Override
    public Fragment getItem(int position) {
       return _fragments.get(position % _tabCount);
    }

    @Override
    public int getCount() {
        return _fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
    switch (position % _tabCount) {
        case 0:
            return "Бары";
    }
    return "";

    }

    public static HeaderDesign getHeaderDesign(int page) {
        switch (page) {
            case 0:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.material_view_pager_background_color,
                        "http://www.huahin4russians.com/wp-content/uploads/huahin-bar-1024x685.jpg");
            case 1:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.material_view_pager_background_color,
                        "http://ufa-room.ru/uploads/ufa/2015/01/pivo_1.jpg");
            case 2:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.material_view_pager_background_color,
                        "http://img0.joyreactor.cc/pics/post/full/%D0%BF%D0%B8%D0%B2%D0%BE-%D0%BF%D0%B5%D1%81%D0%BE%D1%87%D0%BD%D0%B8%D1%86%D0%B0-%D0%BB%D0%B8%D1%87%D0%BD%D0%BE%D0%B5-241510.jpeg");
            case 3:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.material_view_pager_background_color,
                        "http://abali.ru/wp-content/uploads/2013/11/pivko.jpg");
        }
        return null;
    }

}
