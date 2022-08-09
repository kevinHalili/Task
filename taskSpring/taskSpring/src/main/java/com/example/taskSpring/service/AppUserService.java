package com.example.taskSpring.service;


import com.example.taskSpring.model.AppUser;
import com.example.taskSpring.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {


    private final AppUserRepository appUserRepository;
    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAllUsers()
    {
        return appUserRepository.findAll();
    }









}
