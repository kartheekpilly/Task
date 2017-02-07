package com.example.kartheek_sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText FirstName, LastName, address, phone;
    private Button next;
    private View rootView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_first, container, false);
        FirstName= (EditText)rootView.findViewById(R.id.firstname);
        LastName= (EditText)rootView.findViewById(R.id.lastname);
        address=(EditText)rootView.findViewById(R.id.address);
        phone = (EditText)rootView.findViewById(R.id.phone);
        next = (Button)rootView.findViewById(R.id.next);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragment sfragment = new SecondFragment();
                Bundle mBundle = new Bundle();
                mBundle.putString("name", FirstName.getText().toString());
                mBundle.putString("last", LastName.getText().toString());
                mBundle.putString("phone", phone.getText().toString());
                mBundle.putString("address", address.getText().toString());
                sfragment.setArguments(mBundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, sfragment).addToBackStack("SecondFragment").commit();

            }
        });
        return rootView;
    }


}
