package com.stephanson.beerin.app.Model;

/**
 * Created by Stephan on 13.04.2016.
 */
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Table(name = "Stock")
public class Stock extends Model{

    @Expose
    @SerializedName("availability")
    @Column(name = "availability")
    private Integer availability;

    @Expose
    @SerializedName("description")
    @Column(name = "description")
    private String description;

    @Expose
    @SerializedName("id")
    @Column(name = "stock_id")
    private Integer id;

    @Expose
    @SerializedName("name")
    @Column(name = "name")
    private String name;

    @Expose
    @SerializedName("photo_url")
    @Column(name = "photo_url")
    private String photoUrl;

    @Expose
    @SerializedName("place_id")
    @Column(name = "place_id")
    private Integer placeId;

    @Expose
    @SerializedName("price")
    @Column(name = "price")
    private String price;

    @Expose
    @SerializedName("rate")
    @Column(name = "rate")
    private Integer rate;

    @Expose
    @SerializedName("short_description")
    @Column(name = "short_description")
    private String shortDescription;

    /**
     *
     * @return
     * The availability
     */
    public Integer getAvailability() {
        return availability;
    }

    /**
     *
     * @param availability
     * The availability
     */
    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getStockId() {
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
     * The photoUrl
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     *
     * @param photoUrl
     * The photo_url
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The rate
     */
    public Integer getRate() {
        return rate;
    }

    /**
     *
     * @param rate
     * The rate
     */
    public void setRate(Integer rate) {
        this.rate = rate;
    }

    /**
     *
     * @return
     * The shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     *
     * @param shortDescription
     * The short_description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}