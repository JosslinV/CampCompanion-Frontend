package net.azilab.campCompanion.maps;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import net.azilab.campCompanion.MainActivity;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {
    //ELEMENTS
    private GoogleMap googleMap;

    //ATTRIBUTES
    private final int MAX_ZOOM = 5;
    private final LatLngBounds FRANCE = new LatLngBounds(
            new LatLng(41.2632185, -5.4534286),
            new LatLng(51.268318, 9.8678344));

    public MapFragment() {
        getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap gmap) {
        this.googleMap = gmap;

        // Constrain navigation around france territory
        this.googleMap.setMinZoomPreference(MAX_ZOOM);
        this.googleMap.setLatLngBoundsForCameraTarget(FRANCE);

        //Disable map button
        this.googleMap.getUiSettings().setMapToolbarEnabled(false);

        //Set camera centered on france
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FRANCE.getCenter(), 5));
    }

    public void addPointOnMap(double latitude, double longitude, String title, int spotId) {
        if(this.googleMap != null) {
            LatLng markerPosition = new LatLng(latitude, longitude);
            MarkerOptions newMarker = new MarkerOptions();
            newMarker.position(markerPosition);
            newMarker.title(title);

            Marker marker = this.googleMap.addMarker(newMarker);
            marker.setTag(spotId);
        }
    }
}
