package com.example.fondos_de_pantalla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Altas extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);


        Button botoncito = (Button) findViewById(R.id.dardealta);

        botoncito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView text = findViewById(R.id.texto);
                EditText texto1 = findViewById(R.id.boleta);
                EditText texto2 = findViewById(R.id.alumno);
                EditText texto3 = findViewById(R.id.materia);
                EditText texto4 = findViewById(R.id.asesor);

                String n1 = texto1.getText().toString();
                String n2 = texto2.getText().toString();
                String n3 = texto3.getText().toString();
                String n4 = texto4.getText().toString();

                String total = "tu boleta:" + n1 + "tu nombre:" + n2 + "se registro con la materia:" + n3 + "y con el asesor:" +n4;

                text.setText(total);



            }
        });



    }
}