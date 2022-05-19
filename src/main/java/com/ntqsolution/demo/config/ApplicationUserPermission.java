package com.ntqsolution.demo.config;

public enum ApplicationUserPermission {
    PROJECT_LIST("project:list"),
    PROJECT_DETAILS("project:details"),
    EMPLOYEE_LIST("employee:list"),
    EMPLOYEE_DETAILS("employee:details");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
