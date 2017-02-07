package com.example.kartheek_sample;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Details_Views extends AppCompatActivity
{
    private ImageView image;
    private TextView text_view, desc_view, footer_view;
    Bundle b;
    private Button btn;
    String img,text,descc,footer,txt,web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);

        b = getIntent().getExtras();

        image = (ImageView)findViewById(R.id.imageView);
        text_view = (TextView)findViewById(R.id.title_view);
        desc_view = (TextView)findViewById(R.id.desc_view);

        btn = (Button)findViewById(R.id.target);

        //declare string to getIntent
        img = b.getString("Images2");
        text = b.getString("Title2");
        descc= b.getString("Desc2");
        txt = b.getString("Text2");
        web = b.getString("Target2");

        //setting the views
        text_view.setText(text);
        desc_view.setText(descc);
        btn.setText(txt);
        Glide.with(getApplicationContext()).load(img).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse(web);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
    }
}
