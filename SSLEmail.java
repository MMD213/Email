package org.example;



import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SSLEmail {

    /**
     * Outgoing Mail (SMTP) Server
     * requires TLS or SSL: smtp.gmail.com (use authentication)
     * Use Authentication: Yes
     * Port for SSL: 465
     */
    public static void main(String[] args) {
        Random rd = new Random();
        Scanner in = new Scanner(System.in);
        int z = rd.nextInt(9999) + 1000;

        String b = String.valueOf(z);
        System.out.println("Введите email");
        String email = in.nextLine();


        final String fromEmail = "mmd.05@yandex.ru"; //requires valid gmail id
        final String password = "vpjvwbipiushkysw"; // correct password for gmail id
        final String toEmail = email; // can be any email id

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
        EmailUtil.sendEmail(session, toEmail, "Authenticator", b);
        System.out.println();
        while (true){
            System.out.println("Введите код");
            int numb1 = in.nextInt();
            if (numb1 == z) {
                Session session2 = Session.getDefaultInstance(props, auth);
                System.out.println("Session created");
                EmailUtil.sendEmail(session, toEmail, "Authenticator", "Rgistration is confirmed");
                break;
        }
            else{
                System.out.println("Неверный код");
            }
        }
    }
}
