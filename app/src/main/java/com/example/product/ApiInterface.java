package com.example.product;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/products")
    Call<DataModel> addProduct(@Body DataModel dataModel);
}
