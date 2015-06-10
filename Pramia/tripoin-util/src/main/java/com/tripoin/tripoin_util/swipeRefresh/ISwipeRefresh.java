package com.tripoin.tripoin_util.swipeRefresh;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by Ginanjar Aji Sanjaya on 6/10/2015.
 */
public interface ISwipeRefresh {

    public void onRefreshAction(final SwipeRefreshLayout swipeView, final AsyncTask asyncTask);
}
