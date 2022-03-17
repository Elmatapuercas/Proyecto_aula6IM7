package com.example.fondos_de_pantalla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cambios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);


        Button regresar = (Button) findViewById(R.id.regresar);
        Button cambiar = (Button) findViewById(R.id.Cambios);

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseFirestore db = FirebaseFirestore.getInstance();

                TextView confirm = findViewById(R.id.texto);
                EditText texto1 = findViewById(R.id.boleta);
                EditText texto2 = findViewById(R.id.alumno);
                EditText texto3 = findViewById(R.id.materia);
                EditText texto4 = findViewById(R.id.asesor);



                String n1 = texto1.getText().toString();
                String n2 = texto2.getText().toString();
                String n3 = texto3.getText().toString();
                String n4 = texto4.getText().toString();

                Map<String,Object> user = new HashMap<>();
                user.put("boleta", n1);
                user.put("nombre", n2);
                user.put("materia", n3);
                user.put("asesor", n4);


                db.collection("users").document(n1).set(user);

                confirm.setText("se hizo");







            }
        });



        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentito = new Intent(Cambios.this,Menu_CRUD.class);
                startActivity(intentito);
            }
        });


    }
}