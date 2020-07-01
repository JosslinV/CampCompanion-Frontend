package net.azilab.campCompanion.backendCommunicator;

import android.app.Activity;
import android.widget.Toast;

import com.androidnetworking.common.Priority;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import net.azilab.campCompanion.MainActivity;
import net.azilab.campCompanion.model.Spot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import okhttp3.Response;

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
                Toast toast = Toast.makeText(activityCaller, "Server unreachable: Check your connection.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public static void requestSpotById(String spotId, final Activity activityCaller, final RequestCallback callback) {
        AndroidNetworking.get("http://192.168.1.10:8080/api/spot/{id}")
                .addPathParameter("id", spotId)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            callback.onDataReceived(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast toast = Toast.makeText(activityCaller, "Server unreachable: Check your connection.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
    }

    public static void sendSpot(Spot spotToAdd, final Activity activityCaller, final RequestCallback callback) {
        AndroidNetworking.post("http://192.168.1.10:8080/api/spot")
                .setPriority(Priority.LOW)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(spotToAdd.toJSon())
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        try {
                            callback.onDataReceived(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        System.out.println(error.getMessage());
                        Toast toast = Toast.makeText(activityCaller, error.getMessage(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
    }


}