package com.example.taskSpring.service;

import com.example.taskSpring.model.*;

import com.example.taskSpring.repository.OffDaysRepository;
import com.example.taskSpring.repository.WorkingDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Service

public class AppUserDataService {

    private final WorkingDaysRepository workingDaysRepository;
    private final OffDaysRepository offDaysRepository;


    @Autowired
    public AppUserDataService( WorkingDaysRepository workingDaysRepository, OffDaysRepository offDaysRepository) {
        this.workingDaysRepository = workingDaysRepository;
        this.offDaysRepository = offDaysRepository;

    }



    public double setPay(double hourlyPay, Calendar workingDays, int regularHours, int overtimeHours)
    {
        List<OffDays> offDaysList = offDaysRepository.findAll();
        double totalPay = 0;
        for (OffDays offDays : offDaysList) {
            if (workingDays.get(Calendar.DAY_OF_WEEK) > 6) {

                totalPay += hourlyPay * overtimeHours * 1.5 +
                        hourlyPay * regularHours * 1.25;
            } else if (offDays.getDate().equals(workingDays)) {
                totalPay += hourlyPay * overtimeHours * 2 + hourlyPay *
                        regularHours * 1.5;
            } else if (workingDays.get(Calendar.DAY_OF_WEEK) > 6) {

                totalPay += hourlyPay * overtimeHours * 1.5 + hourlyPay *
                        regularHours * 1.25;
            } else if (workingDays.get(Calendar.DAY_OF_WEEK) > 6 && offDays.getDate().equals(workingDays)) {

                totalPay += hourlyPay * overtimeHours * 2 + hourlyPay *
                        regularHours * 1.5;
            } else {
                totalPay += hourlyPay * overtimeHours * 1.25 +
                        hourlyPay * regularHours;
            }

        }

        return totalPay;
    }


    public HashMap<Long,AppUserData> getUserData()
    {

        HashMap<Long,AppUserData> appUserDataMap = new HashMap<>();
        List<WorkingDays> workingDaysList = workingDaysRepository.findAll();


            for(WorkingDays workingDays : workingDaysList )
            {
                AppUserData appUserData = new AppUserData();
                appUserData.setUserId( workingDays.getUser().getUserId() );
                appUserData.setName( workingDays.getUser().getName());
                appUserData.setLastName( workingDays.getUser().getSurname() );
                appUserData.setTotalHours( workingDays.getHours());
                appUserData.setRegularHours(8);
                appUserData.setOvertimeHours( appUserData.getTotalHours() - appUserData.getRegularHours() );
                appUserData.setTotalPay(setPay(workingDays.getUser().getHourlyPayment(),workingDays.getDate(),appUserData.getRegularHours(),
                        appUserData.getOvertimeHours()));

                if(appUserDataMap.containsKey( appUserData.getUserId() ) )
                {
                    AppUserData userData = appUserDataMap.get( appUserData.getUserId() );
                    userData.setTotalHours( appUserDataMap.get( appUserData.getUserId() ).getTotalHours() + appUserData.getTotalHours());
                    userData.setRegularHours( appUserDataMap.get( appUserData.getUserId()).getRegularHours() + 8);
                    userData.setOvertimeHours(  appUserDataMap.get( appUserData.getUserId()).getOvertimeHours() + appUserData.getOvertimeHours() );
                    userData.setTotalPay( appUserDataMap.get( appUserData.getUserId()).getTotalPay() + appUserData.getTotalPay() );

                }

                else {
                    appUserDataMap.put( appUserData.getUserId(),appUserData );
                }


        }
        return appUserDataMap;
    }






}
