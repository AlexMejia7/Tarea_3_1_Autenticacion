<?php
use Firebase\JWT\JWT;
use Firebase\JWT\Key;

require 'vendor/autoload.php'; // Asegúrate de que Composer haya creado la carpeta vendor

class JWT_Helper {
    private static $key = "GRUPO4"; // Cambia esto por algo más seguro

    // Generar token
    public static function generateToken($data) {
        $payload = [
            "iat" => time(),
            "exp" => time() + (60*60), // 1 hora de validez
            "data" => $data
        ];

        return JWT::encode($payload, self::$key, 'HS256');
    }

    // Validar token
    public static function validateToken($token) {
        try {
            $decoded = JWT::decode($token, new Key(self::$key, 'HS256'));
            return $decoded->data;
        } catch (Exception $e) {
            return false; // Token inválido
        }
    }
}
?>
