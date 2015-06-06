package com.tripoin.pramia.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.View;

import com.tripoin.pramia.R;
import com.tripoin.pramia.view.fragment.FragmentSample;

import java.util.ArrayList;
import java.util.List;

import br.liveo.Model.HelpLiveo;
import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.navigationliveo.NavigationLiveo;


public class ActivityMain extends NavigationLiveo implements OnItemClickListener {

    private HelpLiveo mHelpLiveo;


    @Override
    public void onInt(Bundle bundle) {
        this.userName.setText("Rudson Lima");
        this.userEmail.setText("rudsonlive@gmail.com");
        this.userPhoto.setImageResource(R.drawable.ic_rudsonlive);
        this.userBackground.setImageResource(R.drawable.ic_user_background_second);

        mHelpLiveo = new HelpLiveo();

        mHelpLiveo.add("Add Order", R.drawable.ic_add_to_photos_black_24dp);
        mHelpLiveo.add(getString(R.string.menu_list), R.drawable.ic_format_list_bulleted_black_24dp);
        mHelpLiveo.add(getString(R.string.order_list), R.drawable.ic_format_list_bulleted_black_24dp, 7);
        mHelpLiveo.add(getString(R.string.about), R.drawable.ic_person_black_24dp);
        mHelpLiveo.addSeparator();
        mHelpLiveo.addSubHeader(getString(R.string.settings));
        mHelpLiveo.add(getString(R.string.change_ip), R.drawable.ic_satellite_black_24dp);
        mHelpLiveo.add(getString(R.string.change_bluetooth), R.drawable.ic_bluetooth_audio_black_24dp);

       /* mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.app_name), R.drawable.ic_add_to_photos_black_24dp, 7);
        mHelpLiveo.addSubHeader(getString(R.string.app_name)); //Item subHeader
        mHelpLiveo.add(getString(R.string.app_name), R.drawable.ic_add_to_photos_black_24dp);
        mHelpLiveo.add(getString(R.string.app_name), R.drawable.ic_add_to_photos_black_24dp);
        mHelpLiveo.add(getString(R.string.app_name), R.drawable.ic_add_to_photos_black_24dp);
        mHelpLiveo.addSeparator(); // Item separator
        mHelpLiveo.add(getString(R.string.trash), R.drawable.ic_add_to_photos_black_24dp);
        mHelpLiveo.add(getString(R.string.spam), R.drawable.ic_add_to_photos_black_24dp, 120);*/


        with(this).startingPosition(2)
                .addAllHelpItem(mHelpLiveo.getHelp())
                .colorItemSelected(R.color.nliveo_blue_colorPrimary)
                .colorNameSubHeader(R.color.nliveo_blue_colorPrimary)
                .footerItem(R.string.string_log_out, R.drawable.ic_settings_black_24dp)
                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                .build();
    }

    @Override
    public void onItemClick(int position) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentSample fragmentSample = null;
        Log.d("position", String.valueOf(position));
        switch (position){
            case 0: fragmentSample = new FragmentSample();
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentSample).addToBackStack(null).commit();
                break;
        }
    }

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };
}
