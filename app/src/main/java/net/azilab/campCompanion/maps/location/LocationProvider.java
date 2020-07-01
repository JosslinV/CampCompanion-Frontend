package net.azilab.campCompanion.maps.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;


import java.util.Calendar;

public class LocationProvider implements LocationListener {

    private LocationManager mLocationManager;
    private Activity originActivity;

    public LocationProvider(Activity originActivity) {
        this.originActivity = originActivity;
        this.mLocationManager = (LocationManager) originActivity.getSystemService(Context.LOCATION_SERVICE);
    }

    public void getOnePosition(LocationHandler handler) {
        if (originActivity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null && location.getTime() > Calendar.getInstance().getTimeInMillis() - 2 * 60 * 1000) {
                handler.handleLocationReceived(location);
            } else {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
            mLocationManager.removeUpdates(this);
        }
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
