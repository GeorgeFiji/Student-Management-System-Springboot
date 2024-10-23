package com.CS241.studentmanagement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author akash
 */

@Table
@Entity(name="student_course_details")
public class StudentCourseDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="student_id")
	private int studentId;
	
	@Column(name="course_id")
	private int courseId;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)  
	@JoinColumn(name="grade_details_id")
	private GradeDetails gradeDetails;
	
	public StudentCourseDetails() {
		
	}

	public StudentCourseDetails(int studentId, int courseId,
			GradeDetails gradeDetails) {
		this.studentId = studentId;
		this.courseId = courseId;
	
		this.gradeDetails = gradeDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public GradeDetails getGradeDetails() {
		return gradeDetails;
	}

	public void setGradeDetails(GradeDetails gradeDetails) {
		this.gradeDetails = gradeDetails;
	}	
}