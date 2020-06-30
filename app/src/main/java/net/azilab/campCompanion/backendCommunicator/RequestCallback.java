package net.azilab.campCompanion.backendCommunicator;

import org.json.JSONArray;
import org.json.JSONException;

public interface RequestCallback {
    void onDataReceived(JSONArray response) throws JSONException;
}
