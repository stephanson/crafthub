package com.stephanson.beerin.app.Views.Interface;

import com.stephanson.beerin.app.Model.Stock;

import java.util.List;

/**
 * Created by Stephan on 14.04.2016.
 */
public interface IStockView extends IRefreshableView{
    void setStockList(List<Stock> stocList);
}
