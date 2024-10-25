package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Path path = Paths.get("src/books.json");
        InputStreamReader reader = new InputStreamReader(Files.newInputStream(path));
        Type personListType = new TypeToken<List<Person>>() {}.getType();
        List<Person> people = gson.fromJson(reader, personListType);

        //1 задание
        System.out.println("Persons:");
        people.forEach(p -> System.out.println(p.getName() + " " + p.getSurname()));
        System.out.println("Count: " + people.size());

        //2 задание

        Set<Book> uniqueBooksSet = people.stream()
                .flatMap(person -> person.getFavoriteBooks().stream())
                .collect(Collectors.toSet());

        System.out.println("Unique books:");
        uniqueBooksSet.forEach(System.out::println);

        System.out.println("Count unique books: " + uniqueBooksSet.size());

        //3 задание

        System.out.println("All books sorted by publishing year:");
        uniqueBooksSet.stream()
                .sorted(Comparator.comparingInt(Book::getPublishingYear))
                .forEach(System.out::println);

        //4 задание

        boolean hasAustenBooks = uniqueBooksSet.stream()
                .anyMatch(book -> book.getAuthor().equals("Jane Austen"));

        System.out.println(hasAustenBooks);

        //5 задание

        Optional<Integer> maxBooks = people.stream()
                .map(person -> person.getFavoriteBooks().size())
                .max(Integer::compareTo);

        maxBooks.ifPresent(max -> System.out.println("Bigest count books: " + max));

        //6 задание

        OptionalDouble averageBooks = people.stream()
                .mapToInt(person -> person.getFavoriteBooks().size())
                .average();

        if (averageBooks.isPresent()) {
            double average = averageBooks.getAsDouble();

            people.stream()
                    .map(person -> {
                        double bookCount = person.getFavoriteBooks().size();
                        String sms = (bookCount < average) ? "read more" : (bookCount > average) ? "you are a bookworm" : "fine";
                        return person.getName() + " " + person.getSurname() + " " + sms;
                    })
                    .forEach(System.out::println);
        }
    }
}

