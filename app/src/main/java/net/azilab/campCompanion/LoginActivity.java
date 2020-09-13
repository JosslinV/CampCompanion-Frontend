package net.azilab.campCompanion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.azilab.campCompanion.backendCommunicator.AuthenticatorRequester;
import net.azilab.campCompanion.backendCommunicator.RequestCallback;
import net.azilab.campCompanion.model.Credential;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView errorMsg;
    private TextView username;
    private TextView password;
    private Button connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.authentication_view);

        this.errorMsg = findViewById(R.id.errorTxt);
        this.username = findViewById(R.id.usernameTxt);
        this.password = findViewById(R.id.passwordTxt);
        this.connection = findViewById(R.id.connectionBtn);
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText() != null && password.getText() != null) {
                    authenticate();
                }
            }
        });
    }

    private void authenticate() {
        Credential credential = new Credential(username.getText().toString(), password.getText().toString());

        AuthenticatorRequester.requestAuthentication(credential, LoginActivity.this, new RequestCallback<Response>() {
            @Override
            public void onDataReceived(Response response) throws JSONException {
                if(response.code() == 200) {
                    try {
                        String token = response.body().string();
                        SharedPreferences preferences = getSharedPreferences("applicationPref", MODE_PRIVATE);
                        preferences.edit().putString("token", token).commit();

                        Intent intent = new Intent(LoginActivity.this, MapActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (response.code() == 401) {
                    errorMsg.setText("Nom d'utilisateur ou mot de passe incorrect");
                } else {
                    errorMsg.setText("Erreur serveur");
                }
            }
        });
    }
}
