package com.example.kartheek_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kartheek_sample.models.CardTransaction;
import com.example.kartheek_sample.models.Item;
import com.example.kartheek_sample.models.Order;
import com.example.kartheek_sample.models.Payments;
import com.example.kartheek_sample.models.SectionItem;
import com.example.kartheek_sample.models.Tenders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SecondFragment extends Fragment {

    private ArrayList<Item> OrderHistoryList;
    private View rootView;
    ListView ordersListview;
    Bundle args = new Bundle();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_second, container, false);
        ordersListview = (ListView) rootView.findViewById(R.id.orderslist);
        if(getArguments()!=null)
        {
            args.putString("name", getArguments().getString("name"));
            args.putString("last", getArguments().getString("last"));
            args.putString("phone", getArguments().getString("phone"));
            args.putString("address", getArguments().getString("address"));
        }
        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray orders_jArry = obj.getJSONArray("orders");

            List<Payments> OrdersList = new ArrayList<>();
            for (int i = 0; i < orders_jArry.length(); i++) {


                JSONArray  payments_array =  orders_jArry.getJSONObject(i).getJSONArray("payments");

                JSONObject basic_data  = payments_array.getJSONObject(0);
                Payments pymts = new Payments();
                pymts.setId(basic_data.optString("id"));
                pymts.setAmount(""+basic_data.get("amount"));
                pymts.setCreatedTime(""+ basic_data.get("createdTime"));
                pymts.setClientCreatedTime(""+basic_data.get("clientCreatedTime"));
                pymts.setModifiedTime(""+basic_data.get("modifiedTime"));
                pymts.setResult(basic_data.optString("result"));
                pymts.setOrderId(basic_data.optString("orderId"));
                pymts.setEmployeeId(basic_data.optString("employeeId"));

                try {
                    if (!basic_data.isNull("cardTransaction")) {
                        JSONObject card_transc = basic_data.getJSONObject("cardTransaction");
                        CardTransaction crdTrans = new CardTransaction();
                        crdTrans.setCardType(card_transc.optString("cardType"));
                        crdTrans.setEntryType(card_transc.optString("entryType"));
                        crdTrans.setLast4(card_transc.optString("last4"));
                        crdTrans.setType(card_transc.optString("AUTH"));
                        crdTrans.setAuthCode(card_transc.optString("authCode"));
                        crdTrans.setReferenceId(card_transc.optString("referenceId"));
                        crdTrans.setState(card_transc.optString("state"));

                        pymts.setCardtranscation(crdTrans);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                try {
                    if (!basic_data.isNull("order")) {
                        JSONObject order_json  = basic_data.getJSONObject("order");
                        Order ord = new Order();
                        ord.setId(order_json.optString("id"));
                        ord.setCurrency(order_json.optString("currency"));
                        ord.setEmployeeId(order_json.optString("employeeId"));
                        ord.setTotal((Integer) order_json.get("total"));
                        ord.setTaxRemoved(order_json.getBoolean("taxRemoved"));
                        ord.setVat(order_json.getBoolean("isVat"));
                        ord.setGroupLineItems(order_json.getBoolean("groupLineItems"));
                        ord.setTestMode(order_json.getBoolean("testMode"));
                        ord.setState(order_json.optString("state"));
                        ord.setCreatedTime(""+ order_json.get("createdTime"));
                        ord.setClientCreatedTime(""+order_json.get("clientCreatedTime"));
                        ord.setModifiedTime(""+ order_json.get("modifiedTime"));
                        ord.setDeviceId( order_json.optString("deviceId"));

                        pymts.setOrders(ord);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                try {
                    if (!basic_data.isNull("tender") ) {
                        JSONObject tender_json = basic_data.getJSONObject("tender");
                        Tenders tender = new Tenders();
                        tender.setId(tender_json.optString("id"));
                        tender.setLabelKey(tender_json.optString("labelKey"));
                        tender.setLabel(tender_json.optString("label"));
                        tender.setEditable(tender_json.getBoolean("editable"));
                        tender.setOpensCashDrawer(tender_json.getBoolean("opensCashDrawer"));
                        tender.setEnabled(tender_json.getBoolean("enabled"));
                        tender.setVisible(tender_json.getBoolean("visible"));

                        pymts.setTenders(tender);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                OrdersList.add(pymts);
//                Log.v("Orders",""+OrdersList);
                if (OrdersList != null && OrdersList.size() > 0 ) {
                    OrderHistoryList = new ArrayList<>();
                    List<String> SessionsHistoryDates = new ArrayList<>();
                    for (int ix = 0; ix < OrdersList.size(); ix++) {
                         String date = getDateFromTimeStamp(OrdersList.get(ix).getCreatedTime());
                        if (!SessionsHistoryDates.contains(date)) {
                            OrderHistoryList.add(new SectionItem(date));
                            SessionsHistoryDates.add(date);
                        }
                        OrderHistoryList.add(OrdersList.get(ix));
                    }


                    OrdersAdapter oAdapter= new OrdersAdapter(getActivity(), OrderHistoryList);
                    ordersListview.setAdapter(oAdapter);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ordersListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ThridFragment tFragment = new ThridFragment();
                args.putSerializable("paymentObj", (Serializable) OrderHistoryList.get(position));
                tFragment.setArguments(args);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, tFragment)
                               .addToBackStack("ThridFragment").commit();



            }
        });
    }

    private String getDateFromTimeStamp(String data) {
        String date = "";
        try {
            String tStamp = new BigDecimal(data).toBigIntegerExact().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
            Date netDate = (new Date(Long.parseLong(tStamp)));
            date = sdf.format(netDate);
            Log.v("Date: ",""+sdf.format(netDate));

        } catch(NumberFormatException | ArithmeticException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("orders.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
