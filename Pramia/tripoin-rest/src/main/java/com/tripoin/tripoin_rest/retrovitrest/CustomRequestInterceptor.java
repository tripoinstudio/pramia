package com.tripoin.tripoin_rest.retrovitrest;

import com.tripoin.tripoin_common.GeneralConstant;
import com.tripoin.tripoin_util.GeneralConverter;

import retrofit.RequestInterceptor;

/**
 * Created by Achmad Fauzi on 5/26/2015 : 10:39 AM.
 * mail : achmad.fauzi@sigma.co.id
 */
public class CustomRequestInterceptor implements RequestInterceptor {

    private String credentials;

    public CustomRequestInterceptor(String credentials) {
        this.credentials = credentials;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader(
                GeneralConstant.Rest.AUTHORIZATION,
                GeneralConstant.Rest.BASIC.
                        concat(GeneralConstant.Punctuation.SPACE).
                        concat(new GeneralConverter().encodeToBase64(credentials))
        );
        request.addHeader(
                GeneralConstant.Rest.ACCEPT,
                GeneralConstant.Rest.APPLICATION_JSON
        );
    }

}
