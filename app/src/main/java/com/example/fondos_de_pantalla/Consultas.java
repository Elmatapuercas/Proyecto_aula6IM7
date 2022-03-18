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


        //Declaracion de botones
        Button regresar = (Button) findViewById(R.id.regresar);
        Button consultas = (Button) findViewById(R.id.consultar);


        //Metodo para obtener datos de una boleta en especifico.
        consultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Conectar base de datos.
                final String TAG = "MenuCrud";
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                //Grafico
                TextView consulta = findViewById(R.id.consulta);
                EditText nombre = findViewById(R.id.nombre);

                //Obtener datos del EditText.
                String consultota = nombre.getText().toString();

                //Referencia el documento al que se quiere acceder.
                DocumentReference docRef = db.collection("users").document(consultota);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                    //metodo para obtener datos y establecerlos en la vista.
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();

                    /* Si el documento existe, obtendra los datos del documento asociado a la boleta
                    ingresada */

                            if (document.exists()) {
                                //Se obtienen los strings del documento.
                                Long boleta = document.getLong("boleta");
                                String asesoria = document.getString("asesor");
                                String materia = document.getString("materia");

                                //se coloca la informacion dentro del textview.
                                String resultado = boleta + "\n" + asesoria + "\n" + materia;
                                consulta.setText(resultado);

                        // En caso de que el documento no exista.
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


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentito = new Intent(Consultas.this,Menu_CRUD.class);
                startActivity(intentito);
            }
        });




    }
}