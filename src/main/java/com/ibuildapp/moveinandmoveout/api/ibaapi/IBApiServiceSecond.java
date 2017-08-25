package com.ibuildapp.moveinandmoveout.api.ibaapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibuildapp.moveinandmoveout.model.ItemsContainerMove;
import com.ibuildapp.moveinandmoveout.model.ItemsContainerProp;
import com.ibuildapp.moveinandmoveout.model.sheets.SheetProperties;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class IBApiServiceSecond {
    private  String baseUrl = "";

    public IBApiServiceSecond(String domain){
        baseUrl = domain;
        if(!baseUrl.contains("http"))
            baseUrl = "http://"+baseUrl;
    }

    public  IBApiSecond getIBApi(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ItemsContainerMove.class, new ItemsContainerMoveDeserializer())
                .registerTypeAdapter(SheetProperties.class, new SheetPropertiesDeserializer()).create();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build().create(IBApiSecond.class);
    }

    public IBApiSecond getIBApi(int startPosition) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ItemsContainerMove.class, new ItemsContainerMoveDeserializer(startPosition))
                .registerTypeAdapter(SheetProperties.class, new SheetPropertiesDeserializer()).create();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build().create(IBApiSecond.class);
    }
}