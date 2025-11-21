<?php
header('Content-Type: application/json');
error_reporting(0);

include "config.php";
include "jwt_helper.php";

// ---------------------------
// 1️⃣ Verificar token
// ---------------------------
$headers = getallheaders();
$auth = $headers['Authorization'] ?? ($headers['authorization'] ?? '');
$token = str_replace('Bearer ', '', $auth);

$payload = JWT_Helper::validateToken($token);
if (!$payload) {
    echo json_encode(['success' => false, 'msg' => 'Token inválido']);
    exit;
}

// ---------------------------
// 2️⃣ Recibir datos POST
// ---------------------------
$nombre   = $_POST['nombre'] ?? '';
$precio   = $_POST['precio'] ?? '';
$cantidad = $_POST['cantidad'] ?? '';

if (!$nombre || !$precio || $cantidad === '') {
    echo json_encode(['success' => false, 'msg' => 'Faltan datos']);
    exit;
}

// Convertir tipos
$precio   = floatval($precio);
$cantidad = intval($cantidad);

// ---------------------------
// 3️⃣ Insertar en DB
// ---------------------------
$stmt = $conn->prepare("INSERT INTO productos (nombre, precio, cantidad) VALUES (?, ?, ?)");
$stmt->bind_param('sdi', $nombre, $precio, $cantidad);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    echo json_encode(['success' => false, 'msg' => 'Error al insertar']);
}

$stmt->close();
$conn->close();
?>
