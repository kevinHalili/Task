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
public class OffDays {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long offDayId;
    private Calendar date;


}
