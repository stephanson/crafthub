package com.stephanson.beerin.app.Model;

/**
 * Created by Stephan on 05.04.2016.
 */
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Pubs")
public class Pub extends Model{

    @Expose
    @Column(name = "addres")
    @SerializedName("addres")
    private String addres;

    @Expose
    @SerializedName("background_url")
    @Column(name = "backgroundUrl")
    private String backgroundUrl;

    @Expose
    @SerializedName("descr")
    @Column(name = "descr")
    private String descr;

    @Expose
    @SerializedName("id")
    @Column(name = "pub_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private Integer id;

    @Expose
    @SerializedName("isActive")
    @Column(name = "isActive")
    private Boolean isActive;

    @Expose
    @SerializedName("name")
    @Column(name = "name")
    private String name;

    @Expose
    @SerializedName("phone")
    @Column(name = "phone")
    private String phone;

    @SerializedName("pictures")
    @Expose
    private List<PubPicture> pictures = new ArrayList<PubPicture>();

    @Expose
    @SerializedName("work_time")
    @Column(name = "work_time")
    private String workTime;

    /**
     *
     * @return
     * The addres
     */
    public String getAddres() {
        return addres;
    }

    /**
     *
     * @param addres
     * The addres
     */
    public void setAddres(String addres) {
        this.addres = addres;
    }

    /**
     *
     * @return
     * The backgroundUrl
     */
    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    /**
     *
     * @param backgroundUrl
     * The background_url
     */
    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    /**
     *
     * @return
     * The descr
     */
    public String getDescr() {
        return descr;
    }

    /**
     *
     * @param descr
     * The descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getPubId() {
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
     * The isActive
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     *
     * @param isActive
     * The isActive
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The pictures
     */
    public List<PubPicture> getPictures() {
        return pictures;
    }

    /**
     *
     * @param pictures
     * The pictures
     */
    public void setPictures(List<PubPicture> pictures) {
        this.pictures = pictures;
    }

    /**
     *
     * @return
     * The workTime
     */
    public String getWorkTime() {
        return workTime;
    }

    /**
     *
     * @param workTime
     * The work_time
     */
    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

}