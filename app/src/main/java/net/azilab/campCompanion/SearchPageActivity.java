package net.azilab.campCompanion;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.azilab.campCompanion.maps.location.LocationProvider;
import net.azilab.campCompanion.model.SpotRequest;

public class SearchPageActivity extends AppCompatActivity {

    private RadioGroup locationGroup;

    private RadioButton aroundUser;
    private RadioButton aroundLocation;

    private SeekBar accessibilityBar;
    private SeekBar locationBar;
    private SeekBar utilitiesBar;
    private SeekBar privacyBar;

    private TextView locationSearch;

    private Button searchSpot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spot_research_view);

        this.locationGroup = findViewById(R.id.locationGroup);
        this.aroundUser = findViewById(R.id.aroundUser);
        this.aroundLocation = findViewById(R.id.aroundLocation);
        this.aroundUser.setChecked(true);

        this.accessibilityBar = findViewById(R.id.accessibilitySliderSearch);
        this.locationBar = findViewById(R.id.locationSliderSearch);
        this.utilitiesBar = findViewById(R.id.utilitiesSliderSearch);
        this.privacyBar = findViewById(R.id.privacySliderSearch);

        this.locationSearch = findViewById(R.id.location);
        updateTextViewVisibility(aroundLocation.isChecked());

        this.searchSpot = findViewById(R.id.searchButton);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setElementsState();
    }

    private void setElementsState() {
        this.searchSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchSpot();
            }
        });

        this.aroundLocation.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                updateTextViewVisibility(isChecked);
            }
        });
    }

    private void updateTextViewVisibility(boolean visible) {
        if(visible) {
            this.locationSearch.setVisibility(View.VISIBLE);
        } else {
            this.locationSearch.setVisibility(View.INVISIBLE);
        }
    }

    private void searchSpot() {
        try {
            final SpotRequest request = new SpotRequest();
            if (this.locationGroup.getCheckedRadioButtonId() == R.id.aroundUser) {
                Location location = LocationProvider.getPosition(this);

                request.setLocationLongitude(location.getLongitude());
                request.setLocationLatitude(location.getLatitude());

            } else if (this.locationGroup.getCheckedRadioButtonId() == R.id.aroundLocation) {
                Toast toast = Toast.makeText(this, "It is around a location !", Toast.LENGTH_SHORT);
                toast.show();

                Location location = LocationProvider.getPosition(this);

                request.setLocationLongitude(location.getLongitude());
                request.setLocationLatitude(location.getLatitude());
            }

            Intent intent = new Intent(this, ResultPageActivity.class);
            intent.putExtra("requestOptions", request);
            startActivity(intent);
        }
        catch (NullPointerException e) {
            Toast toast = Toast.makeText(this, "Location could not be determined.", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
