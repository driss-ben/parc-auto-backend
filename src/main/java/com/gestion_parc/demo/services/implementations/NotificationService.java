package com.gestion_parc.demo.services.implementations;

import com.gestion_parc.demo.beans.Notification;
import com.gestion_parc.demo.repositories.NotificationRepos;
import com.gestion_parc.demo.services.interfaces.INotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotification {
    @Autowired
    NotificationRepos notificationRepos;

    @Override
    public List<Notification> findAll() {
        return notificationRepos.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public Integer findNbrNotificationNotRead(){
        return notificationRepos.findByIsRead(false).size();
    }

    @Override
    public void setAllAsRead(){
        List<Notification> notifications = notificationRepos.findAll();
        for (Notification ntf : notifications ) {
            ntf.setRead(true);
            notificationRepos.save(ntf);
        }
    }
}
