package org.firstspringmigration.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Author {
    private Integer id;
    private String name;
    private String surname;
    private String address;

    public Author(String name, String surname, String address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }
}
