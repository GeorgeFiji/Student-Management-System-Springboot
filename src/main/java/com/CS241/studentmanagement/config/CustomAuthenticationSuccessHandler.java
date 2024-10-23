/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.CS241.studentmanagement.entity.Student;
import com.CS241.studentmanagement.entity.Teacher;
import com.CS241.studentmanagement.entity.Admin;
import com.CS241.studentmanagement.entity.Parent;
import com.CS241.studentmanagement.service.AdminService;
import com.CS241.studentmanagement.service.ParentService;
import com.CS241.studentmanagement.service.StudentService;
import com.CS241.studentmanagement.service.TeacherService;

/**
 * @author akash
 */

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
        @Autowired
	private AdminService adminService;
        
        @Autowired
	private ParentService parentService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication auth) throws IOException, ServletException {
            
		String role = auth.getAuthorities().iterator().next().toString();
		
		//redirecting the user to proper url depending on the authority
		if(role.equals("ROLE_STUDENT")) {
			String userName = auth.getName();
			Student theStudent = studentService.findByStudentName(userName);
			int userId = theStudent.getId(); //student id is a part of the url to get the current student in the controller class 
			HttpSession session = request.getSession();
			session.setAttribute("user", theStudent);
			response.sendRedirect(request.getContextPath() + "/student/" + userId + "/courses");
			
		} else if(role.equals("ROLE_TEACHER")) {
			String userName = auth.getName();
			Teacher theTeacher = teacherService.findByTeacherName(userName);
			int userId = theTeacher.getId();
			HttpSession session = request.getSession();
			session.setAttribute("user", theTeacher);
			response.sendRedirect(request.getContextPath() + "/teacher/" + userId + "/courses");
		} 
                else if(role.equals("ADMIN")){ //if the role is admin
                        String userName = auth.getName();
			Admin theAdmin = adminService.findByAdminName(userName);
			int userId = theAdmin.getId();
			HttpSession session = request.getSession();
			session.setAttribute("user", theAdmin);
                        response.sendRedirect(request.getContextPath() + "/admin/" + userId + "adminPanel");
		}
                else if(role.equals("ROLE_PARENT")){ //if the role is parent
                        String userName = auth.getName();
			Parent theParent = parentService.findByParentName(userName);
			int userId = theParent.getId();
			HttpSession session = request.getSession();
			session.setAttribute("user", theParent);
                        response.sendRedirect(request.getContextPath() + "/student/" + userId + "/courses");
		}
                else { //if the role is admin - in memory authorisation
			response.sendRedirect(request.getContextPath() + "/admin/adminPanel");
		}
	}
}

