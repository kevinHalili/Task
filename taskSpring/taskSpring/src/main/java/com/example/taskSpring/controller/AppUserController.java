package com.example.taskSpring.controller;


import com.example.taskSpring.model.AppUser;

import com.example.taskSpring.model.AppUserData;
import com.example.taskSpring.model.OffDays;
import com.example.taskSpring.model.WorkingDays;
import com.example.taskSpring.service.AppUserDataService;
import com.example.taskSpring.service.AppUserService;
import com.example.taskSpring.service.OffDaysService;
import com.example.taskSpring.service.WorkingDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class AppUserController {

    private final AppUserService appUserService;
    private final WorkingDaysService workingDaysService;

    private final OffDaysService offDaysService;
    private final AppUserDataService appUserDataService;

    @Autowired
    public AppUserController(AppUserService appUserService, WorkingDaysService workingDaysService, OffDaysService offDaysService, AppUserDataService appUserDataService) {
        this.appUserService = appUserService;
        this.workingDaysService = workingDaysService;
        this.offDaysService = offDaysService;
        this.appUserDataService = appUserDataService;
    }


    @GetMapping("/all")
    public List<AppUser> getAllUsers()
    {
       return appUserService.getAllUsers();
    }

    @GetMapping("/workingDays")
    public List<WorkingDays> getAllWorkingDays()
    {
        return workingDaysService.getAllWorkingDays();
    }


    @GetMapping("/offDays")
    public List<OffDays> getAllOffDays()
    {
        return offDaysService.getAllOffDays();
    }

//    @PostMapping("setData")
//    public AppUserData setUserData(@RequestParam Long userId)
//    {
//       return appUserDataService.setUserData( userId);
//    }
//
    @GetMapping("/data")
    public HashMap<Long,AppUserData> getUserData ()
    {
        return appUserDataService.getUserData();
    }





}
