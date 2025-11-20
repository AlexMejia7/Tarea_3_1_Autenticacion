<?php
header('Content-Type: application/json');
include "config.php";
include "jwt_helper.php";

// Obtener headers
$headers = getallheaders();
$auth = $headers['Authorization'] ?? ($headers['authorization'] ?? '');
$token = str_replace('Bearer ', '', $auth);

// Validar token con tu clase moderna
$payload = JWT_Helper::validateToken($token);
if (!$payload) {
    echo json_encode(['success'=>false,'msg'=>'Token inválido']);
    exit;
}

// Obtener ID
$id = $_POST['id'] ?? '';
if (!$id) {
    echo json_encode(['success'=>false,'msg'=>'Falta el ID']);
    exit;
}

// Ejecutar DELETE
$stmt = $conn->prepare("DELETE FROM productos WHERE id=?");
$stmt->bind_param('i', $id);

if ($stmt->execute())
    echo json_encode(['success'=>true,'msg'=>'Eliminado']);
else
    echo json_encode(['success'=>false,'msg'=>'Error al eliminar']);
?>
