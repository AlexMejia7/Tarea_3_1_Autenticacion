<?php
// Configuración de conexión a MySQL y clave secreta JWT
header('Content-Type: application/json');
error_reporting(0); // Evita que se mezclen warnings con JSON

$host = "localhost";
$user = "root";
$pass = ""; 
$db = "tarea_3_1_autenticacion";

$conn = new mysqli($host, $user, $pass, $db);

if ($conn->connect_error) {
    echo json_encode(['success' => false, 'msg' => 'Error de conexión a la base de datos']);
    exit;
}

// Clave secreta para firmar JWT
$key = "GRUPO4";
?>
