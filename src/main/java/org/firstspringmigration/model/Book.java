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
public class Book {
    private Integer id;
    private String title;
    private int year;
    private String language;
    private Author author;
    private Category category;


    public Book(String title, int year, String language, Author author, Category category) {
        this.title = title;
        this.year = year;
        this.language = language;
        this.author = author;
        this.category = category;
    }
}
