package com.empcontrol.backend.enums


enum class EmployeeRoles(var permissions: List<RolePermissions>) {
    ADMIN(
        listOf(
            RolePermissions.READ_ALL_EMPLOYEES,
            RolePermissions.READ_ONE_EMPLOYEE,
            RolePermissions.WRITE_ONE_EMPLOYEE,
            RolePermissions.UPDATE_ONE_EMPLOYEE,
            RolePermissions.DISABLE_ONE_EMPLOYEE,

    )
    ),
    USER(
        listOf(
            RolePermissions.READ_ALL_EMPLOYEES,
            RolePermissions.READ_ONE_EMPLOYEE,
            )
    )
}