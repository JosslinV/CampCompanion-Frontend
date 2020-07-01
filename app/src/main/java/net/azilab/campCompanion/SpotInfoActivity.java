package net.azilab.campCompanion;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import net.azilab.campCompanion.maps.MapFragment;
import net.azilab.campCompanion.maps.MapHelper;
import net.azilab.campCompanion.model.Spot;

public class SpotInfoActivity extends AppCompatActivity {

    private Spot spot;

    private TextView accessibilityNote;
    private TextView locationNote;
    private TextView utilitiesNote;
    private TextView privacyNote;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.spot = (Spot) getIntent().getSerializableExtra("spot");
        setContentView(R.layout.spot_information_view);

        this.accessibilityNote = findViewById(R.id.accessibilityDisplay);
        this.locationNote = findViewById(R.id.locationDisplay);
        this.utilitiesNote = findViewById(R.id.utilitiesDisplay);
        this.privacyNote = findViewById(R.id.privacyDisplay);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportActionBar().setTitle(spot.getName());

        this.accessibilityNote.setText(String.valueOf(spot.getAccessibilityNote()));
        this.locationNote.setText(String.valueOf(spot.getLocationNote()));
        this.utilitiesNote.setText(String.valueOf(spot.getUtilitiesNote()));
        this.privacyNote.setText(String.valueOf(spot.getPrivacyNote()));
    }
}
