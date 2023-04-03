package com.example.apiproduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList <ProductModel> list = new ArrayList<>();
    String url = "https://dummyjson.com/products/search?q=Laptop";
    ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         recyclerView = findViewById(R.id.productrecyclerview);




         getData();
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jar = response.getJSONArray("products");
                    for (int i = 0 ; i< jar.length(); i++){
                        JSONObject jack = jar.getJSONObject(i);

                        String title = jack.getString("title");
                        String price = jack.getString("price");
                        String desc = jack.getString("description");
                        String img = jack.getString("thumbnail");

                        ProductModel productModel = new ProductModel(title, price, desc, img);

                        list.add(productModel);

                    }
                    adapter = new ProductAdapter(MainActivity.this,list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jor);
    }
}