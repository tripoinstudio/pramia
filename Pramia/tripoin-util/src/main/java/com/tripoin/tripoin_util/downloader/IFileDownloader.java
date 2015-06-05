package com.tripoin.tripoin_util.downloader;

/**
 * Created by Achmad Fauzi on 6/6/2015 : 12:50 AM.
 * mail : achmad.fauzi@sigma.co.id
 */
public interface IFileDownloader {

    /**
     * Download image and save into a selected directory
     */
    public void download(String urlSource, String pathTarget);

}
