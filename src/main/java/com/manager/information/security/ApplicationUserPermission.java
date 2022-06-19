package com.manager.information.security;

public enum ApplicationUserPermission {
    USER_READ("user: read"),
    USER_WRITE("user: writer"),
    USER_DELETE("user: delete"),
    ADMIN_READ("activity: read"),
    ADMIN_WRITE("activity: write"),
    ADMIN_DELETE("activity: delete");


    private final String permision;

    ApplicationUserPermission(String permision) {
        this.permision = permision;
    }

    public String getPermision() {
        return permision;
    }
}
