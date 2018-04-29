/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfa.autoapp;

import com.lfa.autoapp.dao.SubscriberDAO;
import com.lfa.autoapp.dao.impl.SubscriberDAOImpl;
import com.lfa.autoapp.entity.Subscriber;
import com.lfa.autoapp.loader.SubscriberLoader;
import com.lfa.autoapp.util.Mailer;
import com.sun.mail.imap.IMAPFolder;
import java.util.Properties;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author USER
 */
public class Program {

    public static void main(String[] args) {
        try {
            IMAPFolder folder = null;
            Store store = null;
            String subject = null;
            Flag flag = null;
            try {
                Properties props = System.getProperties();
                props.setProperty("mail.store.protocol", "imaps");

                Session session = Session.getDefaultInstance(props, null);

                store = session.getStore("imaps");
                store.connect("imap.googlemail.com", "lfaimap@gmail.com", "admin1234!");

                //folder = (IMAPFolder) store.getFolder("[Gmail]/Inbox"); // This doesn't work for other email account
                folder = (IMAPFolder) store.getFolder("inbox"); //This works for both email account

                if (!folder.isOpen()) {
                    folder.open(Folder.READ_WRITE);
                }
                Message[] messages = folder.getMessages();
                System.out.println("No of Messages : " + folder.getMessageCount());
                System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
                System.out.println(messages.length);
                for (int i = 0; i < messages.length; i++) {

                    System.out.println("*****************************************************************************");
                    System.out.println("MESSAGE " + (i + 1) + ":");
                    Message msg = messages[i];
            //System.out.println(msg.getMessageNumber());
                    //Object String;
                    //System.out.println(folder.getUID(msg)

                    subject = msg.getSubject();

                    System.out.println("Subject: " + subject);
                    System.out.println("From: " + msg.getFrom()[0]);
                    System.out.println("To: " + msg.getAllRecipients()[0]);
                    System.out.println("Date: " + msg.getReceivedDate());
                    System.out.println("Size: " + msg.getSize());
                    System.out.println(msg.getFlags());
                    System.out.println("Body: \n" + msg.getContent());
                    System.out.println(msg.getContentType());

                }
            } finally {
                if (folder != null && folder.isOpen()) {
                    folder.close(true);
                }
                if (store != null) {
                    store.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    public void smtp(){
        SubscriberDAO subDAO=new SubscriberDAOImpl();
        SubscriberLoader loader=new SubscriberLoader(subDAO);
        loader.load("d:/training/subs.txt");
        
        Mailer mailer=new Mailer();
        mailer.setFrom("enquire@leapfrog.academy");
        mailer.setHost("smtp.vianet.com.np");
        mailer.setPort("25");
        String message="Hello Ke chha bro halkhabar :)";
        mailer.setBody(message);
        
        
        
        for(Subscriber subscriber:subDAO.getAll()){
            mailer.setSubject("Hello new mail sent "  + subscriber.getName());
            System.out.println("sending mail to " + subscriber.getEmail());
            mailer.setTo(subscriber.getEmail());
            mailer.send();
            
        }
        
    }
}
