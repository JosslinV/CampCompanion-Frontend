package net.azilab.campCompanion.backendCommunicator;

import android.app.Activity;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import net.azilab.campCompanion.model.Credential;
import net.azilab.campCompanion.model.Spot;

import org.json.JSONException;

import okhttp3.Response;

public class AuthenticatorRequester {

    public static void requestAuthentication(Credential credential, final Activity activityCaller, final RequestCallback callback) {
        AndroidNetworking.post("http://192.168.1.10:8080/api/authentication")
                .setPriority(Priority.LOW)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(credential.toJSon())
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
