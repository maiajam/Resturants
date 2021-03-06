
package com.maiajam.talabat.data.models.getReatrurant;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetResturent {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("sub_message")
    @Expose
    private String subMessage;
    @SerializedName("return")
    @Expose
    private List<GetRestReturn> _return = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    public List<GetRestReturn> getReturn() {
        return _return;
    }

    public void setReturn(List<GetRestReturn> _return) {
        this._return = _return;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
