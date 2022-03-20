package com.example.fondos_de_pantalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class Bajas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

            Button regresar = (Button) findViewById(R.id.regresar);
            Button eliminar = (Button) findViewById(R.id.eliminar);

            EditText boleta = findViewById(R.id.boleta);
            TextView resultado = findViewById(R.id.result);


            eliminar.setOnClickListener(new View.OnClickListener() {

                final String TAG = "MenuCrud";
                FirebaseFirestore db = FirebaseFirestore.getInstance();


                @Override
                public void onClick(View v) {

                    String nom = boleta.getText().toString();

                    db.collection("users").document(nom)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error deleting document", e);
                                }
                            });


                }
            });


            regresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intentito = new Intent(Bajas.this, Menu_CRUD.class);
                    startActivity(intentito);
                }
            });


        }
    }
