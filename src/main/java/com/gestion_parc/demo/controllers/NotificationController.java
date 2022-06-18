package com.gestion_parc.demo.controllers;

import com.gestion_parc.demo.beans.Notification;
import com.gestion_parc.demo.services.implementations.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:3000")
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("")
    public List<Notification> getAll(){
        return notificationService.findAll();
    }

    @GetMapping("/not-read")
    public Integer getAllNotRead(){
        return notificationService.findNbrNotificationNotRead();
    }

    @GetMapping("/read")
    public void setAllAsRead(){
        notificationService.setAllAsRead();
    }
}
