package es.studium.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtUsuario, txtClave;
    Switch switchGuardar;
    Button btnAcceder;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Usuario = "nombreKey";
    public static final String Clave = "claveKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Icono en el action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtClave = findViewById(R.id.txtClave);
        switchGuardar = findViewById(R.id.switchGuardar);
        btnAcceder = findViewById(R.id.btnAcceder);

        // Comprobamos si hay información en las SharedPreferences
        // Si hay info accedemos directamente a la activity Bienvenido
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String isShared = sharedPreferences.getString(Usuario, "");
        if (isShared != "") {
            Intent actBienvenido = new Intent(MainActivity.this, activityBienvenido.class);
            startActivity(actBienvenido);
        }

        btnAcceder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Si introduce usuario y clave abrimos el editor de SharedPreferences
                if(!txtUsuario.getText().toString().equals("") && !txtClave.getText().toString().equals("")){
                    String name = txtUsuario.getText().toString();
                    String pass = txtClave.getText().toString();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    // usuario y clave de prueba
                    String nombre = "ismael";
                    String dni = "28752771B";

                    // Si los datos coinciden con el nombre: ismael y la clave: 28752771B
                    if(name.equals(nombre) && pass.equals(dni)){
                        // Si pulsa en el switch para guardar los datos se guardan el usuario y la clave en la SharedPreferences
                        if(switchGuardar.isChecked()){
                            editor.putString(Usuario, name);
                            editor.putString(Clave, dni);
                            editor.commit();

                            // Pulsamos el botón Acceder y mostramos el activity Bienvenido
                            if(v.getId() == (R.id.btnAcceder)){
                                Intent intent = new Intent(MainActivity.this, activityBienvenido.class);
                                startActivity(intent);
                            }
                        }
                        // Si NO pulsa el switch para guardar los datos y pulsa el botón Acceder, abrimos mostramos el activity Bienvenido
                        // pero no se guardaran los datos en la SharedPreferences
                        else{
                            if(v.getId() == (R.id.btnAcceder)){
                                Intent intent = new Intent(MainActivity.this, activityBienvenido.class);
                                startActivity(intent);
                            }
                        }
                    }
                    // Si los datos NO coinciden con el nombre: ismael y la clave: 28752771B al pulsar el botón Acceder
                    // Mostramos un Toast indicando que las credenciales no son correctas
                    else{
                        Toast.makeText(MainActivity.this, "El usuario y/o la clave no son correctas", Toast.LENGTH_LONG).show();
                    }
                }
                // Si no introduce usuario y clave y pulsa el botón Acceder mostramos un Toast indicando que rellene los campos
                else{
                    Toast.makeText(MainActivity.this, "Introduzca los datos Usuario y Clave", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}