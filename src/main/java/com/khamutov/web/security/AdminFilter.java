package com.khamutov.web.security;

import com.khamutov.entities.UserRole;

public class AdminFilter extends SessionFilter {
    @Override
    boolean isAccessGranted(UserRole userRole) {
        return userRole.getAuthority()>1;
    }
}
