package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableSet;

public class MailWSClientMain {
    public static void main(String[] args) {
        MailWSClient.sendToGroup(
                ImmutableSet.of(new Addressee("To <trainingmailefimov@yandex.ru>")),
                ImmutableSet.of(new Addressee("Copy <trainingmailefimov@yandex.ru>")), "Subject", "Body");
    }
}