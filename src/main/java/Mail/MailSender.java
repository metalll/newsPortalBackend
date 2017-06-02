package Mail;

import DBControllers.DBBanners;
import com.j256.ormlite.dao.Dao;
import model.Banners;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by NSD on 05.04.17.
 */
public class MailSender {





    private static volatile MailSender instance;

//    private Dao<Banners,String> dao;



    public static MailSender getInstance() {
        MailSender localInstance = instance;
        if (localInstance == null) {
            synchronized (DBBanners.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MailSender("pbezpekaweb.service@mail.ru","QazWsx321");
                }
            }
        }
        return localInstance;
    }




    private String username;
        private String password;
        private Properties props;

       private MailSender(String username, String password) {
            this.username = username;
            this.password = password;
           props = new Properties();
           props.setProperty("mail.debug", "true");
           props.setProperty("javax.net.ssl.debug", "all");
           props.setProperty("mail.smtp.host", "smtp.mail.ru");
           props.setProperty("mail.smtp.port", "465");
           props.setProperty("mail.smtp.auth", "true");
           props.setProperty("mail.smtp.connectiontimeout", "60000");
           props.setProperty("mail.smtp.timeout", "60000");
           props.setProperty("mail.transport.protocol", "smtps");
           props.setProperty("mail.smtp.socketFactory.port", "465");
           props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
           props.setProperty("mail.smtp.socketFactory.fallback", "false");
           props.setProperty("mail.smtp.ssl.enable", "true");
           props.setProperty("mail.mime.charset", "UTF-8");
           props.setProperty("java.security.debug", "all");
           props.setProperty("logging.properties", "all");


        }

        public void send(String subject, String text, String fromEmail, String toEmail){
            Session session = Session.getInstance(props, new GmailAuthentificator(username,password));

            try {


                MimeMessage message = new MimeMessage(session);
                message.setSubject(subject);
                message.setText(text);
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); message.setSender(new InternetAddress("naumen.customs@mail.ru"));
                Transport.send(message);

                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }



