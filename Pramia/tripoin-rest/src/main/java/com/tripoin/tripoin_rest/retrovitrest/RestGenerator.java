package com.tripoin.tripoin_rest.retrovitrest;

import com.squareup.okhttp.OkHttpClient;
import com.tripoin.tripoin_common.GeneralConstant;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Achmad Fauzi on 5/24/2015 : 12:21 AM.
 * mail : achmad.fauzi@sigma.co.id
 */
public class RestGenerator<S> {

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass,  String username, String password) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(GeneralConstant.Rest.BASE_URL_DEV)
                .setClient(new OkClient(new OkHttpClient()));

        if (username != null && password != null) {
            builder.setRequestInterceptor(
                    new CustomRequestInterceptor(
                            username.concat(GeneralConstant.Punctuation.COLON).concat(password))
            );
        }

        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}