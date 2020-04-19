package com.example.pb_android_radion.Throws

class UsuarioDDDException : Throwable() {
    override val message: String?
        get() = "O DDD deve conter dois digitos."
}
