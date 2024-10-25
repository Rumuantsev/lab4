package org.example;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;


@Getter
@ToString
@AllArgsConstructor
class Person {
    private String name;
    private String surname;
    private Boolean subscribed;
    private List<Book> favoriteBooks;

    public List<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
class Book {
    private String name;
    private String author;
    private Integer publishingYear;
    private String isbn;
    private String publisher;

    public int getPublishingYear() {
        return publishingYear;
    }

    public String getAuthor() {
        return author;
    }
}
