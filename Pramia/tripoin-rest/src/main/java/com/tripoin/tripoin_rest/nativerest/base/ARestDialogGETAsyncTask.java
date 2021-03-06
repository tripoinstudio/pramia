package com.tripoin.tripoin_rest.nativerest.base;

import android.util.Log;

/**
 * Created by Achmad Fauzi on 11/25/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 *  This Class is used to communicate with REST method GET with progress bar
 */
public abstract class ARestDialogGETAsyncTask extends ABaseDialogRest{

    @Override
    protected String doInBackground(String... params) {
        Log.e("URL WS", processedURL());
        try {
            jsonObject = getJsonParser().retrieveJSONAsGet( processedURL(), params[0] );
            objectResult = objectMapper.readValue( String.valueOf(jsonObject), initClassResult() );
        } catch (Exception e) {
            Log.e("ERROR WEB SERVICE", e.toString());
            e.printStackTrace();
            objectResult = null;
        }
        return null;
    }
}
