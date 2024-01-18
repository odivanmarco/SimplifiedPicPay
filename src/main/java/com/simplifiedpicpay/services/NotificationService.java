package com.simplifiedpicpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.simplifiedpicpay.domain.user.User;
import com.simplifiedpicpay.dtos.NotificationDto;
import com.simplifiedpicpay.services.Interfaces.INotificationService;

@Service
public class NotificationService implements INotificationService{
    
    @Autowired
    private RestTemplate restTemplate;
    
    public void sendNotification(User user, String message) throws Exception{
        String email = user.getEmail();
        NotificationDto notificationRequest = new NotificationDto(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest, String.class);

        if((notificationResponse.getStatusCode() != HttpStatus.OK)){
            System.out.println("Erro ao enviar notifacação");
            throw new Exception("Serviço de notificação com problema");
        }
    }
}
