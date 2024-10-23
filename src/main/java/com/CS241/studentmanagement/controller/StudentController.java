/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.CS241.studentmanagement.entity.Course;
import com.CS241.studentmanagement.entity.GradeDetails;
import com.CS241.studentmanagement.entity.Student;
import com.CS241.studentmanagement.entity.StudentCourseDetails;
import com.CS241.studentmanagement.service.CourseService;
import com.CS241.studentmanagement.service.StudentCourseDetailsService;
import com.CS241.studentmanagement.service.StudentService;

/**
 * @author akash
 */

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentCourseDetailsService studentCourseDetailsService;

	@GetMapping("/{studentId}/courses")
	public String showStudentPanel(@PathVariable("studentId") int studentId, Model theModel) {
		Student student = studentService.findByStudentId(studentId); //accessing student logged in
		List<Course> courses = student.getCourses();
		theModel.addAttribute("student", student);
		theModel.addAttribute("courses", courses);
		return "student/student-courses";
	}
	
	@GetMapping("/{studentId}/courses/{courseId}")
	public String showStudentCourse(@PathVariable("studentId") int studentId, @PathVariable("courseId") int courseId, Model theModel) {
		Student student = studentService.findByStudentId(studentId);
		List<Course> courses = student.getCourses();
		Course course = courseService.findCourseById(courseId);
		StudentCourseDetails studentCourseDetails = studentCourseDetailsService.findByStudentAndCourseId(studentId, courseId);
		GradeDetails gradeDetails = studentCourseDetails.getGradeDetails();
		theModel.addAttribute("course", course);
		theModel.addAttribute("courses", courses);
		theModel.addAttribute("student", student);
		theModel.addAttribute("gradeDetails", gradeDetails);
		return "student/student-course-detail";
	}
}
