<?php
header('Content-Type: application/json');
error_reporting(0);

$response = [
    "success" => true,
    "message" => "📡 Conexión exitosa al servidor desde Android",
    "server_ip" => $_SERVER['SERVER_ADDR'],
    "client_ip" => $_SERVER['REMOTE_ADDR'],
    "php_version" => phpversion(),
    "status" => "OK"
];

echo json_encode($response, JSON_PRETTY_PRINT);
?>
