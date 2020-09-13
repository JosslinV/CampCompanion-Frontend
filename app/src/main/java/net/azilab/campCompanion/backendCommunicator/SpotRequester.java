package net.azilab.campCompanion.backendCommunicator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import net.azilab.campCompanion.model.Spot;
import net.azilab.campCompanion.model.SpotRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class SpotRequester {

    private static String backendUrl = "192.168.1.10:8080";

    public static void requestSpot(final Activity activityCaller, final RequestCallback callback) {
        String token = activityCaller.getSharedPreferences("applicationPref", MODE_PRIVATE).getString("token", "");

        AndroidNetworking.get("http://" + backendUrl + "/api/spot")
        .addHeaders("Authorization", "Bearer " + token)
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
        String token = activityCaller.getSharedPreferences("applicationPref", MODE_PRIVATE).getString("token", "");

        AndroidNetworking.get("http://" + backendUrl + "/api/spot/{id}")
                .addHeaders("Authorization", "Bearer " + token)
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
        String token = activityCaller.getSharedPreferences("applicationPref", MODE_PRIVATE).getString("token", "");
        ANRequest.GetRequestBuilder requestBuilder = new ANRequest.GetRequestBuilder("http://" + backendUrl + "/api/spot/list");

        requestBuilder.addHeaders("Authorization", "Bearer " + token);
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
        String token = activityCaller.getSharedPreferences("applicationPref", MODE_PRIVATE).getString("token", "");
        AndroidNetworking.post("http://" + backendUrl + "/api/spot")
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Bearer " + token)
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
