package com.example.kartheek_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by intone on 15-Oct-16.
 */

public class ThirdScreen extends AppCompatActivity
{

    TextView first, last, pho, adress;
    String firstname, lastname, phoneno, address;
    Bundle b ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thirdscreen);


        b = getIntent().getExtras();

        first= (TextView)findViewById(R.id.first);
        last = (TextView)findViewById(R.id.last);
        pho=(TextView)findViewById(R.id.phone);
        adress = (TextView)findViewById(R.id.address);

         firstname = b.getString("name");
         lastname = b.getString("last");
         phoneno = b.getString("phone");
         address = b.getString("address");

        first.setText(firstname);
        last.setText(lastname);
        pho.setText(phoneno);
        adress.setText(address);
    }



}
