package ua.com.alevel.db.impl;

import com.google.gson.Gson;
import ua.com.alevel.db.FileDb;
import ua.com.alevel.entity.Student;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonFileDb implements FileDb {

    private final List<Student> students = new ArrayList<>();

    @Override
    public void create(Student student) {
        readJson();
        student.setId(generateId());
        students.add(student);
        writeJson();
    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Optional<Student> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Collection<Student> findAll() {
        readJson();
        return this.students;
    }

    private void readJson() {
        Gson gson = new Gson();
        try {
            Student[] from = gson.fromJson(new FileReader("students.json"), Student[].class);
            if (from != null) {
                this.students.clear();
                this.students.addAll(Arrays.asList(from));
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private void writeJson() {
        Gson gson = new Gson();
        try(FileWriter fileWriter = new FileWriter("students.json")) {
            String json = gson.toJson(this.students);
            fileWriter.write(json);
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (this.students.stream().anyMatch(s -> s.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}
