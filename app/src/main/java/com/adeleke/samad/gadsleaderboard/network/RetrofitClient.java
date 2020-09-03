package com.adeleke.samad.gadsleaderboard.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofitList;
    private static Retrofit retrofitSubmit;
    private static final String BASE_LIST_URL = "https://gadsapi.herokuapp.com/";
    private static final String BASE_SUBMIT_URL = "https://docs.google.com/forms/d/e/";

    private RetrofitClient(){}

    public static Retrofit getRetrofitListInstance() {
        if (retrofitList == null) {
            retrofitList = new Retrofit.Builder()
                    .baseUrl(BASE_LIST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitList;
    }

    public static Retrofit getRetrofitSubmitInstance() {
        if (retrofitSubmit == null) {
            retrofitSubmit = new Retrofit.Builder()
                    .baseUrl(BASE_SUBMIT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitSubmit;
    }
}
