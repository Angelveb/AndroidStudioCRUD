package com.example.listainventario;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class Agregar extends AppCompatActivity {

    EditText producto_input, precio_input, cantidad_input;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        producto_input = findViewById(R.id.producto_input);
        precio_input = findViewById(R.id.precio_input);
        cantidad_input = findViewById(R.id.cantidad_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Agregar.this);
                myDB.agregar(producto_input.getText().toString().trim(),
                             precio_input.getText().toString().trim(),
                        Integer.parseInt((cantidad_input.getText().toString().trim())));
            }
        });
    }
}
