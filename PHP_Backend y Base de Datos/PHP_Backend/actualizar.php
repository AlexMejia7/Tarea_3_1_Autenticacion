<?php
header('Content-Type: application/json');
error_reporting(0); // Oculta warnings/notices
ob_start();

include "config.php";
include "jwt_helper.php";

// Obtener headers y token
$headers = getallheaders();
$auth = $headers['Authorization'] ?? ($headers['authorization'] ?? '');
$token = str_replace('Bearer ', '', $auth);

// Validar JWT con tu helper
$payload = JWT_Helper::validateToken($token);
if (!$payload) {
    ob_clean();
    echo json_encode(['success'=>false,'msg'=>'Token inválido']);
    exit;
}

// Obtener datos POST
$id = $_POST['id'] ?? '';
$nombre = $_POST['nombre'] ?? '';
$precio = $_POST['precio'] ?? '';
$cantidad = $_POST['cantidad'] ?? '';

if (!$id || !$nombre || !$precio || !$cantidad) {
    ob_clean();
    echo json_encode(['success'=>false,'msg'=>'Faltan datos']);
    exit;
}

// Preparar update con cantidad
$stmt = $conn->prepare("UPDATE productos SET nombre=?, precio=?, cantidad=? WHERE id=?");
$stmt->bind_param('sdii', $nombre, $precio, $cantidad, $id);

if ($stmt->execute()) {
    ob_clean();
    echo json_encode(['success'=>true,'msg'=>'Producto actualizado']);
} else {
    ob_clean();
    echo json_encode(['success'=>false,'msg'=>'Error al actualizar']);
}
?>
