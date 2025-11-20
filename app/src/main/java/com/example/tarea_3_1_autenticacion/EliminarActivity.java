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

public class EliminarActivity extends AppCompatActivity {

    EditText edtIdDel;
    Button btnEliminar, btnVolver;

    // URL del backend
    private static final String URL_ELIMINAR = "http://192.168.0.5/PHP_Backend/eliminar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        edtIdDel = findViewById(R.id.edtIdDel);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnVolver = findViewById(R.id.btnVolver);

        // Acción eliminar
        btnEliminar.setOnClickListener(v -> eliminar());

        // Volver al menú
        btnVolver.setOnClickListener(v -> {
            startActivity(new Intent(this, MenuActivity.class));
            finish();
        });
    }

    private void eliminar() {
        StringRequest req = new StringRequest(Request.Method.POST, URL_ELIMINAR,
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
            public Map<String, String> getHeaders() {
                Map<String, String> h = new HashMap<>();
                h.put("Authorization", "Bearer " + MainActivity.TOKEN);
                return h;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> p = new HashMap<>();
                p.put("id", edtIdDel.getText().toString());
                return p;
            }
        };

        Volley.newRequestQueue(this).add(req);
    }
}
