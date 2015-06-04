package com.tripoin.pramia.view.activity;

import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.View;

import com.tripoin.pramia.R;

import java.util.ArrayList;
import java.util.List;

import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;


public class ActivityMain extends NavigationLiveo implements NavigationLiveoListener {

    private List<String> mListNameItem;

    @Override
    public void onUserInformation() {

    }

    @Override
    public void onInt(Bundle bundle) {
        this.setNavigationListener(this);
        mListNameItem = new ArrayList<>();
        mListNameItem.add(0, getString(R.string.add_order));
        mListNameItem.add(1, getString(R.string.menu_list));
        mListNameItem.add(2, getString(R.string.order_list));
        mListNameItem.add(3, getString(R.string.about));
        mListNameItem.add(4, getString(R.string.more_markers));
        mListNameItem.add(5, getString(R.string.change_ip));
        mListNameItem.add(6, getString(R.string.change_bluetooth));

        // icons list items
        List<Integer> mListIconItem = new ArrayList<>();
        mListIconItem.add(0, R.drawable.ic_add_to_photos_black_24dp);
        mListIconItem.add(1, R.drawable.ic_format_list_bulleted_black_24dp);
        mListIconItem.add(2, R.drawable.ic_format_list_bulleted_black_24dp);
        mListIconItem.add(3, R.drawable.ic_person_black_24dp);
        mListIconItem.add(4, 0);
        mListIconItem.add(5, R.drawable.ic_satellite_black_24dp);
        mListIconItem.add(6, R.drawable.ic_bluetooth_audio_black_24dp);


        List<Integer> mListHeaderItem = new ArrayList<>();
        mListHeaderItem.add(4);

        setDefaultStartPositionNavigation(2);

        SparseIntArray mSparseCounterItem = new SparseIntArray();
        mSparseCounterItem.put(1, 0);
        mSparseCounterItem.put(2, 0);

        //If not please use the FooterDrawer use the setFooterVisible(boolean visible) method with value false
        this.setFooterInformationDrawer(R.string.string_log_out, R.drawable.ic_settings_black_24dp);

        this.setNavigationAdapter(mListNameItem, mListIconItem, mListHeaderItem, mSparseCounterItem);
    }

    @Override
    public void onItemClickNavigation(int i, int i2) {

    }

    @Override
    public void onPrepareOptionsMenuNavigation(Menu menu, int i, boolean b) {

    }

    @Override
    public void onClickFooterItemNavigation(View view) {

    }

    @Override
    public void onClickUserPhotoNavigation(View view) {

    }
}
