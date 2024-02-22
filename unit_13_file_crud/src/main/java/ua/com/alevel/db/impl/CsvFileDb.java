package ua.com.alevel.db.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ua.com.alevel.db.FileDb;
import ua.com.alevel.entity.Student;
import ua.com.alevel.factory.FileType;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvFileDb implements FileDb {

    private final List<Student> students = new ArrayList<>();

    @Override
    public void create(Student student) {
        readCsv();
        student.setId(generateId());
        students.add(student);
        writeCsv();
    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void delete(String id) {
        readCsv();
        students.removeIf(student -> student.getId().equals(id));
        writeCsv();
    }

    @Override
    public Optional<Student> findById(String id) {
        readCsv();
        return students.stream().filter(student -> student.getId().equals(id)).findFirst();
    }

    @Override
    public Collection<Student> findAll() {
        readCsv();
        return this.students;
    }

    private void readCsv() {
        try(CSVReader csvReader = new CSVReader(new FileReader(FileType.STUDENTS_CSV.getType()))) {
            List<String[]> list = csvReader.readAll();
            this.students.clear();
            for (String[] s : list) {
                Student student = new Student();
                student.setId(s[0]);
                student.setFirstName(s[1]);
                student.setLastName(s[2]);
                student.setAge(Integer.parseInt(s[3]));
                students.add(student);
            }
        } catch (IOException | CsvException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private void writeCsv() {
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FileType.STUDENTS_CSV.getType()))) {
            List<String[]> list = new ArrayList<>();
            for (Student student : this.students) {
                String[] strings = new String[]{
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        String.valueOf(student.getAge())
                };
                list.add(strings);
            }
            csvWriter.writeAll(list);
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
