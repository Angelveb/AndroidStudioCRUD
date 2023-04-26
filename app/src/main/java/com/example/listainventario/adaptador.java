package com.example.listainventario;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adaptador extends RecyclerView.Adapter<adaptador.MyViewHolder> {

    private final Context context;
    private final Activity activity;
    private final ArrayList id;
    private final ArrayList producto;
    private final ArrayList precio;
    private final ArrayList cantidad;

    adaptador(Activity activity, Context context, ArrayList id, ArrayList producto, ArrayList precio,
              ArrayList cantidad){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.producto_txt.setText(String.valueOf(producto.get(position)));
        holder.precio_txt.setText(String.valueOf(precio.get(position)));
        holder.cantidad_txt.setText(String.valueOf(cantidad.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActualizarActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("producto", String.valueOf(producto.get(position)));
                intent.putExtra("precio", String.valueOf(precio.get(position)));
                intent.putExtra("cantidad", String.valueOf(cantidad.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, producto_txt, precio_txt, cantidad_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.producto_id_txt);
            producto_txt = itemView.findViewById(R.id.producto_producto_txt);
            precio_txt = itemView.findViewById(R.id.producto_precio_txt);
            cantidad_txt = itemView.findViewById(R.id.producto_cantidad_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
