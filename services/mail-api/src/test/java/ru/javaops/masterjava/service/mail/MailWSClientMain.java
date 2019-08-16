package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import ru.javaops.web.WebStateException;

@Slf4j
public class MailWSClientMain {
    public static void main(String[] args) throws WebStateException {
        String state = MailWSClient.sendToGroup(
                ImmutableSet.of(new Addressee("To <trainingmailefimov@yandex.ru>")),
                ImmutableSet.of(new Addressee("Copy <trainingmailefimov@yandex.ru>")), "Subject", "Body");
        System.out.println(state);
    }
}