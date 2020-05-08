package com.example.pb_android_radion.throws

class UsuarioTelefoneException : Throwable() {
    override val message: String?
        get() = "O Telefone deve conter nove digitos."

}
