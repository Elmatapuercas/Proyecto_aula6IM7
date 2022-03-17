package com.example.fondos_de_pantalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Menu_CRUD extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_crud);


        Button altas = (Button) findViewById(R.id.Altas);
        altas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu_CRUD.this, Altas.class);
                startActivity(intent);

            }


/*
        final String TAG = "MenuCrud";
        FirebaseFirestore db = FirebaseFirestore.getInstance();





            Button altas = (Button) findViewById(R.id.Altas);
            altas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Map<String, Object> user = new HashMap<>();
                    user.put("first", "Ada");
                    user.put("last", "Lovelace");
                    user.put("born", "1815");

                    db.collection("users").add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "Documento anadido: " + documentReference.getId());

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error al anadir", e);

                                }
                            });

                }
            });
            */


        });

        Button consultas = (Button) findViewById(R.id.Consultas);
        consultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(Menu_CRUD.this, Consultas.class);
                startActivity(intent2);


            }
        });


        Button cambios = (Button) findViewById(R.id.Cambios);
        cambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 = new Intent(Menu_CRUD.this, Cambios.class);
                startActivity(intent3);

            }
        });


    }
}