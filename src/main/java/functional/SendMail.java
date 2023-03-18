package functional;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Properties;

public class SendMail {

	// email : greenhorse40@gmail.com
	// password : GreenHorse123456789
	// application password : vkbhbpurqathieyu
	
    private static String userName="greenhorse40@gmail.com";
    private static String passWord="vkbhbpurqathieyu";

    public static void send(String recepient, String subject, String text){
        //Etape 5.1 Creation de la session :
        Properties prop=new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true");

        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, passWord);
                    }
                });

        //Etape 5.2 La creation d'un message :
        Message message= new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
            message.setSubject(subject);
            message.setText(text);


            //Etape 5.3 Envoie de message :
            Transport.send(message);
//            System.out.println("Message Envoiee avec succee.");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
