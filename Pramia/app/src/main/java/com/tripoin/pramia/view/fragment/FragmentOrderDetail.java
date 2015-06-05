package com.tripoin.pramia.view.fragment;

import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.pramia.ApplicationConstant;
import com.tripoin.pramia.R;
import com.tripoin.tripoin_common.GeneralConstant;
import com.tripoin.tripoin_component.ui.fragment.impl.ABaseNavigationDrawerFragment;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Achmad Fauzi on 6/6/2015 : 3:57 AM.
 * mail : achmad.fauzi@sigma.co.id
 */
public class FragmentOrderDetail extends ABaseNavigationDrawerFragment {

    @InjectView(R.id.txtSample) public TextView txtSample;

    private String mData;

    @Override
    public String getFragmentTitle() {
        return ApplicationConstant.FragmentTitle.ORDER_DETAIL;
    }

    @Override
    public void initWidget() {
        mData = "THIS IS Fragment Order Detail";
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_order_detail;
    }


    @OnClick(R.id.txtSample)
    public void clickSample(){
        Toast.makeText(getActivity(), "Textview clicked ".concat(mData), Toast.LENGTH_SHORT).show();
    }
}
