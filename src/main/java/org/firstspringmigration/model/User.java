package org.firstspringmigration.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    private Integer id;
    private String username;
    private String password;
    private String roleName;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
