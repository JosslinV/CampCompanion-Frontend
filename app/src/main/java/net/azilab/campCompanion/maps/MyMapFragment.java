package net.azilab.campCompanion.maps;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import net.azilab.campCompanion.MainActivity;

public class MyMapFragment extends SupportMapFragment implements OnMapReadyCallback {
    //ELEMENTS
    private GoogleMap googleMap;
    private Activity MainActivity;

    //ATTRIBUTES
    private final int MAX_ZOOM = 5;
    private final LatLngBounds FRANCE = new LatLngBounds(
            new LatLng(41.2632185, -5.4534286),
            new LatLng(51.268318, 9.8678344));

    public MyMapFragment() {
        getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap gmap) {
        this.googleMap = gmap;

        // Constrain navigation around france territory
        this.googleMap.setMinZoomPreference(MAX_ZOOM);
        this.googleMap.setLatLngBoundsForCameraTarget(FRANCE);

        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FRANCE.getCenter(), 5));

        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

            }
        });
    }
}
