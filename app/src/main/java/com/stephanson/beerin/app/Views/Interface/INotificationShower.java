package com.stephanson.beerin.app.Views.Interface;

/**
 * Created by Stephan on 30.05.2016.
 */
public interface INotificationShower {
    void showShortSnack(int textId);
    void showLongSnack(int textId);

    void showShortSnack(String msg);
    void showLongSnack(String msg);

    void showShortSnack(String pattern, int...resources);
    void showLongSnack(String pattern, int...resources);
}
