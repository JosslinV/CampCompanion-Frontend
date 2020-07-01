package net.azilab.campCompanion.maps;

import android.app.Activity;
import android.content.Intent;

import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;


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

    private final int MAX_ZOOM = 5;
    private final LatLngBounds FRANCE = new LatLngBounds(
            new LatLng(41.2632185, -5.4534286),
            new LatLng(51.268318, 9.8678344));

    public MapHelper(Activity originActivity, MapFragment mapFragment) {
        this.originActivity = originActivity;
        this.mapFragment = mapFragment;
        this.locationprovider = new LocationProvider(originActivity);
    }

    public void initMap() {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                centerCamera(googleMap);

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
            }
        });
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
}
