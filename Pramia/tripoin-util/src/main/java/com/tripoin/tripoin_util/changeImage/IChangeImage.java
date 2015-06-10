package com.tripoin.tripoin_util.changeImage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Ginanjar Aji Sanjaya on 6/10/2015.
 */
public interface IChangeImage {
    public Intent takePictFromGallery();
    public Intent takePictFromCamera();
    public void actionTakeFromGallery(Intent data, FragmentActivity fragmentActivity);
    public void actionTakeFromCamera();
    public Bitmap getTakenImage();
    public String getTakenImagePath();
}
