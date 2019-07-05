package ru.ftc.android.shifttemple.network;

import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:32
 */

public final class RetrofitProvider {

    private static final String BASE_URL = "http://84.237.55.80:8081/api/v001/";

    private final Retrofit retrofit;

    public RetrofitProvider(List<Interceptor> interceptorList) {

        //TODO создаем Retrofit на основе URL
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//TODO Gson
                .client(createClient(interceptorList))
                .build();
        //TODO добавить GSON  .addConverterFactory(GsonConverterFactory.create()
    }

    private OkHttpClient createClient(List<Interceptor> interceptorList) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        for (Interceptor interceptor: interceptorList) {
            builder.addInterceptor(interceptor);
        }

        return builder.build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}