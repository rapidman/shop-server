package com.ajax.shop.service;

import com.ajax.shop.data.UserOrderData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static com.ajax.shop.utils.EmailUtils.*;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 13.08.18
 */
@Service
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private static final String FROM = "rapidman.stv@gmail.com";
    private static final String ORDER_SUBJECT = "Ваш заказ";
    @Value("${mail.smtp.host}")
    private String mailSmtpHost;
    @Value("${mail.smtp.port}")
    private String mailSmtpPort;
    @Value("${mail.smtp.user}")
    private String mailSmtpUser;
    @Value("${mail.smtp.password}")
    private String mailSmtpPassword;
    @Value("${mail.smtp.auth}")
    private String mailSmtpAuth;

    @Autowired
    private TemplateService templateService;

    public void sendEmail(UserOrderData userOrderData) {

        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", mailSmtpHost);
            properties.put("mail.smtp.port", mailSmtpPort);
            properties.put("mail.smtp.socketFactory.port", mailSmtpPort);
            properties.put("mail.smtp.auth", mailSmtpAuth);
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.mime.address.strict", "false");
            //for testing
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailSmtpUser, mailSmtpPassword);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            String to = toPunycode(userOrderData.getEmail()) + "," + FROM;
            InternetAddress[] addresses = InternetAddress.parseHeader(to, false);
            message.setRecipients(Message.RecipientType.TO, addresses);

            message = applyTemplate(message, userOrderData);

            Transport.send(message, mailSmtpUser, mailSmtpPassword);
        } catch (Exception e) {
            log.error("Error while send email", e);
        }
    }

    MimeMessage applyTemplate(MimeMessage message, UserOrderData userOrderData) throws Exception {
        String content = templateService.processEmailOrderTemplate(userOrderData);
        String subject = ORDER_SUBJECT;
        message.setContent(content, MAIL_CONTENT_TYPE);
        message.setSubject(subject, MAIL_ENCODING);
        log.info("message <{}> sent to {}", content, userOrderData.getEmail());
        return message;
    }
}
