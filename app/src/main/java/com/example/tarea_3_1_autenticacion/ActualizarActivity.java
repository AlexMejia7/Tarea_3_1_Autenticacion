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

public class ActualizarActivity extends AppCompatActivity {

    EditText edtId, edtNombreA, edtPrecioA, edtCantidadA;
    Button btnAct, btnRegresar;

    private static final String URL_ACTUALIZAR ="http://192.168.0.5/PHP_Backend/actualizar.php";

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_actualizar);

        edtId = findViewById(R.id.edtId);
        edtNombreA = findViewById(R.id.edtNombreA);
        edtPrecioA = findViewById(R.id.edtPrecioA);
        edtCantidadA = findViewById(R.id.edtCantidadA); // Nuevo campo
        btnAct = findViewById(R.id.btnActualizar);
        btnRegresar = findViewById(R.id.btnRegresar);

        btnAct.setOnClickListener(v -> actualizar());
        btnRegresar.setOnClickListener(v -> {
            startActivity(new Intent(this, MenuActivity.class));
            finish();
        });
    }

    private void actualizar() {
        StringRequest req = new StringRequest(Request.Method.POST, URL_ACTUALIZAR,
                response -> Toast.makeText(this, "Resp: " + response, Toast.LENGTH_SHORT).show(),
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Error de red", Toast.LENGTH_SHORT).show();
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> h = new HashMap<>();
                h.put("Authorization", "Bearer " + MainActivity.TOKEN);
                return h;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> p = new HashMap<>();
                p.put("id", edtId.getText().toString());
                p.put("nombre", edtNombreA.getText().toString());
                p.put("precio", edtPrecioA.getText().toString());
                p.put("cantidad", edtCantidadA.getText().toString()); // Nuevo parámetro
                return p;
            }
        };

        Volley.newRequestQueue(this).add(req);
    }
}
