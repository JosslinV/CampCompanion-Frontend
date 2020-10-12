package net.azilab.campCompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.azilab.campCompanion.maps.MapFragment;
import net.azilab.campCompanion.maps.MapHelper;

public class MapActivity extends AppCompatActivity {

    private static MapHelper mapHelper;
    private Button addSpot;
    private Button searchSpot;
    private Button deleteMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complex_map_view);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.mapHelper = new MapHelper(MapActivity.this, (MapFragment) fragmentManager.findFragmentById(R.id.maps));

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
        //Bouton ajouter spot
        this.addSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, AddSpotActivity.class);
                //Si l'utilisateur a placé un marqueur, ses coordonnées sont envoyés
                //à la vue gérant l'ajout de spot
                if(mapHelper.getSelectedMarker() != null) {
                    double markerLatitude =  mapHelper.getSelectedMarker().getPosition().latitude;
                    double markerLongitude =  mapHelper.getSelectedMarker().getPosition().longitude;
                    intent.putExtra("selectedLatitude",markerLatitude);
                    intent.putExtra("selectedLongitude",markerLongitude);
                }
                startActivity(intent);
            }
        });

        //bouton rechercher spot
        this.searchSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });

        //bouton effacer marqueur
        this.deleteMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapHelper.resetSelectedMarker();
            }
        });
    }
}