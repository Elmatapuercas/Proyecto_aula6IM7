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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AuthActivity extends AppCompatActivity{


    final String TAG = "MenuCrud";

    private String name = "";
    private String email = "";
    private String password = "";

    FirebaseAuth Mauth;


        @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.auth);


            EditText correo = (EditText)findViewById(R.id.correoelec);
            EditText contra = (EditText)findViewById(R.id.Password);
            EditText nom = (EditText)findViewById(R.id.Nombre);

            Button registro = findViewById(R.id.registro);
            Button log = findViewById(R.id.login);

            Mauth = FirebaseAuth.getInstance();



           registro.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   name = nom.getText().toString();
                   email = correo.getText().toString();
                   password = contra.getText().toString();

                   if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                       if(password.length() >= 6){

                           registerUser();

                       }else{
                           Toast.makeText(AuthActivity.this,"El password debe contener "
                                   +
                                   "como minimo 6 caracteres", Toast.LENGTH_SHORT).show();
                       }

                   }else{

                       Toast.makeText(AuthActivity.this,"Completa los campos pana"
                               ,Toast.LENGTH_SHORT).show();

                   }



               }
           });

           log.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   name = nom.getText().toString();
                   email = correo.getText().toString();
                   password = contra.getText().toString();


                   if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                       if(password.length() >= 6){

                           LogIn();

                       }else{
                           Toast.makeText(AuthActivity.this,"El password debe contener "
                                   +
                                   "como minimo 6 caracteres", Toast.LENGTH_SHORT).show();
                       }

                   }else{

                       Toast.makeText(AuthActivity.this,"Completa los campos pana"
                               ,Toast.LENGTH_SHORT).show();

                   }


               }
           });





        }



    private void registerUser(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener
                (new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            String id = Mauth.getCurrentUser().getUid();

                            Map<String,Object> usuario = new HashMap<>();
                            usuario.put("nombre",name);
                            usuario.put("email",email);
                            usuario.put("password",password);

                            db.collection("AuthUsers").document(id).set(usuario).
                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task2) {

                                            if(task2.isSuccessful()){


                                                Toast.makeText(AuthActivity.this,
                                                        "Te registramos en la base pana",
                                                        Toast.LENGTH_SHORT).show();

                                            }else{

                                                Toast.makeText(AuthActivity.this,
                                                        "No te pudimos registrar " +
                                                                "en la base de datos pana",
                                                        Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });



                        }else{
                            Toast.makeText(AuthActivity.this,
                                    "No se pudo realizar el registro pana",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    private void LogIn(){
        Mauth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = Mauth.getCurrentUser();
                            updateUI(user);
                            startActivity(new Intent(AuthActivity.this,Menu_CRUD.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
    }


}