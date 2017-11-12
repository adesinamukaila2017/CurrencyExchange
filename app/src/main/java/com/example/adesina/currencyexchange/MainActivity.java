package com.example.adesina.currencyexchange;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/** URL to query the crypto to compare to access price and rate information */
public class MainActivity extends AppCompatActivity {
    private static final String URL_DATA = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=" +
            "AFN,DZD,NGN,USD,EUR,INR,GBP,EGP,JPY,GBP,AUD,CAD,CHF,XOF,CNY,KES,GHS,UGX,ZAR,XAF,NZD,MYR,RUB,BND,GEL";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CurrencyList> developersLists;
    String requestUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        developersLists = new ArrayList<>();

        loadUrlData();
    }

    private void loadUrlData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, requestUrl,  null,

        //StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();
                try {

                    JSONObject btc_rates = response.getJSONObject("BTC".trim());

                    JSONObject eth_rates = response.getJSONObject("ETH".trim());

                    Iterator<?> keysBTC = btc_rates.keys();
                    Iterator<?> keysETH = eth_rates.keys();

                    while (keysBTC.hasNext() && keysETH.hasNext()) {
                        String keyBTC = (String) keysBTC.next();
                        String keyETH = (String) keysETH.next();

                        CurrencyList developers = new CurrencyList(keyBTC, btc_rates.getDouble(keyBTC), eth_rates.getDouble(keyETH));

                       developersLists.add(developers);

                    }

                    adapter = new CurrencyAdapter(developersLists, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

