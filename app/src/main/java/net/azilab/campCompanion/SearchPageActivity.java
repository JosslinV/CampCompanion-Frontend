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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import net.azilab.campCompanion.maps.location.LocationProvider;
import net.azilab.campCompanion.model.SpotRequest;

import java.util.Arrays;

public class SearchPageActivity extends AppCompatActivity {

    private RadioGroup locationGroup;

    private RadioButton aroundUser;
    private RadioButton aroundLocation;

    private SeekBar accessibilityBar;
    private SeekBar locationBar;
    private SeekBar utilitiesBar;
    private SeekBar privacyBar;

    private AutocompleteSupportFragment autocompleteFragment;
    private static Place selectedPlace;

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
        this.autocompleteFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        initPlaceFinder();

        this.searchSpot = findViewById(R.id.searchButton);
    }

    private void initPlaceFinder() {
        // updateTextViewVisibility(aroundLocation.isChecked());
        String apiKey = getString(R.string.api_key);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        this.autocompleteFragment.setTypeFilter(TypeFilter.CITIES);
        this.autocompleteFragment.setCountry("FR");
        this.autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG));

        this.autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                System.out.println(place);
                selectedPlace = place;
            }

            @Override
            public void onError(@NonNull Status status) {
                System.out.println(status);
            }
        });
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
    }

    private void searchSpot() {
        try {
            final SpotRequest request = new SpotRequest();
            if (this.locationGroup.getCheckedRadioButtonId() == R.id.aroundUser) {
                Location location = LocationProvider.getPosition(this);

                request.setLocationLongitude(location.getLongitude());
                request.setLocationLatitude(location.getLatitude());

            } else if (this.locationGroup.getCheckedRadioButtonId() == R.id.aroundLocation) {
                LatLng placeLocation = selectedPlace.getLatLng();
                System.out.println(placeLocation);
                request.setLocationLongitude(placeLocation.longitude);
                request.setLocationLatitude(placeLocation.latitude);
            }

            request.setAccessibilityNote(this.accessibilityBar.getProgress());
            request.setLocationNote(this.locationBar.getProgress());
            request.setUtilitiesNote(this.utilitiesBar.getProgress());
            request.setPrivacyNote(this.privacyBar.getProgress());

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
