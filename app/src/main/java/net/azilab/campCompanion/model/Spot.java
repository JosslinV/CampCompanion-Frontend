package net.azilab.campCompanion.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Spot implements Serializable {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private String imgPath;
    private int accessibilityNote;
    private int locationNote;
    private int utilitiesNote;
    private int privacyNote;

    //GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getAccessibilityNote() {
        return accessibilityNote;
    }

    public void setAccessibilityNote(int accessibilityNote) {
        this.accessibilityNote = accessibilityNote;
    }

    public int getLocationNote() {
        return locationNote;
    }

    public void setLocationNote(int locationNote) {
        this.locationNote = locationNote;
    }

    public int getUtilitiesNote() {
        return utilitiesNote;
    }

    public void setUtilitiesNote(int utilitiesNote) {
        this.utilitiesNote = utilitiesNote;
    }

    public int getPrivacyNote() {
        return privacyNote;
    }

    public void setPrivacyNote(int privacyNote) {
        this.privacyNote = privacyNote;
    }

    public JSONObject toJSon() {
        JSONObject spotJson = new JSONObject();
        try {
            spotJson.put("name", this.name);
            spotJson.put("latitude", this.latitude);
            spotJson.put("longitude", this.longitude);
            spotJson.put("imgPath", this.imgPath);
            spotJson.put("accessibilityNote", this.accessibilityNote);
            spotJson.put("locationNote", this.locationNote);
            spotJson.put("utilitiesNote", this.utilitiesNote);
            spotJson.put("privacyNote", this.privacyNote);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return spotJson;
    }
}
