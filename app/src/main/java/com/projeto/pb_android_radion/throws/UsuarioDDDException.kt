package com.projeto.pb_android_radion.throws

class UsuarioDDDException : Throwable() {
    override val message: String?
        get() = "O DDD deve conter dois digitos."
}
