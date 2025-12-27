package com.ufc.jornal.exception

class PermissionDeniedForPostCreationException: RuntimeException {
    constructor() : super("Unauthorized to create post")
}