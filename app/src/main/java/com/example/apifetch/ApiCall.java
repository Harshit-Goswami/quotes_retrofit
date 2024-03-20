package com.example.apifetch;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ApiCall {
Retrofit retrofit=new Retrofit.Builder().baseUrl("https://zenquotes.io/").addConverterFactory(
            GsonConverterFactory.create()).build();
ApiService service = retrofit.create(ApiService.class);
//Call call = (Call) service.getAllData();


}
