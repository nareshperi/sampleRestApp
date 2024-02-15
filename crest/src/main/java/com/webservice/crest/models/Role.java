package com.webservice.crest.models;

public enum Role {
    USER ("USER"),
    ADMIN ("ADMIN");

    private final String name;       

    private Role(String role) {
        name = role;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
