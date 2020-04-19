package com.example.pb_android_radion.Throws

class UsuarioCPFException : Throwable() {
    override val message: String?
    get() = "O CPF deve conter onze digitos."
}
