package com.example.djangopdfview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonApi {
    String text="";
    void JsonApi(String text);
    @GET("show/")
    Call<List<Model>> getPosts();
    @GET("show/")
    Call<List<Model>> getseacrchPosts(@Query("search") String text);
}
