package com.stephanson.beerin.app.Model;

/**
 * Created by Stephan on 13.04.2016.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stocks {

    @SerializedName("stock")
    @Expose
    private List<Stock> stock = new ArrayList<Stock>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Stocks() {
    }

    /**
     *
     * @param stock
     */
    public Stocks(List<Stock> stock) {
        this.stock = stock;
    }

    /**
     *
     * @return
     * The stock
     */
    public List<Stock> getStocks() {
        return stock;
    }

    /**
     *
     * @param stock
     * The stock
     */
    public void setStocks(List<Stock> stock) {
        this.stock = stock;
    }

}