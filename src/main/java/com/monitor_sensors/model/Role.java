package com.monitor_sensors.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    VIEWER,
    ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
