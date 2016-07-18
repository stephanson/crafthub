package com.stephanson.beerin.app.Views.Interface;

import com.stephanson.beerin.app.Model.Broadcast;

import java.util.List;

/**
 * Created by Stephan on 17.04.2016.
 */
public interface IBroadcastsView extends IRefreshableView{
    void setBroadcastsList(List<Broadcast> bcList);
}
