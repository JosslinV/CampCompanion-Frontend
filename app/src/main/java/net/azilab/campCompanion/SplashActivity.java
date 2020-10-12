package net.azilab.campCompanion;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);

        //Affichage de l'image du splash screen pendant 3 sec
        Thread mThread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //Check for token, or launch log screen
                    checkAuthentification();
                    finish();
                }
            }
        };
        mThread.start();
    }

    private void checkAuthentification() {
        //Vérifie si un token est présent sur le téléphone
        if(getSharedPreferences("applicationPref", MODE_PRIVATE).contains("token")) {
            Intent intent = new Intent(SplashActivity.this, MapActivity.class);
            startActivity(intent);
            //Sinon, demande à l'utilisateur de s'authentifier
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
