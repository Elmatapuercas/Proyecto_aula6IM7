package com.example.fondos_de_pantalla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Carga extends AppCompatActivity {

    /*Todos los derechos reservados para Chávez López Diego, Aceves Serrano Victor Ghalieb,
    Gonzalez Perez Itzel y Pereda Ramirez Yael del código fuente para la aplicación.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carga);

        //Duracion de la animacion
        final int duracion = 5000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //codigo que se ejecutara
                Intent intent =new Intent(Carga.this, Menu.class);
                startActivity(intent);
                finish();

            }
        },duracion);

    }
}