package com.example.apiproduct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    ArrayList <ProductModel>list = new ArrayList<>();

    public ProductAdapter(Context context, ArrayList<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(list.get(position).title);
        holder.price.setText(list.get(position).price);
        holder.description.setText(list.get(position).description);
        String url = list.get(position).imageUrl;
        Picasso.get().load(url).into(holder.imageUrl);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),viewdetail.class);
                intent.putExtra("title",list.get(position).title);
                intent.putExtra("price",list.get(position).price);
                intent.putExtra("description",list.get(position).description);
                intent.putExtra("url",list.get(position).imageUrl);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,price,description;
        CardView view;
        ImageView imageUrl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);
            view = itemView.findViewById(R.id.cardview);
            imageUrl = itemView.findViewById(R.id.action_image);


        }
    }
}
