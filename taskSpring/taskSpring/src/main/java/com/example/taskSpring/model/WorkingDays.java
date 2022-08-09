package com.example.taskSpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;


@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkingDays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long workingDaysId;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser user;
    private Calendar date;
    private int hours;
}
