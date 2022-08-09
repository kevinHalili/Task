package com.example.taskSpring.service;

import com.example.taskSpring.model.OffDays;
import com.example.taskSpring.repository.OffDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffDaysService {

    private final OffDaysRepository offDaysRepository;

    @Autowired
    public OffDaysService(OffDaysRepository offDaysRepository) {
        this.offDaysRepository = offDaysRepository;
    }

    public List<OffDays> getAllOffDays()
    {
        return offDaysRepository.findAll();
    }

}
