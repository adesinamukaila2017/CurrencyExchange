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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by EKENE on 7/23/2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {


    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_URL = "url";

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
        holder.login.setText(currencyList.getLogin());

        Picasso.with(context)
                .load(currencyList.getAvatar_url())
                .into(holder.avatar_url);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CurrencyList currencyList1 = developersLists.get(position);

                Intent skipIntent = new Intent(v.getContext(), CurrencyActivity.class);
                skipIntent.putExtra(KEY_NAME, currencyList1.getLogin());
                skipIntent.putExtra(KEY_URL, currencyList1.getHtml_url());
                skipIntent.putExtra(KEY_IMAGE, currencyList1.getAvatar_url());
                v.getContext().startActivity(skipIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return developersLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView login;
        public ImageView avatar_url;
        public TextView html_url;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            login = (TextView) itemView.findViewById(R.id.username);
            avatar_url = (ImageView) itemView.findViewById(R.id.imageView);
            html_url = (TextView) itemView.findViewById(R.id.htmUrl);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }

    }
}
