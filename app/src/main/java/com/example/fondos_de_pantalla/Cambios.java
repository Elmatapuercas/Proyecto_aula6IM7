package com.example.fondos_de_pantalla;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Cambios extends AppCompatActivity {



    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static Date StringADate(String date){

        Date fecha = null;
        try {

            fecha = sdf.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();

        }
        return fecha;

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

//Conexion a base de datos
        FirebaseFirestore db = FirebaseFirestore.getInstance();


//Declaracion de graficos
        TextView confirm = findViewById(R.id.texto);
        EditText texto1 = findViewById(R.id.boleta);
        EditText texto2 = findViewById(R.id.alumno);
        EditText texto3 = findViewById(R.id.materia);
        EditText texto4 = findViewById(R.id.asesor);
        EditText texto5 = findViewById(R.id.fecha);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        Button regresar = (Button) findViewById(R.id.regresar);
        Button cambiar = (Button) findViewById(R.id.Cambios);

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n1 = texto1.getText().toString();
                String n2 = texto2.getText().toString();
                String n3 = texto3.getText().toString();
                String n4 = texto4.getText().toString();



                DocumentReference doc = db.collection("users").document(n1);

                doc.update("nombre",n2);
                doc.update("materia",n3);
                doc.update("asesor",n4);

                confirm.setText("se hizo");


            }
        });



        texto5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Cambios.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        String  n1 = texto1.getText().toString();
                        month = month + 1;
                        String datee = day + "/" + month + "/" + year;
                        texto5.setText(datee);

                        String date = day + "/" + month + "/" + year;

                        Date fechacon = StringADate(date);

                        DocumentReference doc = db.collection("users").document(n1);

                        doc.update("fecha",fechacon);


                    }
                },year,month,day);
                datePickerDialog.show();


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