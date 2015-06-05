package com.tripoin.tripoin_util.downloader.impl;

import com.tripoin.tripoin_util.downloader.IFileDownloader;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Achmad Fauzi on 6/6/2015 : 12:45 AM.
 * mail : achmad.fauzi@sigma.co.id
 */
public class FileDownloader implements IFileDownloader {

    @Override
    public void download(String urlSource, String pathTarget) {
        try {
            File file = new File(pathTarget);
            if(!file.isFile()){
                URL url = new URL(urlSource);

                URLConnection ucon = url.openConnection();
                InputStream is = ucon.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                ByteArrayBuffer baf = new ByteArrayBuffer(50);
                int current = 0;
                while ((current = bis.read()) != -1) {
                    baf.append((byte) current);
                }

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(baf.toByteArray());
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
