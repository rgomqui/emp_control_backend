package com.empcontrol.backend.enums


enum class UserRolesEnum(var permissions: List<RolePermissionsEnum>) {
    ADMIN(
        listOf(
            RolePermissionsEnum.READ_ALL_EMPLOYEES,
            RolePermissionsEnum.READ_ONE_EMPLOYEE,
            RolePermissionsEnum.WRITE_ONE_EMPLOYEE,
            RolePermissionsEnum.UPDATE_ONE_EMPLOYEE,
            RolePermissionsEnum.DISABLE_ONE_EMPLOYEE,

    )
    ),
    USER(
        listOf(
            RolePermissionsEnum.READ_ALL_EMPLOYEES,
            RolePermissionsEnum.READ_ONE_EMPLOYEE,
            )
    )
}