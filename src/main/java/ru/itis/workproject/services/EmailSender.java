package ru.itis.workproject.services;

public interface EmailSender {
    void sendNotificationAboutRegistration(String login);
    void sendLinkToUploadedFile();
}
