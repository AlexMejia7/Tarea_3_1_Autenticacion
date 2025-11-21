<?php
// Incluimos el helper de JWT
require 'jwt_helper.php';

// Generar un token de ejemplo
$token = JWT_Helper::generateToken([
    "id" => 1,
    "username" => "admin Alex Nain Mejia Castro"
]);

echo "<h2>Token generado:</h2>";
echo "<p>$token</p>";

// Validar el token generado
$user = JWT_Helper::validateToken($token);

echo "<h2>Datos decodificados del token:</h2>";
echo "<pre>";
print_r($user);
echo "</pre>";

// Mensaje de éxito para verificar que todo funciona
if ($user) {
    echo "<p style='color:green;'>✅ JWT Helper funciona correctamente</p>";
} else {
    echo "<p style='color:red;'>❌ Error al validar el token</p>";
}
?>
