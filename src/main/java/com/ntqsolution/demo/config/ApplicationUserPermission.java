package com.ntqsolution.demo.config;

public enum ApplicationUserPermission {
    PROJECT_READ("project:read"),
    PROJECT_WRITE("project:write"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
