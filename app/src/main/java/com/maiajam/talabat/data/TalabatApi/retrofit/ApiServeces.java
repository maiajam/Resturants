package com.maiajam.talabat.data.TalabatApi.retrofit;

import com.maiajam.talabat.data.models.getOrder.GetOrder;
import com.maiajam.talabat.data.models.getOrder.Return;
import com.maiajam.talabat.data.models.getReatrurant.GetResturent;
import com.maiajam.talabat.data.models.login.Login;
import com.maiajam.talabat.data.models.loginData.LoginData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServeces {

    @POST("login")
    Call<ArrayList<Login>> Login(@Body LoginData data);

    @POST("getResturants")
    @FormUrlEncoded
    Call<GetResturent> getRestList(@Field("langu") String lang);

    @POST("getOrder")
    @FormUrlEncoded
    Call<GetOrder> getOrder(@Field("restId") String name, @Field("langu") String lang);

}
