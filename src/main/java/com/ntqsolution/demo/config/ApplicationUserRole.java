package com.ntqsolution.demo.config;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.ntqsolution.demo.config.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    PROJECT_MANAGER(Sets.newHashSet(PROJECT_LIST, PROJECT_DETAILS)),
    EMPLOYEE_MANAGER(Sets.newHashSet(EMPLOYEE_LIST, EMPLOYEE_DETAILS)),
    ADMIN(Sets.newHashSet(EMPLOYEE_LIST, EMPLOYEE_DETAILS, PROJECT_LIST, PROJECT_DETAILS));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
