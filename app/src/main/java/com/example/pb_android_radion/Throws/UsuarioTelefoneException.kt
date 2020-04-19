package com.example.pb_android_radion.Throws

class UsuarioTelefoneException : Throwable() {
    override val message: String?
        get() = "O Telefone deve conter nove digitos."

}
