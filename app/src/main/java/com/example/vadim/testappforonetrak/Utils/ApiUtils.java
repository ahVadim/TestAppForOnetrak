package com.example.vadim.testappforonetrak.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {


    public static final String BASE_URL = "https://intern-f6251.firebaseio.com/";

    private static OkHttpClient mOkHttpClient;
    private static Retrofit retrofit;
    private static Gson gson;
    private static OnetrakAPI api;

    public static OkHttpClient getOkHttpClient(){
        if (mOkHttpClient == null){
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return mOkHttpClient;
    }


    public static Retrofit getRetrofit(){
        if(gson == null) {
            gson = new Gson();
        }
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OnetrakAPI getApi(){
        if (api == null){
            api = getRetrofit().create(OnetrakAPI.class);
        }
        return api;
    }
}
