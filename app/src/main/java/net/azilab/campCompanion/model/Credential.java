package net.azilab.campCompanion.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Credential {

    private String username;
    private String password;

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONObject toJSon() {
        JSONObject spotJson = new JSONObject();
        try {
            spotJson.put("username", this.username);
            spotJson.put("password", this.password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return spotJson;
    }
}
