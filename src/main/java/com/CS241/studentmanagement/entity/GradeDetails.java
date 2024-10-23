/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author akash
 */

@Table
@Entity(name = "grade_details")
public class GradeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

  
    @Column(name = "grade_one")
    public Integer gradeOne;


    @Column(name = "grade_two")
    public Integer gradeTwo;

    
    @Column(name = "grade_three")
    public Integer gradeThree;


    @Column(name = "grade_four")
    public Integer gradeFour;

    
    @Column(name = "grade_five")
    public Integer gradeFive;

    @Column(name = "grade_six")
    public Integer gradeSix;

    @OneToOne(mappedBy = "gradeDetails", 
              cascade = {CascadeType.DETACH, CascadeType.MERGE,
                         CascadeType.PERSIST, CascadeType.REFRESH})
    private StudentCourseDetails studentCourseDetails;

    public GradeDetails() {
    }

    public GradeDetails(int id, int gradeOne, int gradeTwo, int gradeThree, int gradeFour, int gradeFive, int gradeSix) {
        this.id = id;
        setGradeOne(gradeOne);
        setGradeTwo(gradeTwo);
        setGradeThree(gradeThree);
        setGradeFour(gradeFour);
        setGradeFive(gradeFive);
        setGradeSix(gradeSix);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGradeOne() {
        if (gradeOne == null) {
            return -1;
        } else {
            return gradeOne;
        }
    }

    public void setGradeOne(int gradeOne) {
        //validateGrade(gradeOne);
        this.gradeOne = gradeOne;
    }

    public int getGradeTwo() {
        if (gradeTwo == null) {
            return -1;
        } else {
            return gradeTwo;
        }
    }

    public void setGradeTwo(int gradeTwo) {
        //validateGrade(gradeTwo);
        this.gradeTwo = gradeTwo;
    }

    public int getGradeThree() {
        if (gradeThree == null) {
            return -1;
        } else {
            return gradeThree;
        }
    }

    public void setGradeThree(int gradeThree) {
        //validateGrade(gradeThree);
        this.gradeThree = gradeThree;
    }

    public int getGradeFour() {
        if (gradeFour == null) {
            return -1;
        } else {
            return gradeFour;
        }
    }

    public void setGradeFour(int gradeFour) {
        //validateGrade(gradeFour);
        this.gradeFour = gradeFour;
    }

    public int getGradeFive() {
        if (gradeFive == null) {
            return -1;
        } else {
            return gradeFive;
        }
    }

    public void setGradeFive(int gradeFive) {
        //validateGrade(gradeFive);
        this.gradeFive = gradeFive;
    }

    public int getGradeSix() {
        if (gradeSix == null) {
            return -1;
        } else {
            return gradeSix;
        }
    }

    public void setGradeSix(int gradeSix) {
        //validateGrade(gradeSix);
        this.gradeSix = gradeSix;
    }

    public StudentCourseDetails getStudentCourseDetails() {
        return studentCourseDetails;
    }

    public void setStudentCourseDetails(StudentCourseDetails studentCourseDetails) {
        this.studentCourseDetails = studentCourseDetails;
    }

    // Utility method to validate grade between 1 and 100
    private void validateGrade(int grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 1 and 100.");
        }
    }

}
