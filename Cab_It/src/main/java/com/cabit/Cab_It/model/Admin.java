package com.cabit.Cab_It.model;

import java.util.*;

public class Admin
{
    /*
    * Basic POJO class for admin
    * */
    private String id;
    private String username;
    private String password;
    private final Role ROLE = Role.ADMIN;

    // Access privilege controlling data
    public static final Map<String, Set<Role>> CRUD_PRIVILEGES = new HashMap<>()
    {
        {
        	put("create", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to insert an admin
            put("read", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to access an admin
            put("update", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to update an admin
            put("delete", Set.of(
                    Role.ADMIN
            )); //Defines which role(s) could be able to delete an admin
        }
    };

    public Admin(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getROLE() {
        return ROLE;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
