package Mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by NSD on 05.04.17.
 */
public class GmailAuthentificator  extends Authenticator {


        String user;
        String pw;
        public GmailAuthentificator(String username, String password)
        {
            super();
            this.user = username;
            this.pw = password;
        }
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(user, pw);
        }
    }

