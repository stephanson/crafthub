package com.stephanson.beerin.app.Presenters.Interface;

import com.stephanson.beerin.app.Model.Pub;

/**
 * Created by Stephan on 05.04.2016.
 */
public interface IPubsPresenter extends IBaseFragmentPresenter
{
    void subscribeToPubPush(Pub pub);
    boolean isSubscribeToPubPush(Pub pub);
}
