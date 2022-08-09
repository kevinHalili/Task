package com.example.taskSpring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserData {

    private long userId;

    private String name;

    private String lastName;

    private int regularHours;

    private int overtimeHours;

    private int totalHours;

    private double totalPay;

}
