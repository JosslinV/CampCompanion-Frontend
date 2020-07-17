package net.azilab.campCompanion.maps;

import android.app.Activity;
import android.content.Intent;

import android.location.Location;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;


import net.azilab.campCompanion.R;
import net.azilab.campCompanion.SpotInfoActivity;
import net.azilab.campCompanion.backendCommunicator.RequestCallback;
import net.azilab.campCompanion.backendCommunicator.Requester;
import net.azilab.campCompanion.maps.location.LocationHandler;
import net.azilab.campCompanion.maps.location.LocationProvider;
import net.azilab.campCompanion.model.Spot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapHelper {

    private static MapFragment mapFragment;
    private Activity originActivity;
    private LocationProvider locationprovider;

    private Marker selectedMarker;
    private Button deleteMarker;

    private final String USER_MARKER = "userMarker";
    private final int MAX_ZOOM = 5;
    private final LatLngBounds FRANCE = new LatLngBounds(
            new LatLng(41.2632185, -5.4534286),
            new LatLng(51.268318, 9.8678344));

    public MapHelper(Activity originActivity, MapFragment mapFragment) {
        this.originActivity = originActivity;
        this.deleteMarker = originActivity.findViewById(R.id.deleteMarker);
        this.mapFragment = mapFragment;
        this.locationprovider = new LocationProvider(originActivity);
    }

    public void initMap() {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                centerCamera(googleMap);
                resetSelectedMarker();
                Requester.requestSpot(originActivity, new RequestCallback<JSONArray>() {
                    @Override
                    public void onDataReceived(JSONArray response) throws JSONException {
                        for(int i = 0; i < response.length(); i++) {
                            setSpot(new Gson().fromJson(response.getJSONObject(i).toString(), Spot.class));
                        }
                    }
                });

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        handleMarkerClick(marker);
                        return true;
                    }
                });

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        positionMarker(latLng,googleMap);
                        deleteMarker.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    public void positionMarker(LatLng latLng, GoogleMap googleMap) {
        if(selectedMarker != null) {
            resetSelectedMarker();
        }
        LatLng markerPosition = latLng;
        MarkerOptions newMarker = new MarkerOptions();
        newMarker.position(markerPosition);
        newMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        newMarker.title(USER_MARKER);

        selectedMarker = googleMap.addMarker(newMarker);
    }

    public void setSpot(final Spot spot) {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mapFragment.addPointOnMap(spot.getLatitude(),spot.getLongitude(),spot.getName(), spot.getId());
            }
        });
    }

    public void handleMarkerClick(Marker marker) {
        if(marker.equals(selectedMarker)) {
            return;
        }

        int spotId = (int) marker.getTag();
        Requester.requestSpotById(String.valueOf(spotId), originActivity, new RequestCallback<JSONObject>() {
            @Override
            public void onDataReceived(JSONObject response) throws JSONException {
                Spot spotRetrived = new Gson().fromJson(response.toString(), Spot.class);

                Intent intent = new Intent(originActivity, SpotInfoActivity.class);
                intent.putExtra("spot", spotRetrived);
                originActivity.startActivity(intent);
            }
        });
    }

    public void centerCamera(final GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FRANCE.getCenter(), 5));

        locationprovider.getOnePosition(new LocationHandler() {

            @Override
            public void handleLocationReceived(Location location) {
                if(location != null) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 8));
                }
            }
        });
    }

    public Marker getSelectedMarker() {
        return this.selectedMarker;
    }

    public void resetSelectedMarker() {
        if(this.getSelectedMarker() != null) {
            this.selectedMarker.remove();
            this.selectedMarker = null;
            deleteMarker.setVisibility(View.INVISIBLE);
        }
    }
}
