package org.firstspringmigration.model;

import lombok.Getter;

import java.nio.file.AccessDeniedException;

@Getter
public class Role {
    private static final String admin="admin";
    private static final String user="user";
    public static void hasRole(User user) throws AccessDeniedException {
        if (!user.getRoleName().equals(admin)){
            throw new AccessDeniedException("Access is denied");
        }
    }
    public static void userAccount(User user, Integer id) throws AccessDeniedException {
        if (!user.getId().equals(id) && (!user.getRoleName().equals(admin))){
            throw new AccessDeniedException("Access is denied");
        }

    }

}
