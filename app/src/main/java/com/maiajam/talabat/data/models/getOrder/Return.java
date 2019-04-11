
package com.maiajam.talabat.data.models.getOrder;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Return {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_user")
    @Expose
    private String orderUser;
    @SerializedName("order_price")
    @Expose
    private String orderPrice;
    @SerializedName("user_location")
    @Expose
    private String userLocation;
    @SerializedName("resturant_name")
    @Expose
    private String resturantName;
    @SerializedName("order_details")
    @Expose
    private List<OrderDetail> orderDetails = null;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getResturantName() {
        return resturantName;
    }

    public void setResturantName(String resturantName) {
        this.resturantName = resturantName;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
