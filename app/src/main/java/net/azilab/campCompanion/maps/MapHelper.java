package net.azilab.campCompanion.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MapHelper {

    private MapFragment mapFragment;

    public MapHelper(MapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }

    public void initMap() {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mapFragment.addPointOnMap(43.595993,1.259442, "Point au hasard");
            }
        });
    }


}
