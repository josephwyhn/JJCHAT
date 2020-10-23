package com.example.jjchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eBenutzername;
    private EditText ePasswort;
    private Button eLogin;
    private TextView eAttemptInfo;

    boolean isValid = false;

    private String Benutzername = "Admin";
    private String Passwort = "1234";
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eBenutzername = findViewById(R.id.etBenutzername);
        ePasswort = findViewById(R.id.etPasswort);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptInfo = findViewById(R.id.tvAttemptsInfo);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputBenutzername = eBenutzername.getText().toString();
                String inputPasswort = ePasswort.getText().toString();

                if (inputBenutzername.isEmpty() || inputPasswort.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Bitte die kompletten Informationen korrekt eingeben", Toast.LENGTH_SHORT).show();
                }else{

                    isValid = validate( inputBenutzername, inputPasswort);

                    if(!isValid){

                        counter--;
                        Toast.makeText(MainActivity.this, "Ups.. Der Benutzername oder das Passwort sind falsch", Toast.LENGTH_SHORT).show();

                        eAttemptInfo.setText("Login Versuche verbleibend: " + counter);

                        if (counter== 0){
                            eLogin.setEnabled(false);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Login erfolgreich", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity.this, HomePageAcitivity.class);
                            startActivity(intent);
                        }
                    }
                }

            }
        });
    }

    private boolean validate(String Benutzername, String Passwort) {
        if (Benutzername.equals(eBenutzername) && Passwort.equals(ePasswort))
        {
            return true;
    }
                return false;
    }
}