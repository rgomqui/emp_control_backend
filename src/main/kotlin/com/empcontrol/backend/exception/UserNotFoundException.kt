package com.empcontrol.backend.exception

class UserNotFoundException: RuntimeException {

    constructor() : super("User not found")

    constructor(message: String) : super(message)
}