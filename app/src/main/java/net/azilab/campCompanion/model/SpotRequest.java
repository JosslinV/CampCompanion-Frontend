package net.azilab.campCompanion.model;

import java.io.Serializable;

public class SpotRequest implements Serializable {

    private double locationLatitude;
    private double locationLongitude;

    private int accessibilityNote;
    private int locationNote;
    private int utilitiesNote;
    private int privacyNote;

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
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
