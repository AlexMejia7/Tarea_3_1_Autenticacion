<?php
header('Content-Type: application/json');

// Recibir datos enviados por POST
$user = $_POST['usuario'] ?? '';
$pass = $_POST['password'] ?? '';

// Devolverlos en JSON para depuración
$response = [
    'usuario_recibido' => $user,
    'password_recibido' => $pass,
    'success' => ($user !== '' && $pass !== ''),
    'msg' => ($user !== '' && $pass !== '') ? 'Datos recibidos correctamente' : 'Faltan datos'
];

echo json_encode($response, JSON_PRETTY_PRINT);
?>
