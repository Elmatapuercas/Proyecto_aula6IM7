package com.example.fondos_de_pantalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Consultas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);


        Button consultas = (Button) findViewById(R.id.consultar);

        consultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String TAG = "MenuCrud";
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                TextView consulta = findViewById(R.id.consulta);
                EditText boletita = findViewById(R.id.boleta);

                String consultota = boletita.getText().toString();

                DocumentReference docRef = db.collection("users").document(consultota);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {

                                String nombre = document.getString("nombre");
                                String asesoria = document.getString("asesor");
                                String materia = document.getString("materia");

                                String resultado = nombre + "\n" + asesoria + "\n" + materia;

                                consulta.setText(resultado);

                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });


            }
        });







    }
}