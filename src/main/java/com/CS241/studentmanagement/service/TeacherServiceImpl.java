/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.CS241.studentmanagement.dao.RoleDao;
import com.CS241.studentmanagement.dao.TeacherDao;
import com.CS241.studentmanagement.entity.Role;
import com.CS241.studentmanagement.entity.Teacher;
import com.CS241.studentmanagement.user.UserDto;

/**
 * @author akash
 */

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherDao teacherDao;
	
	@Autowired 
	private RoleDao roleDao;
	
	@Override
	@Transactional
	public Teacher findByTeacherName(String teacherName) {
		return teacherDao.findByTeacherName(teacherName);
	}

	@Override
	@Transactional
	public void save(UserDto userDto) {
		Teacher teacher = new Teacher();
		teacher.setUserName(userDto.getUserName());
		teacher.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
		teacher.setFirstName(userDto.getFirstName());
		teacher.setLastName(userDto.getLastName());
		teacher.setEmail(userDto.getEmail());		
		teacher.setRole(userDto.getRole());	
		teacherDao.save(teacher);
	}
	
	@Override
	@Transactional
	public void save(Teacher teacher) {
		teacherDao.save(teacher);	
	}
	
	@Override
	@Transactional
	public List<Teacher> findAllTeachers() {
		return teacherDao.findAllTeachers();
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Teacher teacher = teacherDao.findByTeacherName(username);
		if (teacher == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		Collection<Role> role = new ArrayList<>();
		role.add(teacher.getRole());
		return new org.springframework.security.core.userdetails.User(teacher.getUserName(), teacher.getPassword(),
				mapRolesToAuthorities(role));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Teacher findByTeacherId(int id) {
		return teacherDao.findByTeacherId(id);
	}

	@Override
	@Transactional
	public void deleteTeacherById(int id) {
		teacherDao.deleteTeacherById(id);	
	}
}
