package com.example.taskSpring.service;

import com.example.taskSpring.model.WorkingDays;
import com.example.taskSpring.repository.WorkingDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WorkingDaysService {
    private final WorkingDaysRepository workingDaysRepository;
    @Autowired
    public WorkingDaysService(WorkingDaysRepository workingDaysRepository) {
        this.workingDaysRepository = workingDaysRepository;
    }

    public List<WorkingDays> getAllWorkingDays()
    {
        return workingDaysRepository.findAll();
    }

}
