package com.example.fondos_de_pantalla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Altas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        //Declaracion de botones.
        Button regresar = (Button) findViewById(R.id.regresar);
        Button botoncito = (Button) findViewById(R.id.dardealta);

        //Metodo para dar de alta usuarios en un documento con su informacion de asesoria.
        botoncito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Conexion a la base de datos
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                //Declaracion de objetos de la vista
                TextView text = findViewById(R.id.texto);
                EditText texto1 = findViewById(R.id.boleta);
                EditText texto2 = findViewById(R.id.alumno);
                EditText texto3 = findViewById(R.id.materia);
                EditText texto4 = findViewById(R.id.asesor);

                //Obtener valores de las vista
                String n1 = texto1.getText().toString();
                String n2 = texto2.getText().toString();
                String n3 = texto3.getText().toString();
                String n4 = texto4.getText().toString();

                //Mapeamos los datos al documento, tanto el objeto como el valor
                        Map<String, Object> user = new HashMap<>();
                        user.put("boleta", n1);
                        user.put("nombre", n2);
                        user.put("materia", n3);
                        user.put("asesor", n4);

                //Se sube el usuario a la base de datos asignado a su boleta
                        db.collection("users").document(n1).set(user);

                //Se asigna los valores totales a un string para mostrarlos en la vista
                String total = "Se realizo el siguiente registro:" + "\n"
                        + "boleta:" + n1 + "\n"
                        + "nombre:" + n2 + "\n"
                        + "materia:" + n3 + "\n"
                        + "asesor:" +           n4;

                text.setText(total);

            }
        });


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentito = new Intent(Altas.this,Menu_CRUD.class);
                startActivity(intentito);
            }
        });



    }
}