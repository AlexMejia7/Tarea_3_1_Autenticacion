package com.example.tarea_3_1_autenticacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AgregarActivity extends AppCompatActivity {

    EditText edtNombre, edtPrecio;
    Button btnGuardar, btnVolver;

    // URL del backend
    private static final String URL_AGREGAR = "http://192.168.0.5/PHP_Backend/agregar.php";

    @Override
    protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_agregar);

        edtNombre = findViewById(R.id.edtNombre);
        edtPrecio = findViewById(R.id.edtPrecio);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVolver = findViewById(R.id.btnVolver);

        // Guardar producto
        btnGuardar.setOnClickListener(v -> guardar());

        // Volver al menú
        btnVolver.setOnClickListener(v -> {
            startActivity(new Intent(this, MenuActivity.class));
            finish();
        });
    }

    private void guardar(){

        StringRequest req = new StringRequest(Request.Method.POST, URL_AGREGAR,
                response -> {
                    Toast.makeText(this, "Respuesta: " + response, Toast.LENGTH_SHORT).show();
                    finish();
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Error de red", Toast.LENGTH_SHORT).show();
                }
        ) {
            @Override
            public Map<String,String> getHeaders(){
                Map<String,String> h = new HashMap<>();
                h.put("Authorization", "Bearer " + MainActivity.TOKEN);
                return h;
            }

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> p = new HashMap<>();
                p.put("nombre", edtNombre.getText().toString());
                p.put("precio", edtPrecio.getText().toString());
                return p;
            }
        };

        Volley.newRequestQueue(this).add(req);
    }
}
