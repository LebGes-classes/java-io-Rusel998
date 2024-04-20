package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ExcelReader {
    public void convertExcelToJsonAndSave(String excelFilePath, String jsonFilePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            /*
            StreamSupport.stream(sheet.spliterator(), false) - создает поток для обработки строк листа Excel.
            .skip(1) - пропускает первую строку, которая является заголовком.
            .collect(Collectors.groupingBy(row -> row.getCell(0).getStringCellValue())) - группирует строки по значению в первой ячейке (имя студента).
            .entrySet().stream() - преобразует Map в поток записей.
            .map(this::mapEntryToStudent) - преобразует каждую запись в объект Student.
            .collect(Collectors.toList()) - собирает результат в список студентов.
             */
            List<Student> students = StreamSupport.stream(sheet.spliterator(), false)
                    .skip(1) // skip header row
                    .collect(Collectors.groupingBy(row -> row.getCell(0).getStringCellValue()))
                    .entrySet().stream()
                    .map(this::mapEntryToStudent)
                    .collect(Collectors.toList());

            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

            // Convert to JSON
            String json = writer.writeValueAsString(students);

            // Write JSON to file
            try (FileWriter fileWriter = new FileWriter(jsonFilePath)) {
                fileWriter.write(json);
            }
        }
    }

    private Student mapEntryToStudent(java.util.Map.Entry<String, List<Row>> entry) {
        String studentName = entry.getKey();
        /*
        List<Subject> studentSubjects = ... - создает список предметов студента из списка строк в записи.
        entry.getValue().stream() - создает поток строк для каждого студента.
        .map(this::mapRowToSubject) - преобразует каждую строку в объект Subject.
        .collect(Collectors.toList()) - собирает результат в список предметов.
         */
        List<Subject> studentSubjects = entry.getValue().stream()
                .map(this::mapRowToSubject)
                .collect(Collectors.toList());

        return new Student(studentName, studentSubjects);
    }

    private Subject mapRowToSubject(Row row) {
        String subjectName = row.getCell(1).getStringCellValue();
        String teacherName = row.getCell(2).getStringCellValue();

        Teacher teacher = new Teacher(teacherName, subjectName);
        return new Subject(subjectName, teacher);
    }
}


