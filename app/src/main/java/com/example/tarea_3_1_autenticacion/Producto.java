package com.example.tarea_3_1_autenticacion;

public class Producto {
    public int id;
    public String nombre;
    public double precio;
    public int cantidad;

    // Constructor completo
    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Constructor vacío (opcional)
    public Producto() {
    }

    // Getters (opcional, pero útil)
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }

    // Setters (opcional)
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
