package net.azilab.campCompanion.backendCommunicator;

import android.app.Activity;
import android.widget.Toast;

import com.androidnetworking.common.Priority;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import net.azilab.campCompanion.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;

public class Requester {

    public static void requestSpot(final Activity activityCaller, final RequestCallback callback) {
        AndroidNetworking.get("http://192.168.1.10:8080/api/spot")
        .setPriority(Priority.LOW)
        .build()
        .getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    callback.onDataReceived(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(ANError error) {
                Toast toast = Toast.makeText(activityCaller, "add spot !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}