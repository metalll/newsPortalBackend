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
                    instance = localInstance = new MailSender("pbezpeka.web.service@gmail.com","QazWsx321");
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
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

           props.put("mail.smtp.starttls.enable", "true");

           props.put("mail.smtp.user", username);
           props.put("mail.smtp.password", password);

           props.put("mail.debug", "true");


        }

        public void send(String subject, String text, String fromEmail, String toEmail){
            Session session = Session.getInstance(props, new GmailAuthentificator(username,password));

            try {
                Message message = new MimeMessage(session);
                //от кого
                message.setFrom(new InternetAddress(this.username));
                //кому
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                //Заголовок письма
                message.setSubject(subject);
                //Содержимое
                message.setText(text);

                //Отправляем сообщение

                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", this.username, this.password);
                message.saveChanges();
                Transport.send(message);
                transport.close();



            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }



