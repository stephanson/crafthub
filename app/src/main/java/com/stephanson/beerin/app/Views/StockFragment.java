package com.stephanson.beerin.app.Views;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.stephanson.beerin.app.Infrastructure.Interface.IRestClient;
import com.stephanson.beerin.app.Infrastructure.Persistance.IPersistance;
import com.stephanson.beerin.app.Model.Pub;
import com.stephanson.beerin.app.Model.Stock;
import com.stephanson.beerin.app.Presenters.Interface.IStockPresenter;
import com.stephanson.beerin.app.Presenters.StockPresenter;
import com.stephanson.beerin.app.Views.Abstract.NotificationShowerFragment;
import com.stephanson.beerin.app.Views.Adapters.StockListViewAdapter;
import com.stephanson.beerin.app.Views.Interface.IStockView;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.stephanson.beerin.app.BeerApp;
import com.stephanson.beerin.app.R;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

import java.util.List;

/**
 * Created by Stephan on 13.04.2016.
 */
public class StockFragment extends NotificationShowerFragment implements IStockView {

    private RecyclerView _stockList;
    private StockListViewAdapter _stockListAdapter;
    private IStockPresenter _stockPresenter;

    private Pub _pub;
    private SwipeRefreshLayout _refresher;

    public static StockFragment newInstance(Pub pub)
    {
        StockFragment f = new StockFragment();
        f.setRetainInstance(true);
        f._pub = pub;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IRestClient client = ((BeerApp)getActivity().getApplication()).get_restClient();
        IPersistance persistance = ((BeerApp)getActivity().getApplication()).get_persistance();
        _stockPresenter = new StockPresenter(_pub.getPubId(), this, client, persistance, this );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.main_stock_fragment, container, false);
        setupSwipeToRefresh(view);

        _stockList = (RecyclerView) view.findViewById(R.id.stock_list);
        _stockList.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        RecyclerView.LayoutManager layManager = new LinearLayoutManager(getContext());
        _stockList.setLayoutManager(layManager);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), _stockList);
        return view;
    }

    private void setupSwipeToRefresh(View root) {
        _refresher = (SwipeRefreshLayout)root.findViewById(R.id.swiperefresh);
        int startoffset = (int)getResources().getDimension(R.dimen.refresher_start_offset);
        int stopoffset = (int)getResources().getDimension(R.dimen.refresher_stop_offset);
        _refresher.setProgressViewOffset(false, startoffset, stopoffset);
        _refresher.setOnRefreshListener(() -> _stockPresenter.refreshRequested());
    }

    @Override
    public void setStockList(List<Stock> stockList) {
        if(_stockListAdapter==null||_stockList.getAdapter()==null)
        {
        _stockListAdapter = new StockListViewAdapter(getContext(), stockList);
        _stockList.setAdapter(_stockListAdapter);
        }
        else _stockListAdapter.updateData(stockList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        _stockPresenter.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
        _stockPresenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        _stockPresenter.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        _stockPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        _stockPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        _stockPresenter.onDestroy();
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
}
