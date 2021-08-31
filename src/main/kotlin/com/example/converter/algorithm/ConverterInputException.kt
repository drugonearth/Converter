package com.example.converter.algorithm

class ConverterInputException: Exception() {
    override val message: String
        get() = "Incorrect data"
}