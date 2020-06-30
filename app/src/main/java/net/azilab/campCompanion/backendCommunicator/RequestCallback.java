package net.azilab.campCompanion.backendCommunicator;

import org.json.JSONArray;
import org.json.JSONException;

import okhttp3.Response;

public interface RequestCallback<T> {
    void onDataReceived(T response) throws JSONException;
}
