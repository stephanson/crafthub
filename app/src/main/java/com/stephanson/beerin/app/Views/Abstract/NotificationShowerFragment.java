package com.stephanson.beerin.app.Views.Abstract;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.stephanson.beerin.app.Views.Interface.INotificationShower;

import roboguice.fragment.RoboFragment;

/**
 * Created by Stephan on 30.05.2016.
 */
public abstract class NotificationShowerFragment extends RoboFragment implements INotificationShower{

    private View _root;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _root = view;
    }

    @Override
    public void showShortSnack(int textId) {
        if(_root!=null)
            Snackbar.make(_root, textId, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLongSnack(int textId) {
        if(_root!=null)
            Snackbar.make(_root, textId, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showShortSnack(String msg) {
        if(_root!=null)
            Snackbar.make(_root, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLongSnack(String msg) {
        if(_root!=null)
            Snackbar.make(_root, msg, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void showShortSnack(String pattern, int...resources)
    {
        if(_root==null)return;
        showShortSnack(compileMessage(pattern, resources));
    }

    @Override
    public void showLongSnack(String pattern, int...resources)
    {
        if(_root==null)return;
        showLongSnack(compileMessage(pattern, resources));
    }

    private String compileMessage(String pattern, int[] resources)
    {
        String[] texsts = new  String[resources.length];
        for(int i = 0; i<resources.length; i++)
        {
            texsts[i] = getResources().getString(resources[i]);
        }
        String message = String.format(pattern, texsts);
        return message;
    }


}
