/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.controller;

import com.CS241.studentmanagement.dao.ScheduleDao;
import com.CS241.studentmanagement.dao.StudentDao;
import com.CS241.studentmanagement.dao.TeacherDao;
import com.CS241.studentmanagement.entity.Schedule;
import com.CS241.studentmanagement.entity.Student;
import com.CS241.studentmanagement.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

/**
 * @author akash
 */

@Controller
public class ScheduleController {
    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    @GetMapping("/admin/schedule/create")
    public String showScheduleForm(Model model) {
        // Create a blank schedule object for the form
        Schedule schedule = new Schedule();
        model.addAttribute("schedule", schedule);

        // Fetch all teachers for the dropdown
        List<Teacher> teachers = teacherDao.findAllTeachers();
        model.addAttribute("teachers", teachers);

        // Fetch all students for the multi-select
        List<Student> students = studentDao.findAllStudents();
        model.addAttribute("students", students);

        return "/admin/create-schedule"; // Points to the Thymeleaf template
    }
    
    @PostMapping("/admin/schedule/save")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule, Model model) {
        // Save the schedule using the DAO layer
        scheduleDao.saveSchedule(schedule);
        model.addAttribute("message", "Schedule created successfully!");
        return "/admin/schedule-confirmation";  // Redirect to a list of schedules or any page
    }

}
