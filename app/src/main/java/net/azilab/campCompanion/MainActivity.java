package net.azilab.campCompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.azilab.campCompanion.maps.MapFragment;
import net.azilab.campCompanion.maps.MapHelper;

public class MainActivity extends AppCompatActivity {

    private static MapHelper mapHelper;
    private Button addSpot;
    private Button searchSpot;
    private Button deleteMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.mapHelper = new MapHelper(MainActivity.this, (MapFragment) fragmentManager.findFragmentById(R.id.maps));

        this.addSpot = findViewById(R.id.addSpot);
        this.searchSpot = findViewById(R.id.searchSpot);
        this.deleteMarker = findViewById(R.id.deleteMarker);

        this.deleteMarker.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initActivity();

        mapHelper.initMap();
    }

    private void initActivity() {
        this.addSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddSpotActivity.class);
                if(mapHelper.getSelectedMarker() != null) {
                    double markerLatitude =  mapHelper.getSelectedMarker().getPosition().latitude;
                    double markerLongitude =  mapHelper.getSelectedMarker().getPosition().longitude;
                    intent.putExtra("selectedLatitude",markerLatitude);
                    intent.putExtra("selectedLongitude",markerLongitude);
                }
                startActivity(intent);
            }
        });

        this.searchSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });

        this.deleteMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapHelper.resetSelectedMarker();
            }
        });
    }
}