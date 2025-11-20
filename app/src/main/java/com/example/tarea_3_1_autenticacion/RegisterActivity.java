package com.example.tarea_3_1_autenticacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText txtUser, txtPass;
    Button btnRegister;
    TextView txtLoginLink;

    // URL de tu register.php
    private static final String URL_REGISTER = "http://192.168.0.5/PHP_Backend/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnRegister = findViewById(R.id.btnRegister);
        txtLoginLink = findViewById(R.id.txtLoginLink);

        // Registrar usuario
        btnRegister.setOnClickListener(v -> registrarUsuario());

        // Volver al login
        txtLoginLink.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        });
    }

    private void registrarUsuario() {
        String user = txtUser.getText().toString().trim();
        String pass = txtPass.getText().toString().trim();

        if(user.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest request = new StringRequest(Request.Method.POST, URL_REGISTER,
                response -> {
                    try {
                        JSONObject obj = new JSONObject(response);
                        Toast.makeText(this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                        if(obj.getBoolean("success")){
                            // Registro exitoso → volver al login
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Respuesta inválida", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Error en la red", Toast.LENGTH_SHORT).show();
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("usuario", user); // debe coincidir con register.php
                params.put("password", pass);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}
