package com.tripoin.tripoin_rest.nativerest.base;

import android.app.ProgressDialog;

/**
 * Created by Achmad Fauzi on 11/25/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * This Class is used to communicate with REST without progress bar
 */
public abstract class ABaseDialogRest extends ABaseRest{

    protected ProgressDialog progressDialog = new ProgressDialog( getContext() );

    protected abstract String getProgressDialogTitle();

    @Override
    protected void onPreExecute() {
        progressDialog.setTitle( getProgressDialogTitle() );
        progressDialog.setMessage( "Please Wait" );
        progressDialog.setCancelable( true );
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
    }

}
