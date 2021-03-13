package com.chotabeta.ptask.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chotabeta.ptask.R;
import com.chotabeta.ptask.beans.Data;
import com.chotabeta.ptask.ui.adapters.DataAdapter;
import com.chotabeta.ptask.viewmodels.DataViewModel;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainScreen extends AppCompatActivity implements PaymentResultWithDataListener {

    @BindView(R.id.homeScreenData) RecyclerView homeScreenData;
    Unbinder unbinder;
    DataViewModel dataViewModel = new DataViewModel();
    DataAdapter dataAdapter;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        unbinder = ButterKnife.bind(this);
        homeScreenData.setHasFixedSize(true);
        homeScreenData.setLayoutManager(linearLayoutManager);
        homeScreenData.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        dataAdapter = new DataAdapter(this,new ArrayList<Data.DataBean>());
        homeScreenData.setAdapter(dataAdapter);
        dataViewModel.getDataFromRepo().observe(this, new Observer<ArrayList<Data.DataBean>>() {
            @Override
            public void onChanged(ArrayList<Data.DataBean> data) {
                // notify the adapter to update the UI on event occurred
                Log.d("getData onChanged:",data.size()+"");
                dataAdapter.addData(data);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.addMoneyToWallet)
    public void addMoneyToWallet() {
        Checkout.preload(getApplicationContext());
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_9lGrfJDxRdSmpY");
        checkout.setImage(R.drawable.rzp_logo);

        try {
            JSONObject options = new JSONObject();
            options.put("key","rzp_test_9lGrfJDxRdSmpY");
            options.put("amount", "50000");//pass amount in currency subunits
            options.put("currency", "INR");
            options.put("name", "Merchant Name");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", "order_Gm5PWjyqCQTV7H");//from response of step 3.
            options.put("prefill.name", "gaurav");
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","9988776655");
            options.put("notes","optional notes");
            options.put("theme.hide_topbar", true);
            options.put("theme.color", "#3399cc");
            options.put("theme.backdrop_color", "#3399cc");

            options.put("send_sms_hash", false);

            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(this,options);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Log.d("paymentStatus:success",paymentData.getOrderId()+"\n\n"+
                paymentData.getSignature()+"\n\n"+
                paymentData.getPaymentId()+"\n\n");
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Log.d("paymentStatus:error",i+""+s+""+paymentData.toString());
    }
}