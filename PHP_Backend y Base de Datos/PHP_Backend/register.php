<?php
header('Content-Type: application/json');
error_reporting(0);

include "config.php"; // conexión

$user = $_POST['usuario'] ?? '';
$pass = $_POST['password'] ?? '';

if (!$user || !$pass) {
    echo json_encode(["success"=>false, "msg"=>"Faltan datos"], JSON_PRETTY_PRINT);
    exit;
}

// Verificar si el usuario ya existe
$stmt = $conn->prepare("SELECT id FROM users WHERE username=?");
$stmt->bind_param("s", $user);
$stmt->execute();
$res = $stmt->get_result();

if($res->num_rows > 0){
    echo json_encode(["success"=>false, "msg"=>"Usuario ya existe"], JSON_PRETTY_PRINT);
    exit;
}

// Insertar usuario (en texto plano, solo pruebas)
$stmt = $conn->prepare("INSERT INTO users (username,password) VALUES (?, ?)");
$stmt->bind_param("ss", $user, $pass);

if($stmt->execute()){
    echo json_encode(["success"=>true, "msg"=>"Usuario registrado"], JSON_PRETTY_PRINT);
} else {
    echo json_encode(["success"=>false, "msg"=>"Error al registrar usuario"], JSON_PRETTY_PRINT);
}
?>
