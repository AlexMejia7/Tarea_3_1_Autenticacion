package com.example.tarea_3_1_autenticacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListarActivity extends AppCompatActivity {

    RecyclerView rv;
    ProductoAdapter adapter;
    ArrayList<Producto> lista = new ArrayList<>();
    Button btnVolver;

    private static final String URL_LISTAR = "http://192.168.0.5/PHP_Backend/listar.php";

    @Override
    protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_listar);

        rv = findViewById(R.id.recyclerProductos);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductoAdapter(lista);
        rv.setAdapter(adapter);

        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(v -> {
            startActivity(new Intent(this, MenuActivity.class));
            finish();
        });

        cargar();
    }

    private void cargar(){
        StringRequest req = new StringRequest(Request.Method.POST, URL_LISTAR,
                response -> {
                    try {
                        lista.clear();

                        JSONObject obj = new JSONObject(response);
                        boolean success = obj.getBoolean("success");

                        if(success){
                            JSONArray arr = obj.getJSONArray("productos");

                            for (int i = 0; i < arr.length(); i++){
                                JSONObject o = arr.getJSONObject(i);
                                int id = o.getInt("id");
                                String nombre = o.getString("nombre");
                                double precio = o.getDouble("precio");
                                int cantidad = o.has("cantidad") ? o.getInt("cantidad") : 0;
                                lista.add(new Producto(id, nombre, precio, cantidad));
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(this, "No hay productos", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Error parseo", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Error red", Toast.LENGTH_SHORT).show();
                }
        ){
            @Override
            public Map<String,String> getHeaders(){
                Map<String,String> h = new HashMap<>();
                h.put("Authorization", "Bearer " + MainActivity.TOKEN);
                return h;
            }
        };

        Volley.newRequestQueue(this).add(req);
    }
}
