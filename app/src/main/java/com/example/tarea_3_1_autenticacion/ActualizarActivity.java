package com.example.tarea_3_1_autenticacion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActualizarActivity extends AppCompatActivity {

    EditText edtId, edtNombreA, edtPrecioA, edtCantidadA;
    Button btnAct, btnRegresar;
    Spinner spinnerProductos;

    private static final String URL_LISTAR = "http://192.168.0.5/PHP_Backend/listar.php";
    private static final String URL_ACTUALIZAR = "http://192.168.0.5/PHP_Backend/actualizar.php";

    ArrayList<String> listaIds = new ArrayList<>();
    ArrayList<JSONObject> listaProductos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        edtId = findViewById(R.id.edtId);
        edtNombreA = findViewById(R.id.edtNombreA);
        edtPrecioA = findViewById(R.id.edtPrecioA);
        edtCantidadA = findViewById(R.id.edtCantidadA);
        btnAct = findViewById(R.id.btnActualizar);
        btnRegresar = findViewById(R.id.btnRegresar);
        spinnerProductos = findViewById(R.id.spinnerProductos); // CORREGIDO

        btnAct.setOnClickListener(v -> actualizar());
        btnRegresar.setOnClickListener(v -> finish());

        listarProductos();
    }

    private void listarProductos() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_LISTAR, null,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            JSONArray productos = response.getJSONArray("productos");
                            listaIds.clear();
                            listaProductos.clear();
                            for (int i = 0; i < productos.length(); i++) {
                                JSONObject obj = productos.getJSONObject(i);
                                listaIds.add(obj.getString("id") + " - " + obj.getString("nombre"));
                                listaProductos.add(obj);
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                                    android.R.layout.simple_spinner_item, listaIds);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerProductos.setAdapter(adapter);

                            spinnerProductos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    JSONObject producto = listaProductos.get(position);
                                    edtId.setText(producto.optString("id"));
                                    edtNombreA.setText(producto.optString("nombre"));
                                    edtPrecioA.setText(producto.optString("precio"));
                                    edtCantidadA.setText(producto.optString("cantidad"));
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) { }
                            });

                        } else {
                            Toast.makeText(this, "Error al listar: " + response.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(this, "Error de red al listar", Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> h = new HashMap<>();
                h.put("Authorization", "Bearer " + MainActivity.TOKEN);
                return h;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }

    private void actualizar() {
        StringRequest req = new StringRequest(Request.Method.POST, URL_ACTUALIZAR,
                response -> Toast.makeText(this, "Respuesta: " + response, Toast.LENGTH_SHORT).show(),
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Error de red al actualizar", Toast.LENGTH_SHORT).show();
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
                p.put("cantidad", edtCantidadA.getText().toString());
                return p;
            }
        };

        Volley.newRequestQueue(this).add(req);
    }
}
