package com.learn.binod.navigationdrawer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.learn.binod.navigationdrawer.R;

/**
 * Created by binod on 4/17/2017.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView nameTxt,addressTxt,descTxt;

    public MyViewHolder(View itemView) {
        super(itemView);
        nameTxt=(TextView) itemView.findViewById(R.id.nameTxt);
        addressTxt=(TextView) itemView.findViewById(R.id.addressTxt);
        descTxt=(TextView) itemView.findViewById(R.id.descTxt);
    }
}
