/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.controller;

import java.util.*;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.CS241.studentmanagement.entity.Student;
import com.CS241.studentmanagement.entity.GradeDetails;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author akash
 */

@Controller
@RequestMapping("/teacher/{teacherId}/courses/{courseId}")
public class CourseController {

    @GetMapping("/generateReport")
    public ResponseEntity<InputStreamResource> generateReport(@PathVariable Long teacherId, @PathVariable Long courseId) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // PDF generation logic
        PdfWriter writer = new PdfWriter(out);
        com.itextpdf.kernel.pdf.PdfDocument pdf = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdf);

        // Fetch students and grades
        List<Student> students = getStudentsForCourse(courseId);
        Map<Integer, GradeDetails> studentGradesMap = getGradesForStudents(students); // Change to fetch grades for each student

        // Add title
        document.add(new Paragraph("Student Report"));

        // Add table with student data
        float[] columnWidths = {1, 4, 4, 4, 4, 4, 4, 4};  // Adjust column widths
        Table table = new Table(columnWidths);

        // Header
        table.addCell("Student Name");
        table.addCell("Mid Term 1");
        table.addCell("Term 1 End");
        table.addCell("Mid Term 2");
        table.addCell("Term 2 End");
        table.addCell("Mid Term 3");
        table.addCell("Final");

        // Fill the table with student names and grades
        for (Student student : students) {
            table.addCell(student.getFirstName() + " " + student.getLastName());

            // For each student, get their grades from the map
            GradeDetails grades = studentGradesMap.get(student.getId());
            if (grades != null) {
                table.addCell(grades.getGradeOne() == -1 ? "-" : String.valueOf(grades.getGradeOne()));
                table.addCell(grades.getGradeTwo() == -1 ? "-" : String.valueOf(grades.getGradeTwo()));
                table.addCell(grades.getGradeThree() == -1 ? "-" : String.valueOf(grades.getGradeThree()));
                table.addCell(grades.getGradeFour() == -1 ? "-" : String.valueOf(grades.getGradeFour()));
                table.addCell(grades.getGradeFive() == -1 ? "-" : String.valueOf(grades.getGradeFive()));
                table.addCell(grades.getGradeSix() == -1 ? "-" : String.valueOf(grades.getGradeSix()));
            } else {
                // If no grades available, fill with "-"
                for (int i = 0; i < 6; i++) {
                    table.addCell("-");
                }
            }
        }

        // Add the table to the document
        document.add(table);

        // Close the document
        document.close();

        // Prepare the response as a PDF
        ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=student_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }

    // Mock methods to retrieve students and grades. Replace with actual logic.
    private List<Student> getStudentsForCourse(Long courseId) {
        // Fetch students for the course
        return new ArrayList<>(); 
    }

    private Map<Integer, GradeDetails> getGradesForStudents(List<Student> students) {
        // Fetch grade details for each student
        Map<Integer, GradeDetails> gradesMap = new HashMap<>();
        for (Student student : students) {
            GradeDetails grades = getGradeForStudent(student.getId());
            gradesMap.put(student.getId(), grades);
        }
        return gradesMap;
    }

    private GradeDetails getGradeForStudent(int studentId) {
        // Fetch grade details for a specific student
        return new GradeDetails();
    }
}
