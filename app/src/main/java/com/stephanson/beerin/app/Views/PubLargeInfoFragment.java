package com.stephanson.beerin.app.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.stephanson.beerin.app.Model.Pub;
import com.stephanson.beerin.app.Model.PubPicture;
import com.stephanson.beerin.app.R;
import com.stephanson.beerin.app.Views.Adapters.PubFotoGalaryAdapter;
import com.stephanson.beerin.app.Views.CustomViews.LoopViewPager;
import me.relex.circleindicator.CircleIndicator;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

import java.util.List;

/**
 * Created by Electric Brains on 26.05.2016.
 */
public class PubLargeInfoFragment extends RoboFragment {
    @InjectView(R.id.pub_large_info)
    private ObservableScrollView _scrollView;
    @InjectView(R.id.pub_info_time)
    private TextView _timeOfWork;
    @InjectView(R.id.pub_info_descr)
    private TextView _description;
    @InjectView(R.id.pub_info_phone)
    private TextView _phone;
    @InjectView(R.id.pub_info_addres)
    private TextView _addres;
    @InjectView(R.id.auto_galary)
    private LoopViewPager _fotoGalary;
    @InjectView(R.id.auto_galary_indicator)
    private CircleIndicator _fotoGalaryIndicator;
    private Pub _pub;

    public static PubLargeInfoFragment newInstance(Pub pub)
    {
        PubLargeInfoFragment f = new PubLargeInfoFragment();
        f.setRetainInstance(true);
        f._pub = pub;
        return f;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.pub_large_info_fragment, container, false);
        //сюда не пиши ничего
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialViewPagerHelper.registerScrollView(getActivity(), _scrollView, null);
        setTimeOfWork(_pub.getWorkTime());
        setAdress(_pub.getAddres());
        setDescription(_pub.getDescr());
        setPhone(_pub.getPhone());
        setFotoGalary(_pub.getPictures());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setDescription(String description)
    {
        _description.setText(description);
    }

    private void  setPhone(String phone)
    {
        _phone.setText(phone);
    }

    private void setAdress(String adress)
    {
        _addres.setText(adress);
    }

    private void setTimeOfWork(String timeOfWork)
    {
        _timeOfWork.setText(timeOfWork);
    }

    private void setFotoGalary(List<PubPicture> urls) {
        if (urls.size() != 0){
            _fotoGalary.setAdapter(new PubFotoGalaryAdapter(getContext(), urls));
            _fotoGalaryIndicator.setViewPager(_fotoGalary);
            }
    }


}
