package com.stephanson.beerin.app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Stephan on 25.04.2016.
 */
public class PushNotification {

    @SerializedName("date")
    @Expose
    private long date;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("place_id")
    @Expose
    private Integer placeId;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * @return The date
     */
    public long getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(long date) {
        this.date = date;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The placeId
     */
    public Integer getPlaceId() {
        return placeId;
    }

    /**
     * @param placeId The place_id
     */
    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    /**
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}