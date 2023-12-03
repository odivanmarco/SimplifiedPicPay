package com.simplifiedpicpay.services.Interfaces;

import com.simplifiedpicpay.domain.user.User;

public interface INotificationService {
    public void sendNotification(User user, String message) throws Exception;
}
