package net.azilab.campCompanion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.azilab.campCompanion.backendCommunicator.RequestCallback;
import net.azilab.campCompanion.backendCommunicator.Requester;
import net.azilab.campCompanion.model.Spot;

import org.json.JSONArray;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_spot_view);

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

        this.addSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSpot();
            }
        });
    }

    private void addSpot() {
        Spot newSpot = new Spot();
        newSpot.setName(nameTxt.getText().toString());
        newSpot.setLatitude(Double.parseDouble(latInput.getText().toString()));
        newSpot.setLongitude(Double.parseDouble(longInput.getText().toString()));

        newSpot.setAccessibilityNote(accessibilityBar.getProgress() + 1);
        newSpot.setLocationNote(locationBar.getProgress() + 1);
        newSpot.setUtilitiesNote(utilitiesBar.getProgress() + 1);
        newSpot.setPrivacyNote(privacyBar.getProgress() + 1);

        Requester.sendSpot(newSpot, AddSpotActivity.this, new RequestCallback<Response>() {
            @Override
            public void onDataReceived(Response response) throws JSONException {
                Toast toast = Toast.makeText(AddSpotActivity.this, "Spot added with success", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });
    }
}
