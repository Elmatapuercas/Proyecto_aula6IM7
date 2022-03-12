package com.example.fondos_de_pantalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Altas extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);


        Button botoncito = (Button) findViewById(R.id.dardealta);

        botoncito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String TAG = "MenuCrud";
                FirebaseFirestore db = FirebaseFirestore.getInstance();


                TextView text = findViewById(R.id.texto);
                EditText texto1 = findViewById(R.id.boleta);
                EditText texto2 = findViewById(R.id.alumno);
                EditText texto3 = findViewById(R.id.materia);
                EditText texto4 = findViewById(R.id.asesor);

                String n1 = texto1.getText().toString();
                String n2 = texto2.getText().toString();
                String n3 = texto3.getText().toString();
                String n4 = texto4.getText().toString();



                        Map<String, Object> user = new HashMap<>();
                        user.put("boleta", n1);
                        user.put("nombre", n2);
                        user.put("materia", n3);
                        user.put("asesor", n4);

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



                String total = "Se realizo el siguiente registro:" + "\n"
                        + "boleta:" + n1 + "\n"
                        + "nombre:" + n2 + "\n"
                        + "materia:" + n3 + "\n"
                        + "asesor:" +           n4;

                text.setText(total);



            }
        });



    }
}