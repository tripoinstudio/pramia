package com.tripoin.tripoin_rest.nativerest.base;

import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripoin.tripoin_common.GeneralConstant;
import com.tripoin.tripoin_rest.IJSONParser;
import com.tripoin.tripoin_rest.IRestSetup;
import com.tripoin.tripoin_rest.nativerest.IBaseRest;
import com.tripoin.tripoin_rest.nativerest.JSONParser;
import com.tripoin.tripoin_rest.nativesslrest.SSLJSONParser;
import com.tripoin.tripoin_util.property.impl.PropertyUtil;

import org.json.JSONObject;


/**
 * Created by Achmad Fauzi on 11/25/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 *
 * Base class Rest Communication
 */
public abstract class ABaseRest extends AsyncTask< String, String, String > implements IBaseRest, IRestSetup {

    protected ObjectMapper objectMapper = new ObjectMapper();
    protected Object objectResult;
    protected JSONObject jsonObject;
    protected PropertyUtil propertyUtil = new PropertyUtil(GeneralConstant.ApplicationProperty.APP_PROPERTY.toString(), getContext());


    protected ABaseRest() {
    }

    @Override
    public abstract Context getContext();

    @Override
    public abstract String initUrl();

    @Override
    public abstract Class<?> initClassResult();

    @Override
    public Object getObjectResult() {
        return objectResult;
    }

    @Override
    public String constructBaseURL(){
        return
            GeneralConstant.Rest.HTTP
            .concat(GeneralConstant.Punctuation.COLON)
            .concat(GeneralConstant.Punctuation.SLASH)
            .concat(GeneralConstant.Punctuation.SLASH)
            .concat(getServerHost())
            .concat(GeneralConstant.Punctuation.COLON)
            .concat(getServerPort())
            .concat(GeneralConstant.Punctuation.SLASH)
            .concat(GeneralConstant.Rest.WS_CONTEXT);
    }

    @Override
    public String getApplicationMode(){
        return GeneralConstant.ApplicationMessage.APP_MODE;
    }

    @Override
    public String getServerHost(){
        return propertyUtil.getValuePropertyMap(GeneralConstant.ApplicationProperty.SERVER_HOST_KEY);
    }

    @Override
    public String getServerPort(){
        return propertyUtil.getValuePropertyMap(GeneralConstant.ApplicationProperty.SERVER_PORT_KEY);
    }

    public IJSONParser getJsonParser(){
        if( getApplicationMode().equals(GeneralConstant.ApplicationMessage.DEVELOPMENT) ){
            return new JSONParser();
        }else if( getApplicationMode().equals(GeneralConstant.ApplicationMessage.PRODUCTION) ){
            return new SSLJSONParser();
        }else{
            return null;
        }
    }

    @Override
    public String processedURL() {
        return constructBaseURL().concat(initUrl());
    }
}
