package com.tripoin.tripoin_util.property.base;

import android.content.Context;

import com.tripoin.tripoin_util.property.IPropertyUtil;


/**
 * Created by Achmad Fauzi on 11/29/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 *
 */
public abstract class APropertyUtil extends APropertyUtilBase implements IPropertyUtil {

    protected APropertyUtil(String fileName, Context context) {
        super(fileName, context);
    }
}
