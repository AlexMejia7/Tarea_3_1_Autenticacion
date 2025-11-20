<?php
header('Content-Type: application/json');
error_reporting(0); // Evitar avisos que rompan JSON

include "config.php";
include "jwt_helper.php";

// Obtener token de headers
$headers = getallheaders();
$auth = $headers['Authorization'] ?? ($headers['authorization'] ?? '');
$token = str_replace('Bearer ', '', $auth);

// Verificar token usando la clase JWT_Helper
$payload = JWT_Helper::validateToken($token);
if (!$payload) {
    echo json_encode(['success' => false, 'msg' => 'Token inválido']);
    exit;
}

// Recibir datos POST
$nombre = $_POST['nombre'] ?? '';
$precio = $_POST['precio'] ?? '';

if (!$nombre || !$precio) {
    echo json_encode(['success' => false, 'msg' => 'Faltan datos']);
    exit;
}

// Convertir precio a float seguro
$precio = floatval($precio);

// Preparar insert
$stmt = $conn->prepare("INSERT INTO productos (nombre, precio) VALUES (?, ?)");
$stmt->bind_param('sd', $nombre, $precio);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    echo json_encode(['success' => false, 'msg' => 'Error al insertar']);
}

$stmt->close();
$conn->close();
?>
