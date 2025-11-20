package com.example.tarea_3_1_autenticacion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {

    EditText txtUser, txtPass;
    Button btnLogin;
    TextView txtRegisterLink;
    public static String TOKEN = "";

    // URL completa de tu backend
    private static final String URL_LOGIN = "http://192.168.0.5/PHP_Backend/login.php";
    // Si quieres probar primero el test_post.php:
    // private static final String URL_LOGIN = "http://192.168.0.5/PHP_Backend/test_post.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegisterLink = findViewById(R.id.txtRegisterLink);

        // Ocultar contraseña con asteriscos
        txtPass.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);

        // Acción del botón Login
        btnLogin.setOnClickListener(v -> login());

        // Acción del enlace a registro
        txtRegisterLink.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void login() {
        StringRequest request = new StringRequest(Request.Method.POST, URL_LOGIN,
                response -> {
                    try {
                        JSONObject obj = new JSONObject(response);

                        if (obj.getBoolean("success")) {
                            TOKEN = obj.optString("token", "");
                            if (!TOKEN.isEmpty()) {
                                startActivity(new Intent(this, MenuActivity.class));
                                finish();
                            } else {
                                Toast.makeText(this, "Token no generado", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this,
                                    obj.optString("msg", "Credenciales inválidas"),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Respuesta inválida", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Log.e("VolleyError", error.toString());
                    Toast.makeText(this, "Error en la red", Toast.LENGTH_SHORT).show();
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> p = new HashMap<>();
                String user = txtUser.getText().toString().trim();
                String pass = txtPass.getText().toString().trim();

                // Log para depuración de los datos que se envían
                Log.d("POST_PARAMS", "usuario=" + user + ", password=" + pass);

                p.put("usuario", user);
                p.put("password", pass);
                return p;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}
