package net.azilab.campCompanion.backendCommunicator;

import android.app.Activity;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import net.azilab.campCompanion.model.Log;
import net.azilab.campCompanion.model.Spot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class LogRequester {
    private static String backendUrl = "192.168.1.10:8080";

    public static void sendLog(Log logToAdd, final Activity activityCaller, final RequestCallback callback) {
        String token = activityCaller.getSharedPreferences("applicationPref", MODE_PRIVATE).getString("token", "");
        AndroidNetworking.post("http://" + backendUrl + "/api/log")
                .setPriority(Priority.LOW)
                .addHeaders("Authorization", "Bearer " + token)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(logToAdd.toJSon())
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
