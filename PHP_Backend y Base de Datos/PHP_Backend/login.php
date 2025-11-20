<?php
header('Content-Type: application/json');
error_reporting(0);

include "config.php";       // conexión
include "jwt_helper.php";   // helper JWT

// Recibir parámetros POST
$username = $_POST['usuario'] ?? '';
$password = $_POST['password'] ?? '';

if (!$username || !$password) {
    echo json_encode(['success'=>false, 'msg'=>'Faltan credenciales']);
    exit;
}

// Buscar usuario en la base de datos
$stmt = $conn->prepare("SELECT id, username, password FROM users WHERE username=? LIMIT 1");
$stmt->bind_param('s', $username);
$stmt->execute();
$res = $stmt->get_result();

if ($res->num_rows !== 1) {
    echo json_encode(['success'=>false, 'msg'=>'Credenciales inválidas']);
    exit;
}

$row = $res->fetch_assoc();

// Comprobación de contraseña en texto plano (solo pruebas)
if ($password !== $row['password']) {
    echo json_encode(['success'=>false, 'msg'=>'Credenciales inválidas']);
    exit;
}

// Crear JWT usando la clase moderna
$token = JWT_Helper::generateToken([
    "id" => $row['id'],
    "username" => $row['username']
]);

// Respuesta exitosa
echo json_encode([
    'success' => true,
    'token' => $token
], JSON_PRETTY_PRINT);
exit;
?>
