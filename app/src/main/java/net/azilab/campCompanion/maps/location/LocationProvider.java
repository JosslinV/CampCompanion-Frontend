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

public class LocationProvider {

    public static Location getPosition(Activity originActivity) throws NullPointerException {
        LocationManager mLocationManager = (LocationManager) originActivity.getSystemService(Context.LOCATION_SERVICE);
        if (originActivity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location lastLocation =  mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            System.out.println("Last Location:" + lastLocation.getLatitude() + " - " + lastLocation.getLongitude());
            return lastLocation;
        }

        throw new NullPointerException();
    }
}
