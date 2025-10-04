package com.smartspend.service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;

public class EmailService {

    public void sendEmail(String username, String filePath) {
        final String senderEmail = "your-email@gmail.com";
        final String senderPassword = "your-app-password";  // Use App Password for Gmail

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username + "@gmail.com"));
            message.setSubject("SmartSpend Monthly Report");

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hi " + username + ",\n\nYour monthly financial report is attached.");

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(new File(filePath));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachment);

            message.setContent(multipart);
            Transport.send(message);

            System.out.println("✅ Email sent to " + username + "@gmail.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
