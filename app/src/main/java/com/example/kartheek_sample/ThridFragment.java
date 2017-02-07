package com.example.kartheek_sample;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kartheek_sample.models.Payments;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.kartheek_sample.R.id.orderid;


public class ThridFragment extends Fragment {
    private TextView FirstName, LastName, address, phone;
    private TextView orderId, empId, total, createdDate, paymentType;
    private View rootView;
    private Context context;
    OkHttpClient client;
    MediaType JSON;
    String firstname, lastname, phoneno, addressdetails, orderID, empid, Total, createddate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.thirdscreen, container, false);

        FirstName = (TextView) rootView.findViewById(R.id.first);
        LastName = (TextView) rootView.findViewById(R.id.last);
        address = (TextView) rootView.findViewById(R.id.address);
        phone = (TextView) rootView.findViewById(R.id.phone);

        orderId = (TextView) rootView.findViewById(orderid);
        empId = (TextView) rootView.findViewById(R.id.employeeid);
        total = (TextView) rootView.findViewById(R.id.total);
        createdDate = (TextView) rootView.findViewById(R.id.created_date);
        paymentType = (TextView) rootView.findViewById(R.id.payment_type);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            firstname = getArguments().getString("name");
            FirstName.setText("First Name : " + firstname);
            lastname = getArguments().getString("last");
            LastName.setText("Last Name  : " + lastname);
            phoneno = getArguments().getString("phone");
            phone.setText("Phone      : " + phoneno);
            addressdetails = getArguments().getString("address");
            address.setText("Address    : " + addressdetails);


            Payments pymtObj = (Payments) getArguments().getSerializable("paymentObj");
            orderID = pymtObj.getOrderId();
            orderId.setText("Order Id     : " + orderID);
            empid = pymtObj.getEmployeeId();
            empId.setText("Employee Id  : " + empid);

            Total = pymtObj.getAmount();
            total.setText("Total        : " + Total);

            createddate = getDateFromTimeStamp(pymtObj.getCreatedTime());
            createdDate.setText("Created Date : " + createddate);


            if (!TextUtils.isEmpty(pymtObj.getTenders().getLabel()) && pymtObj.getTenders().getLabel().equalsIgnoreCase("Credit Card")) {
                paymentType.setText(pymtObj.getTenders().getLabel() + " : " + pymtObj.getCardtranscation().getCardType() + "\n\nLast 4 Digits : " + pymtObj.getCardtranscation().getAuthCode());
            } else {
                paymentType.setText("Type : " + pymtObj.getTenders().getLabel());
            }
            new Download().execute();
        }

    }

    private String getDateFromTimeStamp(String data) {
        String date = "";
        try {
            String tStamp = new BigDecimal(data).toBigIntegerExact().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
            Date netDate = (new Date(Long.parseLong(tStamp)));
            date = sdf.format(netDate);
            Log.v("Date: ", "" + sdf.format(netDate));

        } catch (NumberFormatException | ArithmeticException e) {
            e.printStackTrace();
        }
        return date;
    }

    class Download extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            JSON = MediaType.parse("application/json; charset=utf-8");
            client = new OkHttpClient();

            JSON = MediaType.parse("application/json; charset=utf-8");

            RequestBody f = new FormBody.Builder()
                    .add("firstname", firstname)
                    .add("lastname", lastname)
                    .add("phone", phoneno)
                    .add("address", addressdetails)
                    .add("orderid", orderID)
                    .add("empid", empid)
                    .add("createddate", createddate)
                    .build();

            Request request = new Request.Builder().url("http://178.62.187.32/demo/order.php").post(f).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("failed");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String output = response.body().toString();
                    System.out.println("output" + output);
                }
            });


            return null;

        }
    }
}

