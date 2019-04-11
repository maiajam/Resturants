
package com.maiajam.talabat.data.models.getOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetail {

    @SerializedName("prod_name")
    @Expose
    private String prodName;
    @SerializedName("prod_quantity")
    @Expose
    private Integer prodQuantity;
    @SerializedName("prod_price")
    @Expose
    private String prodPrice;
    @SerializedName("prod_image")
    @Expose
    private String prodImage;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(Integer prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdImage() {
        return prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }

}
