package com.tripoin.pramia.view.fragment;

import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.pramia.R;
import com.tripoin.tripoin_component.ui.fragment.impl.ABaseNavigationDrawerFragment;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Achmad Fauzi on 6/4/2015 : 9:06 PM.
 * mail : achmad.fauzi@sigma.co.id
 */
public class FragmentSample extends ABaseNavigationDrawerFragment {

    @InjectView(R.id.txtSample) public TextView txtSample;

    private String m_data;

    @Override
    public String getFragmentTitle() {
        return "Fragment Sample";
    }

    @Override
    public void initWidget() {
        m_data = "Sample Fragment";
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_sample;
    }

    @OnClick(R.id.txtSample)
    public void onClickSample(){
        Toast.makeText(getActivity(), "I am clicked ".concat(m_data), Toast.LENGTH_SHORT).show();
    }
}
