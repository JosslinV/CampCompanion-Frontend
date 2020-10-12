package net.azilab.campCompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.azilab.campCompanion.backendCommunicator.LogRequester;
import net.azilab.campCompanion.backendCommunicator.RequestCallback;
import net.azilab.campCompanion.model.Log;
import net.azilab.campCompanion.model.Spot;

import org.json.JSONException;

import okhttp3.Response;

public class AddLogActivity extends AppCompatActivity {

    private Spot relatedSpot;

    private RatingBar spotNote;
    private TextView logTxt;

    private Button sendLog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        relatedSpot = (Spot) getIntent().getSerializableExtra("spot");
        setContentView(R.layout.add_log_view);

        this.spotNote = findViewById(R.id.spotNote);
        this.logTxt = findViewById(R.id.logTxt);
        this.sendLog = findViewById(R.id.sendLog);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportActionBar().setTitle("Ajouter un log:" + relatedSpot.getName());

        this.sendLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Vérifie que le Log contient un texte
                if(!logTxt.getText().toString().equals("")) {
                    Log logToAdd = new Log();
                    logToAdd.setComment(logTxt.getText().toString());
                    logToAdd.setNote((int)spotNote.getRating());
                    logToAdd.setRelatedSpot(relatedSpot);


                    LogRequester.sendLog(logToAdd, AddLogActivity.this, new RequestCallback<Response>() {
                        @Override
                        public void onDataReceived(Response response) throws JSONException {
                            Toast toast = Toast.makeText(AddLogActivity.this, "Log ajouté avec succès", Toast.LENGTH_SHORT);
                            toast.show();
                            finish();
                        }
                    });

                } else {
                    Toast toast = Toast.makeText(AddLogActivity.this, "Veuillez saisir votre texte", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

}
