package com.hostalmanagement.Web.Application.util;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static java.awt.SystemColor.text;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendUserCredentials(String to,
                                    String username,
                                    EmailTemplateName emailTemplate,
                                    String conformationUrl,
                                    String activationCode,
                                    String subject) throws MessagingException {

        String templateName;

        if(emailTemplate == null){
            templateName = "confirm-email";
        }else {
            templateName = emailTemplate.name();
        }

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );

        Map<String ,Object> properties = new HashMap<>();

        properties.put("username",username);
        properties.put("confirmationUrl",conformationUrl);
        properties.put("activation_code",activationCode);


        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("usernamex1995@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        String template = templateEngine.process(templateName, context);

        helper.setText(template,true);


        emailSender.send(mimeMessage);

    }

    @Async
    public void sendSystemUserCredentials(String to, String subject, String username, String password) throws MessagingException {

        System.out.println("Sending email to: " + to + " | Subject: " + subject);

        // Create a MimeMessage
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("usernamex1995@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        // HTML content with inline CSS
        String htmlContent = "<html>"
                + "<body style='font-family: Arial, sans-serif;'>"
                + "<div style='border: 1px solid #ddd; padding: 20px; max-width: 600px; margin: 0 auto;'>"
                + "<h2 style='color: #4CAF50;'>Welcome to Hostal Management System</h2>"
                + "<p>Dear User,</p>"
                + "<p>We're excited to have you on board! Here are your login credentials:</p>"
                + "<div style='background-color: #f4f4f4; padding: 10px; margin: 20px 0;'>"
                + "<p><strong>User name:</strong> " + username + "</p>"
                + "<p><strong>Password:</strong> " + password + "</p>"
                + "</div>"
                + "<p>Please keep these credentials secure and do not share them with anyone.</p>"
                + "<p>Best regards,<br>Hostal Management System FOT UOR</p>"
                + "</div>"
                + "</body>"
                + "</html>";

        // Set the HTML content
        helper.setText(htmlContent, true); // Pass true to indicate HTML content

        // Send the email
        emailSender.send(message);
        System.out.println("Email sent successfully to " + to);
    }
}