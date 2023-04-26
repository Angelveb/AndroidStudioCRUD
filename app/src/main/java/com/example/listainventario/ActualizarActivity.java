package com.example.listainventario;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActualizarActivity extends AppCompatActivity {

    EditText producto_input, precio_input, cantidad_input;
    Button update_button, delete_button;

    String id, producto, precio, cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        producto_input = findViewById(R.id.producto_input);
        precio_input = findViewById(R.id.precio_input2);
        cantidad_input = findViewById(R.id.cantidad_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar producto after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(producto);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(ActualizarActivity.this);
                producto = producto_input.getText().toString().trim();
                precio = precio_input.getText().toString().trim();
                cantidad = cantidad_input.getText().toString().trim();
                myDB.actualizar(id, producto, precio, cantidad);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoConfirmacion();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("producto") &&
                getIntent().hasExtra("precio") && getIntent().hasExtra("cantidad")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            producto = getIntent().getStringExtra("producto");
            precio = getIntent().getStringExtra("precio");
            cantidad = getIntent().getStringExtra("cantidad");

            //Setting Intent Data
            producto_input.setText(producto);
            precio_input.setText(precio);
            cantidad_input.setText(cantidad);
            Log.d("stev", producto +" "+ precio +" "+ cantidad);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void dialogoConfirmacion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar " + producto + " ?");
        builder.setMessage("¿Está seguro de que quiere eliminar " + producto + " ?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(ActualizarActivity.this);
                myDB.eliminarfila(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
