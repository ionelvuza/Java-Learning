
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class EmailReader {

    public static void main(String args[]) {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
                Session session = Session.getDefaultInstance(props, null);
                Store store = session.getStore("imaps");

                // IMAP host for gmail. 
                // Replace <username> with the valid username of your Email ID.
                // Replace <password> with a valid password of your Email ID.

                //store.connect("imap.gmail.com", "<username>", "<password>");

                // IMAP host for yahoo.
                store.connect("imap.mail.yahoo.com", "user", "password");
                System.out.println(store);

                // Open inbox
                Folder inbox = store.getFolder("Inbox");
                inbox.open(Folder.READ_ONLY);
                
                BufferedReader optionReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Press (U) to get only unread mails OR Press (A) to get all mails:");
                try {
                    char answer = (char) optionReader.read();
                    if(answer=='A' || answer=='a'){
                        showAllMails(inbox);
                    }else if(answer=='U' || answer=='u'){
                        showUnreadMails(inbox);
                    }
                    optionReader.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
                
        } catch (NoSuchProviderException e) {
            System.out.println(e.toString());
            System.exit(1);
        } catch (MessagingException e) {
            System.out.println(e.toString());
            System.exit(2);
        }

    }
    
    static public void showUnreadMails(Folder inbox){        
        try {
            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            Message msg[] = inbox.search(ft);
            System.out.println("MAILS: "+msg.length);
            for(Message message:msg) {
                try {
                    System.out.println("DATE: "+message.getSentDate().toString());
                    System.out.println("FROM: "+message.getFrom()[0].toString());            
                    System.out.println("SUBJECT: "+message.getSubject().toString());
                  //System.out.println("CONTENT: "+message.getContent().toString());
                    System.out.println("******************************************");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    System.out.println("No Information");
                }
            }
        } catch (MessagingException e) {
            System.out.println(e.toString());
        }
    }
    
    static public void showAllMails(Folder inbox){
        try {
            Message msg[] = inbox.getMessages();
            System.out.println("MAILS: "+msg.length);
            for(Message message:msg) {
                try {
                    System.out.println("DATE: "+message.getSentDate().toString());
                    System.out.println("FROM: "+message.getFrom()[0].toString());            
                    System.out.println("SUBJECT: "+message.getSubject().toString());
                  //System.out.println("CONTENT: "+message.getContent().toString());
                    System.out.println("******************************************");
                } catch (Exception e) {
                    System.out.println("No Information");
                }
            }
        } catch (MessagingException e) {
            System.out.println(e.toString());
        }
    }

}