package com.example.jjchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjchatapi.JJChatAPIController;
import com.example.jjchatapi.model.GenericResult;
import com.example.jjchatapi.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText eBenutzername;
    private EditText ePasswort;

    private Button btnLogin;
    private Button btnRegister;

    private JJChatAPIController _apiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Link dynamisch ermitteln
        _apiController = new JJChatAPIController("https://2e90eeb28798.ngrok.io/api");

        eBenutzername = findViewById(R.id.etUsername);
        ePasswort = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    public void btnLoginClicked(View v) {
        String usernameInput = eBenutzername.getText().toString();
        String passwordInput = ePasswort.getText().toString();

        if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
            toastMessage("Bitte Passwort und Benutzername angeben!", Toast.LENGTH_SHORT);
        } else {
            //new DoLoginTask().execute(usernameInput, passwordInput);
            Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
            startActivity(intent);
        }
    }

    public void btnRegisterClicked(View v) {
        String usernameInput = eBenutzername.getText().toString();
        String passwordInput = ePasswort.getText().toString();

        if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
            toastMessage("Bitte Passwort und Benutzername zum Registrieren angeben!", Toast.LENGTH_SHORT);
        } else {
            new DoRegisterTask().execute(usernameInput, passwordInput);
        }
    }

    //eAttemptInfo.setText("Login Versuche verbleibend: " + counter);
    //                            Intent intent = new Intent(MainActivity.this, HomePageAcitivity.class);
    //                            startActivity(intent);

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                btnLoginClicked(v);
                break;
            case R.id.btnRegister:
                btnRegisterClicked(v);
                break;
            default:
                toastMessage("", Toast.LENGTH_SHORT);
        }
    }

    private void toastMessage(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }

    private void setButtonsEnabled(boolean isEnabled){
        btnLogin.setEnabled(isEnabled);
        btnRegister.setEnabled(isEnabled);
    }

    private class DoLoginTask extends AsyncTask<String, Void, GenericResult<User, Exception>> {

        @Override
        protected void onPreExecute() {
            setButtonsEnabled(false);
        }

        @Override
        protected GenericResult<User, Exception> doInBackground(String... strings) {
            GenericResult<User, Exception> result = new GenericResult<>();
            result.setEntity1(null);
            result.setEntity2(null);

            try {
                result.setEntity1(_apiController.login(strings[0], strings[1]));
            } catch (Exception e) {
                result.setEntity2(e);
            }

            return result;
        }

        @Override
        protected void onPostExecute(GenericResult<User, Exception> genericResult) {
            User u = genericResult.getEntity1();
            Exception e = genericResult.getEntity2();

            //TODO: Intent & SQLite saving
            if (e != null) {
                toastMessage("Error: " + e.getMessage(), Toast.LENGTH_SHORT);
            } else if (u != null) {
                toastMessage(u.getUsername() + " - " + u.getPassword(), Toast.LENGTH_LONG);
            } else {
                toastMessage("Unexpected result", Toast.LENGTH_SHORT);
            }

            setButtonsEnabled(true);
        }
    }

    private class DoRegisterTask extends AsyncTask<String, Void, GenericResult<User, Exception>> {

        @Override
        protected void onPreExecute() {
            setButtonsEnabled(false);
        }

        @Override
        protected GenericResult<User, Exception> doInBackground(String... strings) {
            GenericResult<User, Exception> result = new GenericResult<>();
            result.setEntity1(null);
            result.setEntity2(null);

            try {
                result.setEntity1(_apiController.register(strings[0], strings[1]));
            } catch (Exception e) {
                result.setEntity2(e);
            }

            return result;
        }

        @Override
        protected void onPostExecute(GenericResult<User, Exception> genericResult) {
            User u = genericResult.getEntity1();
            Exception e = genericResult.getEntity2();

            //TODO: Intent & SQLite saving
            if (e != null) {
                toastMessage("Error: " + e.getMessage(), Toast.LENGTH_SHORT);
            } else if (u != null) {
                toastMessage(u.getUsername() + " - " + u.getPassword(), Toast.LENGTH_LONG);
            } else {
                toastMessage("Unexpected result", Toast.LENGTH_SHORT);
            }

            setButtonsEnabled(true);
        }
    }
}