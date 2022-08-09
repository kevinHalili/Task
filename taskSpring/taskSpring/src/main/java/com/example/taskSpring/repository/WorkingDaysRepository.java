package com.example.taskSpring.repository;

import com.example.taskSpring.model.WorkingDays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingDaysRepository extends JpaRepository<WorkingDays,Long> {


}
