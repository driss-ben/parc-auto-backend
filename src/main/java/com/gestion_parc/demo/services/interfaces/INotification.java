package com.gestion_parc.demo.services.interfaces;

import com.gestion_parc.demo.beans.Notification;

import java.util.List;

public interface INotification {
    List<Notification> findAll();

    Integer findNbrNotificationNotRead();

    void setAllAsRead();
}
