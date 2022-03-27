package com.example.fondos_de_pantalla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {


    Button boton_CRUD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        boton_CRUD = (Button) findViewById(R.id.crud);


        boton_CRUD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent1 = new Intent(Menu.this, AuthActivity.class);
              startActivity(intent1);

            }
        });

        Button boton_ahorcado = (Button) findViewById(R.id.Ahorcado);
        boton_ahorcado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Menu_CRUD.class);
                startActivity(intent);

            }
        });



    }
}