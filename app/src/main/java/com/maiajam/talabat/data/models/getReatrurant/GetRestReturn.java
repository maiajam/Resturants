
package com.maiajam.talabat.data.models.getReatrurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRestReturn {

    @SerializedName("rest_id")
    @Expose
    private String restId;
    @SerializedName("rest_name")
    @Expose
    private String restName;
    @SerializedName("rest_img")
    @Expose
    private String restImg;
    @SerializedName("rest_location")
    @Expose
    private String restLocation;
    @SerializedName("rest_type")
    @Expose
    private String restType;

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestImg() {
        return restImg;
    }

    public void setRestImg(String restImg) {
        this.restImg = restImg;
    }

    public String getRestLocation() {
        return restLocation;
    }

    public void setRestLocation(String restLocation) {
        this.restLocation = restLocation;
    }

    public String getRestType() {
        return restType;
    }

    public void setRestType(String restType) {
        this.restType = restType;
    }

}
