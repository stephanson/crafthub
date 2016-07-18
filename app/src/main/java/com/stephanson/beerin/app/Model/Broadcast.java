package com.stephanson.beerin.app.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Stephan on 11.04.2016.
 */
@Table(name = "Broadcasts")
public class Broadcast extends Model{

    @Expose
    @SerializedName("id")
    @Column(name = "brod_id")
    private Integer id;

    @Expose
    @SerializedName("last_update")
    @Column(name = "last_update")
    private Integer lastUpdate;

    @Expose
    @SerializedName("picture_url")
    @Column(name = "picture_url")
    private String pictureUrl;

    @Expose
    @SerializedName("place_id")
    @Column(name = "place_id")
    private Integer placeId;

    @Expose
    @SerializedName("text")
    @Column(name = "text")
    private String text;

    @Expose
    @SerializedName("title")
    @Column(name = "title")
    private String title;

    /**
     *
     * @return
     * The id
     */
    public Integer getBroadcastId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The lastUpdate
     */
    public Integer getLastUpdate() {
        return lastUpdate;
    }

    /**
     *
     * @param lastUpdate
     * The last_update
     */
    public void setLastUpdate(Integer lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     *
     * @return
     * The pictureUrl
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     *
     * @param pictureUrl
     * The picture_url
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     *
     * @return
     * The placeId
     */
    public Integer getPlaceId() {
        return placeId;
    }

    /**
     *
     * @param placeId
     * The place_id
     */
    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    /**
     *
     * @return
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}