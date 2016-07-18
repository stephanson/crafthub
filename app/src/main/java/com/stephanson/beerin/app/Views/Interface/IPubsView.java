package com.stephanson.beerin.app.Views.Interface;

import com.stephanson.beerin.app.Model.Pub;

import java.util.List;

/**
 * Created by Stephan on 05.04.2016.
 */
public interface IPubsView extends IRefreshableView{
    void setPubsList(List<Pub> pubs);
}
