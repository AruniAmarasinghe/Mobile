package com.edebtor.oclimb.edebtor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class crdit_histry_item_Adapter extends BaseAdapter {
    private Context mcontex;
    private List<crdit_histry_item> mCreditList;

    public crdit_histry_item_Adapter(Context mcontex, List<crdit_histry_item> mCreditList) {
        this.mcontex = mcontex;
        this.mCreditList = mCreditList;
    }

    @Override
    public int getCount() {
        return mCreditList.size();
    }

    @Override
    public Object getItem(int i) {
        return mCreditList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(mcontex, R.layout.item_cridit_histry, null);
        TextView cr_price = v.findViewById(R.id.credit_Price);
        TextView de_name = v.findViewById(R.id.Debetor_name);
        TextView cr_date = v.findViewById(R.id.credit_date);

        cr_price.setText("Rs."+mCreditList.get(i).getCredit());
        de_name.setText(mCreditList.get(i).getDebetor_name());
        cr_date.setText(mCreditList.get(i).getDate());


        v.setTag(mCreditList.get(i).getId());


        return v;
    }

}
