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
public class Category {
    private Integer id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
