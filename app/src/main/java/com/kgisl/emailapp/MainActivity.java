package com.kgisl.emailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText fromAddressEditText, toAddressEditText,mailSubjectEditText ,mailMessageEditText,passwordEditText;
    private Button sendWithAppButton, sendWithAPIButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fromAddressEditText = (EditText)findViewById(R.id.from_address);
        toAddressEditText = (EditText)findViewById(R.id.to_address);
        mailSubjectEditText = (EditText)findViewById(R.id.mail_subject);
        mailMessageEditText = (EditText)findViewById(R.id.mail_message);
        passwordEditText = (EditText)findViewById(R.id.password);
        sendWithAppButton = (Button)findViewById(R.id.sendwithapp);
        sendWithAPIButton = (Button)findViewById(R.id.sendwithapi);

        sendWithAppButton.setOnClickListener(this);
        sendWithAPIButton.setOnClickListener(this);
    }
    public void sendEmailWithInstalledApp(){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        String[]  TO ={toAddressEditText.getText().toString()};
        String SUBJECT = mailSubjectEditText.getText().toString();
        String MESSAGE = mailMessageEditText.getText().toString();
        emailIntent.setData(Uri.parse("mailto"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
        emailIntent.putExtra(Intent.EXTRA_TEXT, MESSAGE);
        startActivity(Intent.createChooser(emailIntent,"send mail..."));
        finish();
    }

    public void sendEmailWithAPI(){
        MailTask mailTask = new MailTask();
        String FROM = fromAddressEditText.getText().toString();
        String TO = toAddressEditText.getText().toString();
        String SUBJECT = mailSubjectEditText.getText().toString();
        String MESSAGE = mailMessageEditText.getText().toString();
        String PASSWORD = passwordEditText.getText().toString();
        mailTask.execute(FROM,TO,SUBJECT,MESSAGE,PASSWORD);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sendwithapp){
            sendEmailWithInstalledApp();
        }
        else if(v.getId()==R.id.sendwithapi){
            sendEmailWithAPI();
        }
    }
}
