package com.tripoin.tripoin_util.changeImage.impl;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.tripoin.tripoin_common.GeneralConstant;
import com.tripoin.tripoin_util.bitmapDecoder.impl.BitmapDecoderImpl;
import com.tripoin.tripoin_util.changeImage.IChangeImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by Ginanjar Aji Sanjaya on 6/10/2015.
 */
public class ChangeImageImpl implements IChangeImage {
    private Bitmap takenImage = null;
    private String takenImagePath = null;
    private BitmapDecoderImpl bitmapDecoder = new BitmapDecoderImpl();

    @Override
    public Intent takePictFromGallery() {
        return new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    @Override
    public Intent takePictFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f = new File(GeneralConstant.ApplicationProperty.APP_TARGET_DEFAULT_PATH.toString(), "photo_profile_rmu.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        // ******** code for crop image
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        return intent;
    }

    @Override
    public void actionTakeFromGallery(Intent data, FragmentActivity fragmentActivity) {
        Log.i("_ANJARGANTENG", "Sudah masuk startActivityForResult(intent, 1) ________________________________________________________________");
        Uri selectedImage = data.getData();
        String[] filePath = { MediaStore.Images.Media.DATA };
        Cursor c = fragmentActivity.getContentResolver().query(selectedImage,filePath, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePath[0]);
        String picturePath = c.getString(columnIndex);
        String destinationImagePath=null;
        try {
            destinationImagePath = GeneralConstant.ApplicationProperty.APP_TARGET_DEFAULT_PATH.toString()+"photo_profile_rmu.jpg";
            File source= new File(picturePath);
            File destination= new File(destinationImagePath);
            if (source.exists()) {
                FileChannel src = new FileInputStream(source).getChannel();
                FileChannel dst = new FileOutputStream(destination).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }
        } catch (Exception e) {
            Log.e("EROR DISINI","EROR DISINI ----- "+e.toString());
        }
        c.close();
        System.gc();
        Bitmap thumbnail;
        thumbnail = bitmapDecoder.decodeSampledBitmapFromPath(destinationImagePath, 360, 270);
        takenImagePath=destinationImagePath;
        takenImage=thumbnail;
    }

    @Override
    public void actionTakeFromCamera() {
        File f = new File(GeneralConstant.ApplicationProperty.APP_TARGET_DEFAULT_PATH.toString());
        for (File temp : f.listFiles()) {
            if (temp.getName().equals("photo_profile_rmu.jpg")) {
                f = temp;
                break;
            }
        }
        try {
            System.gc();
            Bitmap bitmap;
            Log.d("f.getAbsolutePath() = ", "-------------- " + f.getAbsolutePath());
            bitmap = bitmapDecoder.decodeSampledBitmapFromPath(f.getAbsolutePath(), 360, 270);
            takenImagePath=f.getAbsolutePath();
            takenImage=bitmap;
            String path = GeneralConstant.ApplicationProperty.APP_TARGET_DEFAULT_PATH.toString();
            f.delete();
            OutputStream outFile = null;
            File file = new File(path, "photo_profile_rmu" + ".jpg");
            try {
                outFile = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                outFile.flush();
                outFile.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bitmap getTakenImage() {
        return takenImage;
    }

    @Override
    public String getTakenImagePath() {
        return takenImagePath;
    }
}
