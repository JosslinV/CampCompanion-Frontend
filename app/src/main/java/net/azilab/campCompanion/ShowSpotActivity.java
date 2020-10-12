package net.azilab.campCompanion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.model.LatLng;

import net.azilab.campCompanion.maps.MapFragment;
import net.azilab.campCompanion.maps.MapHelper;

public class ShowSpotActivity extends AppCompatActivity {

    private static MapHelper mapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_map_view);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.mapHelper = new MapHelper(ShowSpotActivity.this, (MapFragment) fragmentManager.findFragmentById(R.id.maps));
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Zoom sur les coordonnées du spot
        Double latitude = this.getIntent().getDoubleExtra("spotLat", 0.0);
        Double longitude = this.getIntent().getDoubleExtra("spotLon", 0.0);

        LatLng position = new LatLng(latitude, longitude);
        mapHelper.initMapWithPosition(position);
    }
}