package Mail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas
 */
import Controller.Controle;
import Model.ClientesBEAN;
import Model.ClientesDAO;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class JavaMailApp
{
      public void email(int num) {
            Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
 
            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("prismasoftgc@gmail.com", "prisma1234");
                             }
                        });
 
            /** Ativa Debug para sessão */
            session.setDebug(true);
 
            try {
                  ClientesBEAN cliente = new Controle().findClienteCodigo(num);
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("prismasoftgc@gmail.com")); //Remetente
                   Address[] toUser = InternetAddress.parse(cliente.getEmail());
                    
                 
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("Emprestimo realizado");//Assunto
                  message.setText("***** Colocar os dados do emprestimo *********");
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
 
                  System.out.println("Feito!!!");
 
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      }
}
