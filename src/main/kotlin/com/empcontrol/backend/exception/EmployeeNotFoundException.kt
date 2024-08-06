package com.empcontrol.backend.exception

class EmployeeNotFoundException: RuntimeException {

    constructor() : super("Employee not found")

    constructor(message: String) : super(message)
}