package com.example.fondos_de_pantalla;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Altas extends AppCompatActivity {
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    //Conexion a la base de datos
    FirebaseFirestore db = FirebaseFirestore.getInstance();


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
        setContentView(R.layout.activity_altas);



        //Declaracion de objetos de la vista
        TextView text = findViewById(R.id.texto);
        EditText texto1 = findViewById(R.id.boleta);
        EditText texto2 = findViewById(R.id.alumno);
        EditText texto3 = findViewById(R.id.materia);
        EditText texto4 = findViewById(R.id.asesor);
        EditText texto5 = findViewById(R.id.fecha);



        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);




//Declaracion de botones.
        Button regresar = (Button) findViewById(R.id.regresar);
        Button botoncito = (Button) findViewById(R.id.dardealta);

        //Metodo para dar de alta usuarios en un documento con su informacion de asesoria.
        botoncito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtener valores de las vista
                String  n1 = texto1.getText().toString();
                String n2 = texto2.getText().toString();
                String n3 = texto3.getText().toString();
                String n4 = texto4.getText().toString();



                //Mapeamos los datos al documento, tanto el objeto como el valor
                Map<String, Object> user = new HashMap<>();
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



        texto5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Altas.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        String  n1 = texto1.getText().toString();
                        month = month + 1;
                        String datee = day + "/" + month + "/" + year;
                        texto5.setText(datee);

                        day = day + 1;

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

                Intent intentito = new Intent(Altas.this,Menu_CRUD.class);
                startActivity(intentito);
            }
        });



    }
}