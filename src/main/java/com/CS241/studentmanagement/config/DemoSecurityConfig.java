/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.config;

import com.CS241.studentmanagement.service.AdminService;
import com.CS241.studentmanagement.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.CS241.studentmanagement.service.StudentService;
import com.CS241.studentmanagement.service.TeacherService;

/**
 * @author akash
 */

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
        
        @Autowired
	private ParentService parentService;
	
        @Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//student and teacher login credentials are stored in mysql db, while admin username and password defined below as in-memory
		
		auth
        	.userDetailsService(studentService)
        	.passwordEncoder(passwordEncoder());
		auth
        	.userDetailsService(teacherService)
        	.passwordEncoder(passwordEncoder());
                auth
        	.userDetailsService(parentService)
        	.passwordEncoder(passwordEncoder());
                auth
        	.userDetailsService(adminService)
        	.passwordEncoder(passwordEncoder());
                
		auth.inMemoryAuthentication()  //admin password username
                .withUser("admin")
                .password(passwordEncoder().encode("1"))
                .roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").authenticated()
			.antMatchers("/admin/**").hasRole("ADMIN") //user with student or teacher role cannot access url starting with admin
			.antMatchers("/student/**").hasRole("STUDENT")
			.antMatchers("/teacher/**").hasRole("TEACHER")
                        .antMatchers("/parent/**").hasRole("PARENT")
			.and()
			.formLogin()
				.loginPage("/showLoginPage") //custom login page is generated in LoginController
				.loginProcessingUrl("/authenticateTheUser") //authenticateTheUser is automatically done by spring boot
				.successHandler(customAuthenticationSuccessHandler) //after login, user is redirected to home page depending on the role.
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied"); //simple access denied mapping defined in LoginController in case of user
		                                                             //tries to access a page without the proper authority				
	}
	
	//needed for admin password encoding for security purposes
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}


