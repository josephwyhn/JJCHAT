package com.example.jjchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText eBenutzername;
    private EditText ePasswort;
    private Button eLogin;
    private TextView eAttemptInfo;

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
            public void onClick(View v){
                String inputname = eBenutzername.getText().toString();
            }
        });
    }
}