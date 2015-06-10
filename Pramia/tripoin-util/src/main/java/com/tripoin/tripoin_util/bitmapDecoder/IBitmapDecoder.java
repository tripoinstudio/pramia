package com.tripoin.tripoin_util.bitmapDecoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Ginanjar Aji Sanjaya on 6/10/2015.
 */
public interface IBitmapDecoder {
    public Bitmap decodeSampledBitmapFromPath(String path, int reqWidth, int reqHeight);
    int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight);
}
