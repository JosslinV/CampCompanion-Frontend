package net.azilab.campCompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.azilab.campCompanion.model.Spot;

public class SpotInfoActivity extends AppCompatActivity {

    private static Spot spot;

    private TextView accessibilityNote;
    private TextView locationNote;
    private TextView utilitiesNote;
    private TextView privacyNote;

    private Button displayOnMap;
    private Button addLog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spot = (Spot) getIntent().getSerializableExtra("spot");
        setContentView(R.layout.spot_information_view);

        this.accessibilityNote = findViewById(R.id.accessibilityDisplay);
        this.locationNote = findViewById(R.id.locationDisplay);
        this.utilitiesNote = findViewById(R.id.utilitiesDisplay);
        this.privacyNote = findViewById(R.id.privacyDisplay);

        this.displayOnMap = findViewById(R.id.displayMapButton);
        this.addLog = findViewById(R.id.addLog);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportActionBar().setTitle(spot.getName());

        this.accessibilityNote.setText(String.valueOf(spot.getAccessibilityNote()));
        this.locationNote.setText(String.valueOf(spot.getLocationNote()));
        this.utilitiesNote.setText(String.valueOf(spot.getUtilitiesNote()));
        this.privacyNote.setText(String.valueOf(spot.getPrivacyNote()));

        this.displayOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpotInfoActivity.this, ShowSpotActivity.class);
                intent.putExtra("spotLat", spot.getLatitude());
                intent.putExtra("spotLon", spot.getLongitude());
                startActivity(intent);
            }
        });

        this.addLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpotInfoActivity.this, AddLogActivity.class);
                intent.putExtra("spot", spot);
                startActivity(intent);
            }
        });
    }
}
