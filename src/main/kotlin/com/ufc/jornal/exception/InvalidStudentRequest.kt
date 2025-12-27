package com.ufc.jornal.exception

class InvalidStudentRequest: RuntimeException {
    constructor(message: String) : super(message)
}