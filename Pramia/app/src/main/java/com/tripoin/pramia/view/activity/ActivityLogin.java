package com.tripoin.pramia.view.activity;

import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.pramia.R;
import com.tripoin.tripoin_component.ui.activity.impl.ABaseActivity;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Achmad Fauzi on 6/4/2015 : 9:05 PM.
 * mail : achmad.fauzi@sigma.co.id
 */
public class ActivityLogin extends ABaseActivity{

    @InjectView(R.id.txtTitle) public TextView txtTitle;

    String myName;

    @Override
    public void initWidget() {
        myName = "Activity Login";
    }

    @Override
    public int getViewLayoutId() {
        return 0;
    }

    @Override
    public void goToMainView(String extraKey, String extraContent) {

    }

    @OnClick(R.id.txtTitle)
    public void onTitleClick(){
        Toast.makeText(this, "I am clicked ".concat(myName),Toast.LENGTH_SHORT).show();
    }
}
