package com.stephanson.beerin.app.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.stephanson.beerin.app.BeerApp;
import com.stephanson.beerin.app.Infrastructure.Interface.IRestClient;
import com.stephanson.beerin.app.Infrastructure.Persistance.IPersistance;
import com.stephanson.beerin.app.MainActivity;
import com.stephanson.beerin.app.Model.Pub;
import com.stephanson.beerin.app.Presenters.Interface.IPubsPresenter;
import com.stephanson.beerin.app.Presenters.PubsFragmentPresenter;
import com.stephanson.beerin.app.R;
import com.stephanson.beerin.app.Views.Abstract.NotificationShowerFragment;
import com.stephanson.beerin.app.Views.Adapters.ClickListeners.IPubClickListener;
import com.stephanson.beerin.app.Views.Adapters.PubsListViewAdapter;
import com.stephanson.beerin.app.Views.Interface.IPubsView;

import java.util.List;

/**
 * Created by Stephan on 23.03.2016.
 */
public class PubsListFragment extends NotificationShowerFragment implements IPubsView, IPubClickListener {
    RecyclerView _pubsList;
    SwipeRefreshLayout _refresher;

    private IPubsPresenter _presenter;
    private PubsListViewAdapter _pubsListAdapter;

    public static PubsListFragment newInstance()
    {
        PubsListFragment f = new PubsListFragment();
        f.setRetainInstance(true);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IRestClient client = ((BeerApp)getActivity().getApplication()).get_restClient();
        IPersistance persistance = ((BeerApp)getActivity().getApplication()).get_persistance();
        _presenter = new PubsFragmentPresenter(this, client, persistance, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.places_fragment, container, false);
        //сюда не пиши ничего
        setupSwipeToRefresh(view);

        _pubsList = (RecyclerView) view.findViewById(R.id.pubs_listview);
        _pubsList.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        RecyclerView.LayoutManager layManager = new LinearLayoutManager(getContext());
        _pubsList.setLayoutManager(layManager);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(),_pubsList);

        return view;
    }

    private void setupSwipeToRefresh(View root) {
        _refresher = (SwipeRefreshLayout)root.findViewById(R.id.swiperefresh);
        int startoffset = (int)getResources().getDimension(R.dimen.refresher_start_offset);
        int stopoffset = (int)getResources().getDimension(R.dimen.refresher_stop_offset);
        _refresher.setProgressViewOffset(false, startoffset, stopoffset);
        _refresher.setOnRefreshListener(() -> {
            _presenter.refreshRequested();
        });
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
        _presenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        _presenter.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        _presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        _presenter.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        _presenter.onDestroy();
    }

    @Override
    public void setPubsList(List<Pub> pubs) {
        if(_pubsListAdapter==null||_pubsList.getAdapter()==null)
        {
            _pubsListAdapter = new PubsListViewAdapter(getContext(), pubs, this, _presenter);
            _pubsList.setAdapter(_pubsListAdapter);
        }
        else _pubsListAdapter.updateData(pubs);
    }

    @Override
    public void setRefreshingInProgress(boolean refreshingInProgress) {
        if(_refresher != null)
        {
            _refresher.setRefreshing(refreshingInProgress);
        }
    }

    @Override
    public void setLoadingInProgress(boolean loadingInProgress) {
        getActivity().findViewById(R.id.progress_bar)
                     .setVisibility( loadingInProgress ? View.VISIBLE
                                                       : View.GONE);
    }

    @Override
    public void onPubClicked(Pub pub) {
        if(pub!=null)
        {
            MainActivity activity = (MainActivity)getActivity();
            Fragment frg = SpecificPubMaterialViewPagerFragment.newInstance(pub);
            activity.changeFragment(frg, true, true);
        }
    }

}
