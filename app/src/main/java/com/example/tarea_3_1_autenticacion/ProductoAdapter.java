package com.example.tarea_3_1_autenticacion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<Producto> items;

    public ProductoAdapter(List<Producto> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Producto p = items.get(position);

        holder.txtId.setText(String.valueOf(p.getId()));
        holder.txtNombre.setText(p.getNombre());
        holder.txtPrecio.setText(String.valueOf(p.getPrecio()));
        holder.txtCantidad.setText("Cantidad: " + p.getCantidad());

        // Opcional: si quieres manejar clics en botones
        // holder.btnEditar.setOnClickListener(v -> { /* acción editar */ });
        // holder.btnEliminar.setOnClickListener(v -> { /* acción eliminar */ });
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtId, txtNombre, txtPrecio, txtCantidad;
        ImageView imgProducto;
        ImageButton btnEditar, btnEliminar;

        public ViewHolder(View v){
            super(v);

            txtId = v.findViewById(R.id.itemId);
            txtNombre = v.findViewById(R.id.itemNombre);
            txtPrecio = v.findViewById(R.id.itemPrecio);
            txtCantidad = v.findViewById(R.id.itemCantidad);

            imgProducto = v.findViewById(R.id.imgProducto);
            btnEditar = v.findViewById(R.id.btnEditar);
            btnEliminar = v.findViewById(R.id.btnEliminar);
        }
    }
}
