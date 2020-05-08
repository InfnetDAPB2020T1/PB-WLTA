package com.example.pb_android_radion.throws

class UsuarioCPFException : Throwable() {
    override val message: String?
    get() = "O CPF deve conter onze digitos."
}
