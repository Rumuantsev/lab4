package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Path path = Paths.get("src/books.json"); // Пример пути
        InputStreamReader reader = new InputStreamReader(Files.newInputStream(path));
        Type personListType = new TypeToken<List<Person>>() {}.getType();
        List<Person> people = gson.fromJson(reader, personListType);

        System.out.println("Persons:");
        people.stream()
                .map(person -> person.getName() + " " + person.getSurname())
                .forEach(System.out::println);
        System.out.println("Count: " + people.size());
    }
}