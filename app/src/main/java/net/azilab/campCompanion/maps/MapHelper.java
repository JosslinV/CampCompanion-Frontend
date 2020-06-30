package net.azilab.campCompanion.maps;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.gson.Gson;

import net.azilab.campCompanion.MainActivity;
import net.azilab.campCompanion.backendCommunicator.RequestCallback;
import net.azilab.campCompanion.backendCommunicator.Requester;
import net.azilab.campCompanion.model.Spot;

import org.json.JSONArray;
import org.json.JSONException;

public class MapHelper {

    private static MapFragment mapFragment;
    private Activity originActivity;

    public MapHelper(Activity originActivity, MapFragment mapFragment) {
        this.originActivity = originActivity;
        this.mapFragment = mapFragment;
    }

    public void initMap() {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                Requester.requestSpot(originActivity, new RequestCallback() {
                    @Override
                    public void onDataReceived(JSONArray response) throws JSONException {
                        for(int i = 0; i < response.length(); i++) {
                            setSpot(new Gson().fromJson(response.getJSONObject(i).toString(), Spot.class));
                        }
                    }
                });
            }
        });
    }

    public void setSpot(final Spot spot) {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mapFragment.addPointOnMap(spot.getLatitude(),spot.getLongitude(),spot.getName());
            }
        });
    }


}
