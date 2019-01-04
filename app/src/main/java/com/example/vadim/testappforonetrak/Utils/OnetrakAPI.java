package com.example.vadim.testappforonetrak.Utils;

import com.example.vadim.testappforonetrak.Model.Data;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface OnetrakAPI {
    @GET("intern/metric.json")
    Single<List<Data>> getData();
}
