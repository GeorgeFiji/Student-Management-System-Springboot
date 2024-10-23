/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.dao;

import com.CS241.studentmanagement.entity.Schedule;
import java.util.List;

/**
 * @author akash
 */

public interface ScheduleDao {
    void saveSchedule(Schedule schedule);
    List<Schedule> findAll();
}
