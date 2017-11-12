package com.example.adesina.currencyexchange;

/**
 * Created by adesina on 11/2/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by EKENE on 7/23/2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {


    public static final String KEY_CURRENCY = "currency";
    public static final String KEY_BTC = "btc";
    public static final String KEY_ETH = "eth";

    private List<CurrencyList> developersLists;
    private Context context;

    public CurrencyAdapter(List<CurrencyList> developersLists, Context context) {

        this.developersLists = developersLists;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final CurrencyList currencyList = developersLists.get(position);
        holder.btc.setText(currencyList.getCurrency());



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CurrencyList currencyList1 = developersLists.get(position);

                Intent skipIntent = new Intent(v.getContext(), CurrencyActivity.class);
                skipIntent.putExtra(KEY_CURRENCY, currencyList1.getCurrency());
                skipIntent.putExtra(KEY_BTC, currencyList1.getBTC());
                skipIntent.putExtra(KEY_ETH, currencyList1.getEth());
                v.getContext().startActivity(skipIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return developersLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView currency;
        public TextView btc;
        public TextView eth;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            currency = (TextView) itemView.findViewById(R.id.currency);
            btc = (TextView) itemView.findViewById(R.id.btc);
            eth = (TextView) itemView.findViewById(R.id.eth);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }

    }
}
