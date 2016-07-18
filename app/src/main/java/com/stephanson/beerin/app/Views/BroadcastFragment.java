package com.stephanson.beerin.app.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.stephanson.beerin.app.BeerApp;
import com.stephanson.beerin.app.Infrastructure.Interface.IRestClient;
import com.stephanson.beerin.app.Infrastructure.Persistance.IPersistance;
import com.stephanson.beerin.app.Model.Broadcast;
import com.stephanson.beerin.app.Model.Pub;
import com.stephanson.beerin.app.Presenters.BroadcastsPresenter;
import com.stephanson.beerin.app.Presenters.Interface.IBroadcastsPresenter;
import com.stephanson.beerin.app.R;
import com.stephanson.beerin.app.Views.Abstract.NotificationShowerFragment;
import com.stephanson.beerin.app.Views.Adapters.BroadcastListViewAdapter;
import com.stephanson.beerin.app.Views.Interface.IBroadcastsView;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

import java.util.List;

/**
 * Created by Electric Brains on 25.04.2016.
 */
public class BroadcastFragment extends NotificationShowerFragment implements IBroadcastsView {

    private RecyclerView _broadcastsList;
    private BroadcastListViewAdapter _bcListAdapter;
    private IBroadcastsPresenter _broadPresenter;
    private Pub _pub;
    private SwipeRefreshLayout _refresher;

    public static BroadcastFragment newInstance(Pub pub)
    {
        BroadcastFragment f = new BroadcastFragment();
        f.setRetainInstance(true);
        f._pub = pub;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IRestClient client = ((BeerApp)getActivity().getApplication()).get_restClient();
        IPersistance persistance = ((BeerApp)getActivity().getApplication()).get_persistance();
        _broadPresenter = new BroadcastsPresenter(_pub.getPubId(), this, client, persistance, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.broadcasts_fragment, container, false);
        setupSwipeToRefresh(view);

        _broadcastsList = (RecyclerView)view.findViewById(R.id.broadcsts_list);
        _broadcastsList.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        RecyclerView.LayoutManager layManager = new LinearLayoutManager(getContext());
        _broadcastsList.setLayoutManager(layManager);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), _broadcastsList);

        return view;
    }

    @Override
    public void setBroadcastsList(List<Broadcast> bcList) {
        if(_bcListAdapter==null||_broadcastsList.getAdapter()==null)
        {
        _bcListAdapter = new BroadcastListViewAdapter(getContext(), bcList);
        _broadcastsList.setAdapter(_bcListAdapter);
        }
        else _bcListAdapter.updateData(bcList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _broadPresenter.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
        _broadPresenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        _broadPresenter.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        _broadPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        _broadPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        _broadPresenter.onDestroy();
    }

    @Override
    public void setRefreshingInProgress(boolean b) {
        if(_refresher != null)
        {
            _refresher.setRefreshing(b);
        }
    }

    @Override
    public void setLoadingInProgress(boolean loadingInProgress) {
        getActivity().findViewById(R.id.progress_bar)
                .setVisibility( loadingInProgress ?View.VISIBLE
                                                  :View.GONE);
    }


    private void setupSwipeToRefresh(View root) {
        _refresher = (SwipeRefreshLayout)root.findViewById(R.id.swiperefresh);
        int startoffset = (int)getResources().getDimension(R.dimen.refresher_start_offset);
        int stopoffset = (int)getResources().getDimension(R.dimen.refresher_stop_offset);
        _refresher.setProgressViewOffset(false, startoffset, stopoffset);

        _refresher.setOnRefreshListener(() -> _broadPresenter.refreshRequested());

    }
}
