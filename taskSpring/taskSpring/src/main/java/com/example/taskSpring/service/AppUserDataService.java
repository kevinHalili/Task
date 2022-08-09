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



    public HashMap<Long,AppUserData> getUserData()
    {

        HashMap<Long,AppUserData> appUserDataMap = new HashMap<>();
        List<WorkingDays> workingDaysList = workingDaysRepository.findAll();
        List<OffDays> offDaysList = offDaysRepository.findAll();

        for(WorkingDays workingDays : workingDaysList )
        {
            for(OffDays offDays : offDaysList)
            {
                AppUserData appUserData = new AppUserData();
                appUserData.setUserId( workingDays.getUser().getUserId() );
                appUserData.setName( workingDays.getUser().getName());
                appUserData.setLastName( workingDays.getUser().getSurname() );
                appUserData.setTotalHours( workingDays.getHours());
                appUserData.setRegularHours(8);
                appUserDataMap.put( appUserData.getUserId(),appUserData );
                appUserData.setOvertimeHours( appUserData.getTotalHours() - appUserData.getRegularHours() );

                if(offDays.getDate().equals( workingDays.getDate() ))
                {
                    appUserData.setTotalPay( workingDays.getUser().getHourlyPayment() *( appUserData.getOvertimeHours() * 2 +
                            appUserData.getRegularHours() * 1.5));
                } else if (workingDays.getDate().get( Calendar.DAY_OF_WEEK ) > 6) {

                    appUserData.setTotalPay( workingDays.getUser().getHourlyPayment() * appUserData.getOvertimeHours() * 1.5 +
                            appUserData.getRegularHours() * 1.25);
                }

                 else if (workingDays.getDate().get( Calendar.DAY_OF_WEEK ) > 6 && offDays.getDate().equals( workingDays.getDate() )) {

                        appUserData.setTotalPay( workingDays.getUser().getHourlyPayment() * (appUserData.getOvertimeHours() * 2 +
                                appUserData.getRegularHours() * 1.5));
                 }
                 else
                {
                    appUserData.setTotalPay( workingDays.getUser().getHourlyPayment() * ( appUserData.getOvertimeHours() +
                            appUserData.getRegularHours() ));
                }



                if(appUserDataMap.containsKey( appUserData.getUserId() ))
                {
                    AppUserData userData = appUserDataMap.get( appUserData.getUserId() );
                    userData.setTotalHours( appUserDataMap.get( appUserData.getUserId() ).getTotalHours() + appUserData.getTotalHours());
                    userData.setRegularHours( appUserData.getRegularHours() +8);
                    userData.setOvertimeHours( userData.getOvertimeHours() + appUserData.getOvertimeHours() );
                    userData.setTotalPay( userData.getTotalPay() + appUserData.getTotalPay() );

                }

            }




        }

        return appUserDataMap;
    }


//    public AppUserData setUserData(Long userId)
//    {
//        long workingDaysId = 1;
//        long offDaysId = 1;
//        AppUserData appUserData = new AppUserData();
//        AppUser user = appUserRepository.findById( userId )
//                .orElseThrow(() -> new IllegalStateException("User with id" + userId + "does not exist"));
//
//
//        appUserData.setFullName( user.getName() + " " + user.getSurname());
//        int totalOvertime = 0;
//        int totalRegular = 0;
//        double totalPay = 0;
//
//
//
//        while(workingDaysRepository.existsById(workingDaysId) || offDaysRepository.existsById(offDaysId)) {
//            WorkingDays workingDays = workingDaysRepository.findById( workingDaysId++ )
//                    .orElseThrow(() -> new IllegalStateException("Working day does not exist"));
//            OffDays offDays = offDaysRepository.findById( offDaysId++ )
//                    .orElseThrow(() -> new IllegalStateException("Working day does not exist"));
//            if(workingDays.getUser().equals( user ) ) {
//                if (workingDays.getHours() > 8) {
//                    appUserData.setRegularHours( 8 );
//                    appUserData.setOvertimeHours( workingDays.getHours() - 8 );
//                }
//
//                appUserData.setTotalHours( workingDays.getHours() );
//
//                if (workingDays.getDate().equals( offDays.getDate() ) || (workingDays.getDate().get( Calendar.DAY_OF_WEEK ) > 6
//                        && workingDays.getDate().equals( offDays.getDate() ))) {
//                    appUserData.setTotalPay(
//                            user.getHourlyPayment() * (appUserData.getRegularHours() * 1.5 +
//                                    appUserData.getOvertimeHours() * 2) );
//                } else if (workingDays.getDate().get( Calendar.DAY_OF_WEEK ) > 6) {
//                    appUserData.setTotalPay(
//                            user.getHourlyPayment() * (appUserData.getRegularHours() * 1.25 +
//                                    appUserData.getOvertimeHours() * 1.5) );
//                } else {
//                    appUserData.setTotalPay(
//                            user.getHourlyPayment() * (appUserData.getRegularHours() +
//                                    appUserData.getOvertimeHours()) );
//                }
//
//                totalPay += appUserData.getTotalPay();
//                totalOvertime += appUserData.getOvertimeHours();
//                totalRegular += appUserData.getRegularHours();
//            }
//        }
//        appUserData.setRegularHours( totalRegular );
//        appUserData.setTotalPay( totalPay );
//        appUserData.setOvertimeHours( totalOvertime );
//        appUserData.setTotalHours( totalOvertime + totalRegular );
//
//        return  appUserDataRepository.save(appUserData);
//
//    }






}
