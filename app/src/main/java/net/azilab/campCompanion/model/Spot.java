package net.azilab.campCompanion.model;

public class Spot {

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
}
