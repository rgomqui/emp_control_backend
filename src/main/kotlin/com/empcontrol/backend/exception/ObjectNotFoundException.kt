package com.empcontrol.backend.exception

class ObjectNotFoundException: RuntimeException {

    constructor() : super("Employee not found")

    constructor(message: String) : super(message)
}