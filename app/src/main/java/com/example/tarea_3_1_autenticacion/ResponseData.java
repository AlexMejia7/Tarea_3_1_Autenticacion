package com.example.tarea_3_1_autenticacion;

public class ResponseData {
    public boolean success;
    public String message;
    public Integer id;

    // Constructor vacío
    public ResponseData() {}

    // Constructor opcional
    public ResponseData(boolean success, String message, Integer id) {
        this.success = success;
        this.message = message;
        this.id = id;
        this.id = id;
    }

    // Getters (opcional)
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Integer getId() { return id; }

    // Setters (opcional)
    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
    public void setId(Integer id) { this.id = id; }
}
