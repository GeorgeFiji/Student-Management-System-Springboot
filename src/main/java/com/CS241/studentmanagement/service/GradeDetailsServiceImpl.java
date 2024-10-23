/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.CS241.studentmanagement.dao.GradeDetailsDao;
import com.CS241.studentmanagement.entity.GradeDetails;

/**
 * @author akashi
 */

@Service
public class GradeDetailsServiceImpl implements GradeDetailsService {

	@Autowired
	private GradeDetailsDao gradeDetailsDao;
	
	@Override
	@Transactional
	public void save(GradeDetails gradeDetails) {
		gradeDetailsDao.save(gradeDetails);
	}

	@Override
	@Transactional
	public GradeDetails findById(int id) {
		return gradeDetailsDao.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		gradeDetailsDao.deleteById(id);
	}

}
