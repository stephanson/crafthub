package com.stephanson.beerin.app.Model;

/**
 * Created by Stephan on 05.04.2016.
 */

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pubs {

    @SerializedName("pubs")
    @Expose
    private List<Pub> pubs = new ArrayList<Pub>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Pubs() {
    }

    /**
     *
     * @param pubs
     */
    public Pubs(List<Pub> pubs) {
        this.pubs = pubs;
    }

    /**
     *
     * @return
     * The pubs
     */
    public List<Pub> getPubs() {
        return pubs;
    }

    /**
     *
     * @param pubs
     * The pubs
     */
    public void setPubs(List<Pub> pubs) {
        this.pubs = pubs;
    }

}