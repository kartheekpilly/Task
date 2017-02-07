package com.example.kartheek_sample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    protected EditText FirstName, LastName;
    Button next;
    protected ProgressDialog mProgressDialog;

   /* ArrayList<String> images=new ArrayList<String>();
    ArrayList<String> titles=new ArrayList<String>();
    ArrayList<String> desc=new ArrayList<String>();*/

    // URL to get contacts JSON
    private static String url = "https://api-int.payeezy.com/v1/clovergo/refdata";

    String orders, payments;

    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    AlertDialogManager alert = new AlertDialogManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cd = new ConnectionDetector(getApplicationContext());
        // Check if Internet present
        isInternetPresent = cd.isConnectingToInternet();

        if (!isInternetPresent) {
            // Internet Connection is not present
            alert.showAlertDialog(MainActivity.this, "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }
        FirstFragment currentFragment = new FirstFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, currentFragment).commit();

    }

}

