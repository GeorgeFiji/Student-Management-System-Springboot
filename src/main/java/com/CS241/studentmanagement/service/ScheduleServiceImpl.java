/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.CS241.studentmanagement.service;

import com.CS241.studentmanagement.dao.ScheduleDao;
import com.CS241.studentmanagement.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author akash
 */

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    public void saveSchedule(Schedule schedule) {
        scheduleDao.saveSchedule(schedule);
    }

    @Override
    public Schedule getSchedule() {
        return null;
    }
}
