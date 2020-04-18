package com.kgisl.emailapp;

import android.os.AsyncTask;
import android.util.Log;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTask extends AsyncTask<String,String,String> {
    private Session session;
    @Override
    protected String doInBackground(String... maildetail) {

        final String FROM = maildetail[0];
        String TO = maildetail[1];
        String SUBJECT = maildetail[2];
        String MESSAGE = maildetail[3];
        final String PASSWORD = maildetail[4];

        Log.d("maillog",FROM);
        Log.d("maillog",TO);
        Log.d("maillog",SUBJECT);
        Log.d("maillog",MESSAGE);
        //Log.d("maillog",PASSWORD);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Log.d("maillog","properties");
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                Log.d("maillog","Auth In");
                return new PasswordAuthentication(FROM, PASSWORD);

            }
        });
        Log.d("maillog","Auth OK");
        session.setDebug(true);
        try{
            Log.d("maillog","Mime In");
            MimeMessage mimeMessage = new MimeMessage(session);
            Log.d("maillog","Mime Session");
            mimeMessage.setFrom(new InternetAddress(FROM));
            Log.d("maillog","Mime from");
            mimeMessage.addRecipient(MimeMessage.RecipientType.TO,new InternetAddress(TO));
            Log.d("maillog","Mime resp");
            mimeMessage.setSubject(SUBJECT);
            Log.d("maillog","Mime subject");
            mimeMessage.setText(MESSAGE);
            Log.d("maillog","Mime message");
            Log.d("maillog","Mime Set Ok");
            Transport.send(mimeMessage);
            Log.d("maillog","mailsent");
        }
        catch (MessagingException me){
            me.printStackTrace();
            Log.d("maillog","mailnotsent");
        }
        return "Done";
    }
}
