package com.ntqsolution.demo.config;


import com.google.common.collect.Sets;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.ntqsolution.demo.config.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    PROJECT_MANAGER(Sets.newHashSet(PROJECT_READ, PROJECT_WRITE)),
    EMPLOYEE_MANAGER(Sets.newHashSet(EMPLOYEE_READ, EMPLOYEE_WRITE)),
    ADMIN(Sets.newHashSet(PROJECT_READ, PROJECT_WRITE, EMPLOYEE_WRITE, EMPLOYEE_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    @NotNull
    public Set<SimpleGrantedAuthority> grantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
