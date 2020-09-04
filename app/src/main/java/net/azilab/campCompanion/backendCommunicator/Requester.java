package net.azilab.campCompanion.backendCommunicator;

import android.app.Activity;
import android.widget.Toast;

import com.androidnetworking.common.ANRequest;
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
import net.azilab.campCompanion.model.SpotRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

public class Requester {

    private static String backendUrl = "10.0.2.2:8080";

    public static void requestSpot(final Activity activityCaller, final RequestCallback callback) {
        AndroidNetworking.get("http://" + backendUrl + "/api/spot")
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
        AndroidNetworking.get("http://" + backendUrl + "/api/spot/{id}")
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

    public static void searchSpot(SpotRequest spotRequest, final Activity activityCaller, final RequestCallback callback) {
        ANRequest.GetRequestBuilder requestBuilder = new ANRequest.GetRequestBuilder("http://" + backendUrl + "/api/spot/list");

        requestBuilder.addQueryParameter("latitude", String.valueOf(spotRequest.getLocationLatitude()));
        requestBuilder.addQueryParameter("longitude", String.valueOf(spotRequest.getLocationLongitude()));

        if(spotRequest.getAccessibilityNote() != 0) {
            requestBuilder.addQueryParameter("accessibility", String.valueOf(spotRequest.getAccessibilityNote()));
        }
        if(spotRequest.getLocationNote() != 0) {
            requestBuilder.addQueryParameter("location", String.valueOf(spotRequest.getLocationNote()));
        }
        if(spotRequest.getUtilitiesNote() != 0) {
            requestBuilder.addQueryParameter("utilities", String.valueOf(spotRequest.getUtilitiesNote()));
        }
        if(spotRequest.getPrivacyNote() != 0) {
            requestBuilder.addQueryParameter("privacy", String.valueOf(spotRequest.getPrivacyNote()));
        }

        ANRequest request = requestBuilder.build();

        System.out.println(request.getUrl());

        request.getAsJSONArray(new JSONArrayRequestListener() {
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

    public static void sendSpot(Spot spotToAdd, final Activity activityCaller, final RequestCallback callback) {
        AndroidNetworking.post("http://" + backendUrl + "/api/spot")
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