package com.stephanson.beerin.app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephan on 11.04.2016.
 */
public class Broadcasts {

    @SerializedName("broadcasts")
    @Expose
    private List<Broadcast> broadcasts = new ArrayList<Broadcast>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Broadcasts() {
    }

    /**
     *
     * @param broadcasts
     */
    public Broadcasts(List<Broadcast> broadcasts) {
        this.broadcasts = broadcasts;
    }

    /**
     *
     * @return
     * The broadcasts
     */
    public List<Broadcast> getBroadcasts() {
        return broadcasts;
    }

    /**
     *
     * @param broadcasts
     * The broadcasts
     */
    public void setBroadcasts(List<Broadcast> broadcasts) {
        this.broadcasts = broadcasts;
    }

}