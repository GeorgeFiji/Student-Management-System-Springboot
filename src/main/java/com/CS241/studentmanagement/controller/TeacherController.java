/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.CS241.studentmanagement.entity.Course;
import com.CS241.studentmanagement.entity.GradeDetails;
import com.CS241.studentmanagement.entity.Student;
import com.CS241.studentmanagement.entity.StudentCourseDetails;
import com.CS241.studentmanagement.entity.Teacher;
import com.CS241.studentmanagement.service.CourseService;
import com.CS241.studentmanagement.service.GradeDetailsService;
import com.CS241.studentmanagement.service.StudentCourseDetailsService;
import com.CS241.studentmanagement.service.TeacherService;

/**
 * @author akash
 */

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentCourseDetailsService studentCourseDetailsService;

	@Autowired
	private GradeDetailsService gradeDetailsService;
	
	@GetMapping("/{teacherId}/courses")
	public String showTeacherCourses(@PathVariable("teacherId") int teacherId, Model theModel) {
		Teacher teacher = teacherService.findByTeacherId(teacherId);
		List<Course> courses = teacher.getCourses();
		theModel.addAttribute("teacher", teacher);
		theModel.addAttribute("courses", courses);
		return "teacher/teacher-courses";
	}
	
	@GetMapping("/{teacherId}/courses/{courseId}")
	public String showTeacherCourseDetails(@PathVariable("teacherId") int teacherId, @PathVariable("courseId") int courseId, Model theModel) {
		Teacher teacher = teacherService.findByTeacherId(teacherId);
		Course course = courseService.findCourseById(courseId);
		List<Course> courses = teacher.getCourses();
		List<Student> students = course.getStudents();
		
		if(students.size() != 0) {

			List<GradeDetails> gradeDetailsList = new ArrayList<>();
			for(Student student : students) {
                            gradeDetailsList.add(studentCourseDetailsService.findByStudentAndCourseId(student.getId(), courseId).getGradeDetails());
			}
			HashMap<List<Student>, List<GradeDetails>> studentGradeList = new HashMap<>();
			studentGradeList.put(students, gradeDetailsList);
			theModel.addAttribute("studentGradeList", studentGradeList);	
		} 
		
		theModel.addAttribute("teacher", teacher);
		theModel.addAttribute("course", course);
		theModel.addAttribute("courses", courses);
		theModel.addAttribute("students", students);
		return "teacher/teacher-course-details";
	}

	@GetMapping("/{teacherId}/courses/{courseId}/editGrades")
	public String editGradesForm(@PathVariable("teacherId") int teacherId, @PathVariable("courseId") int courseId, Model theModel) {
		Teacher teacher = teacherService.findByTeacherId(teacherId);
		Course course = courseService.findCourseById(courseId);
		List<Course> courses = teacher.getCourses();
		List<Student> students = course.getStudents();
		List<GradeDetails> gradeDetailsList = new ArrayList<>();
		for(Student student : students) {
			gradeDetailsList.add(studentCourseDetailsService.findByStudentAndCourseId(student.getId(), courseId).getGradeDetails());
		}
		
		HashMap<List<Student>, List<GradeDetails>> studentGradeList = new HashMap<>();
		studentGradeList.put(students, gradeDetailsList);
		theModel.addAttribute("studentGradeList", studentGradeList);
		theModel.addAttribute("course", course);
		theModel.addAttribute("courses", courses);
		theModel.addAttribute("teacher", teacher);
		theModel.addAttribute("students", students);
		theModel.addAttribute("gradeDetailsList", gradeDetailsList);
		return "teacher/edit-grades-form";
	}

	@PostMapping("/{teacherId}/courses/{courseId}/editGrades/save/{gradeDetailsId}")
	public String modifyGrades(@ModelAttribute GradeDetails gradeDetails, 
			@PathVariable("teacherId") int teacherId, @PathVariable("courseId") int courseId,
			@PathVariable("gradeDetailsId") int gradeDetailsId) throws Exception {
		
		Teacher teacher = teacherService.findByTeacherId(teacherId);
		Course course = courseService.findCourseById(courseId);
                StudentCourseDetails studentCourseDetails = gradeDetailsService.findById(gradeDetailsId).getStudentCourseDetails();
		studentCourseDetails.setGradeDetails(gradeDetails);
		studentCourseDetailsService.save(studentCourseDetails);
		gradeDetailsService.deleteById(gradeDetailsId);
		
	    return "redirect:/teacher/" + teacherId + "/courses/" + courseId;
	}
	
	@GetMapping("/{teacherId}/courses/{courseId}/assignments/{assignmentId}")
	public String showAssignmentDetails(@PathVariable("teacherId") int teacherId, @PathVariable("courseId") int courseId,
			@PathVariable("assignmentId") int assignmentId, Model theModel) {
		Teacher teacher = teacherService.findByTeacherId(teacherId);
		Course course = courseService.findCourseById(courseId);
		List<Student> students = course.getStudents();
		List<Course> courses = teacher.getCourses();
		List<StudentCourseDetails> studentCourseDetails = new ArrayList<>();
		List<String> assignmentStatuses = new ArrayList<>();		
		HashMap<List<Student>, List<String>> list = new HashMap<>();
		list.put(students, assignmentStatuses);
		theModel.addAttribute("list", list);
		theModel.addAttribute("students", students);
		theModel.addAttribute("courses", courses);
		theModel.addAttribute("teacher", teacher);
		return "teacher/teacher-assignment-status";
	}
}




