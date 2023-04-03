package com.example.apiproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class viewdetail extends AppCompatActivity {
    String title,price,description,imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        price = intent.getStringExtra("price");
        description = intent.getStringExtra("description");
        imageurl = intent.getStringExtra("url");

        TextView titlex = findViewById(R.id.dusra_title);
        TextView descx = findViewById(R.id.teesra_desc);
        ImageView ingx =findViewById(R.id.img);

        titlex.setText(title);
        descx.setText(description);

        Picasso.get().load(imageurl).into(ingx);

    }
}