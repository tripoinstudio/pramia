package com.tripoin.tripoin_util.swipeRefresh.swipeRefresh;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.tripoin.tripoin_util.swipeRefresh.ISwipeRefresh;

/**
 * Created by Ginanjar Aji Sanjaya on 6/10/2015.
 */
public class SwipeRefresh implements ISwipeRefresh {

    @Override
    public void onRefreshAction(final SwipeRefreshLayout swipeView, final AsyncTask asyncTask) {
        swipeView.setColorScheme(android.R.color.holo_blue_dark,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_green_light);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                Log.d("Swipe", "Refreshing List");
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);
                        asyncTask.execute();
                        Log.d("Swipe", "Refreshing List Done");
                    }
                }, 3000);
            }
        });
    }
}
