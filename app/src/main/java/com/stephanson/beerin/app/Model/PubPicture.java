package com.stephanson.beerin.app.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Stephan on 29.05.2016.
 */
@Table(name = "PubPicture")
public class PubPicture extends Model {

    @Expose
    @SerializedName("url")
    @Column(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "pub", onDelete = Column.ForeignKeyAction.CASCADE)
    public Pub pub;

}