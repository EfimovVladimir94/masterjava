package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableSet;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class MailServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        Service service = Service.create(
                new URL("http://localhost:8080/mail/mailService?wsdl"),
                new QName("http://mail.javaops.ru/", "MailServiceImplService"));

        MailService mailService = service.getPort(MailService.class);
        mailService.sendToGroup(ImmutableSet.of(
                new Addressee("trainingmailefimov@yandex.ru", null)), null, "Subject", "Body");

        String state = mailService.sendToGroup(ImmutableSet.of(new Addressee("trainingmailefimov@yandex.ru",
                null)), null, "Group mail subject", "Group mail body");
        System.out.println("Group mail state" + state);

        GroupResult groupResult = mailService.sendBulk(ImmutableSet.of(new Addressee(" Efim VO <trainingmailefimov@yandex.ru>"),
                new Addressee("Bad Mail <bad_email.ru>")), "Bulk mail subject", "Bulk mail body");
        System.out.println("Group result + " + groupResult);

    }
}
