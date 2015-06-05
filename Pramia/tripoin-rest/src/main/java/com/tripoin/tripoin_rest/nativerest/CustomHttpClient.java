package com.tripoin.tripoin_rest.nativerest;

import com.tripoin.tripoin_common.GeneralConstant;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

/**
 * Created by Achmad Fauzi on 1/3/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class CustomHttpClient {

    private int timeOut = GeneralConstant.Rest.DEFAULT_HTTP_TIMEOUT * 1000;
    private HttpClient httpClient;

    public HttpClient getHttpClient(){
        if( httpClient == null ){
            httpClient = new DefaultHttpClient();
            final HttpParams params = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout( params, timeOut );
            HttpConnectionParams.setSoTimeout( params, timeOut );
            HttpProtocolParams.setUseExpectContinue( httpClient.getParams(), false );
            ConnManagerParams.setTimeout( params, timeOut );
        }
        return httpClient;
    }
}
