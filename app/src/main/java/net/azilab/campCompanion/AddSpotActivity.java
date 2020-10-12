package net.azilab.campCompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.azilab.campCompanion.backendCommunicator.RequestCallback;
import net.azilab.campCompanion.backendCommunicator.SpotRequester;
import net.azilab.campCompanion.model.Spot;

import org.json.JSONException;

import okhttp3.Response;

public class AddSpotActivity extends AppCompatActivity {

    private TextView nameTxt;
    private TextView latInput;
    private TextView longInput;

    private SeekBar accessibilityBar;
    private SeekBar locationBar;
    private SeekBar utilitiesBar;
    private SeekBar privacyBar;

    private Button addSpot;

    private Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_spot_view);

        this.myIntent = getIntent();

        this.nameTxt = findViewById(R.id.spotName);
        this.latInput = findViewById(R.id.spotLat);
        this.longInput = findViewById(R.id.spotLong);

        this.accessibilityBar = findViewById(R.id.accessibilitySlider);
        this.locationBar = findViewById(R.id.locationSlider);
        this.utilitiesBar = findViewById(R.id.utilitiesSlider);
        this.privacyBar = findViewById(R.id.privacySlider);

        this.addSpot = findViewById(R.id.addButton);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(myIntent.hasExtra("selectedLatitude") && myIntent.hasExtra("selectedLatitude")) {
            Double latitude = myIntent.getDoubleExtra("selectedLatitude",0.0);
            Double longitude = myIntent.getDoubleExtra("selectedLongitude",0.0);

            this.latInput.setText(String.valueOf(latitude));
            this.longInput.setText(String.valueOf(longitude));
        }

        this.addSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSpot();
            }
        });
    }

    private void addSpot() {
        //Vérifie que le spot possède au moins de son nom et des coordonnées
        if(!nameTxt.getText().toString().equals("") && !latInput.getText().toString().equals("") &&  !longInput.getText().toString().equals("")) {
            Spot newSpot = new Spot();
            newSpot.setName(nameTxt.getText().toString());
            newSpot.setLatitude(Double.parseDouble(latInput.getText().toString()));
            newSpot.setLongitude(Double.parseDouble(longInput.getText().toString()));

            newSpot.setAccessibilityNote(accessibilityBar.getProgress() + 1);
            newSpot.setLocationNote(locationBar.getProgress() + 1);
            newSpot.setUtilitiesNote(utilitiesBar.getProgress() + 1);
            newSpot.setPrivacyNote(privacyBar.getProgress() + 1);

            SpotRequester.sendSpot(newSpot, AddSpotActivity.this, new RequestCallback<Response>() {
                @Override
                public void onDataReceived(Response response) throws JSONException {
                    Toast toast = Toast.makeText(AddSpotActivity.this, "Spot added with success", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }
            });
        } else {
            Toast toast = Toast.makeText(AddSpotActivity.this, "Veuillez saisir un nom et les coordonnées du spot", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
