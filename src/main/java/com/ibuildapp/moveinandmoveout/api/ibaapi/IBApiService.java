package com.ibuildapp.moveinandmoveout.api.ibaapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibuildapp.moveinandmoveout.model.ItemsContainerProp;
import com.ibuildapp.moveinandmoveout.model.sheets.SheetProperties;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class IBApiService {
    private  String baseUrl = "";

    public IBApiService(String domain){
        baseUrl = domain;
        if(!baseUrl.contains("http"))
            baseUrl = "http://"+baseUrl;
    }

    public  IBApi getIBApi(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ItemsContainerProp.class, new ItemsContainerPropDeserializer())
                .registerTypeAdapter(SheetProperties.class, new SheetPropertiesDeserializer()).create();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build().create(IBApi.class);
    }

    public IBApi getIBApi(int startPosition) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ItemsContainerProp.class, new ItemsContainerPropDeserializer(startPosition))
                .registerTypeAdapter(SheetProperties.class, new SheetPropertiesDeserializer()).create();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build().create(IBApi.class);
    }
}