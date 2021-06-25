package com.yeremiaadielyogasasongko.uajy.ndeleleng.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String Base_URL = "http://192.168.100.3:6969/api/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                            new OkHttpClient.Builder()
                                    .addInterceptor(
                                            new HttpLoggingInterceptor()
                                                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                                    )
                                    .build()
                    )
                    .build();
        }

        return retrofit;
    }
}
