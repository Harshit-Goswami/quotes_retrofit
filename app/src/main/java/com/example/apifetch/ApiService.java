package com.example.apifetch;
import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

public interface ApiService {
    @GET("/api/quotes")
    Call<List<Model>> getAllData() ;
}
