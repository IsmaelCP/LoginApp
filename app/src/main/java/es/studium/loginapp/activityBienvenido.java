package es.studium.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class activityBienvenido extends AppCompatActivity implements View.OnClickListener{
    Button btnBorrar;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Usuario = "nombreKey";
    public static final String Clave = "claveKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnBorrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        // Si pulsamos el botón Borrar se borran los datos guardados en la SharedPreferences y volvemos a la activity principal

            // Abrimos el editor de SharedPreferences y eliminamos el usuario y la clave
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(Usuario);
            editor.remove(Clave);
            editor.apply();
            // Mostramos un Toast para informar que los datos se han borrado
            Toast.makeText(activityBienvenido.this, "Los datos han sido eliminados de la aplicación", Toast.LENGTH_LONG).show();
            // Mostramos la pantalla de Login
            Intent intent = new Intent(activityBienvenido.this, MainActivity.class);
            startActivity(intent);
    }
}