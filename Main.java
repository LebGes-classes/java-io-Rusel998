package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            ExcelReader converter = new ExcelReader();
            converter.convertExcelToJsonAndSave("C:/Users/rusur/Desktop/niga.xlsx",
                    "C:/Users/rusur/IdeaProjects/Journal/Journal.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Student> students = mapper.readValue(new File("C:/Users/rusur/IdeaProjects/Journal/Journal.json"), new TypeReference<List<Student>>() {});
            for (Student student : students) {
                System.out.println("Student Name: " + student.getName());
                System.out.println("Subjects:");
                for (Subject subject : student.getSubjects()) {
                    System.out.println("\tSubject Name: " + subject.getTitle());
                    System.out.println("\tTeacher Name: " + subject.getTeacher().getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
