<?php
header('Content-Type: application/json');
include "config.php";
include "jwt_helper.php";

// Obtener headers
$headers = getallheaders();
$auth = $headers['Authorization'] ?? ($headers['authorization'] ?? '');
$token = str_replace('Bearer ', '', $auth);

// Validar token con la clase moderna
$payload = JWT_Helper::validateToken($token);
if (!$payload) {
    echo json_encode(['success'=>false,'msg'=>'Token inválido']);
    exit;
}

// Consultar productos
$sql = "SELECT id, nombre, precio, cantidad FROM productos";
$res = $conn->query($sql);

$data = [];
while ($row = $res->fetch_assoc()) {
    $data[] = $row;
}

// Devolver JSON consistente
echo json_encode(['success'=>true, 'productos'=>$data]);
?>
