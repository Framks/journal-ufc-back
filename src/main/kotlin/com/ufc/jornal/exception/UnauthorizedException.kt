package com.ufc.jornal.exception

class UnauthorizedException: RuntimeException {
    constructor() : super("Unauthorized access")
}