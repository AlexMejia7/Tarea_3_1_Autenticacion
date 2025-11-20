package com.example.tarea_3_1_autenticacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnCerrarSesion, btnAgregar, btnListar, btnActualizar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Inicializar botones
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnListar = findViewById(R.id.btnListar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        // Configurar listeners
        btnCerrarSesion.setOnClickListener(v -> {
            // Limpiar token
            MainActivity.TOKEN = "";
            getSharedPreferences("MY_APP", MODE_PRIVATE)
                    .edit()
                    .remove("token")
                    .apply();

            // Volver al login y limpiar actividades anteriores
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        btnAgregar.setOnClickListener(v -> startActivity(new Intent(this, AgregarActivity.class)));
        btnListar.setOnClickListener(v -> startActivity(new Intent(this, ListarActivity.class)));
        btnActualizar.setOnClickListener(v -> startActivity(new Intent(this, ActualizarActivity.class)));
        btnEliminar.setOnClickListener(v -> startActivity(new Intent(this, EliminarActivity.class)));
    }
}
