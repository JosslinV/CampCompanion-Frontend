package net.azilab.campCompanion.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Log implements Serializable {

    // ATTRIBUTES
    private int id;
    private String comment;
    private int note;
    private Spot relatedSpot;
    private User relatedUser;

    // GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Spot getRelatedSpot() {
        return relatedSpot;
    }

    public void setRelatedSpot(Spot relatedSpot) {
        this.relatedSpot = relatedSpot;
    }

    public User getRelatedUser() { return relatedUser;}

    public void setRelatedUser(User relatedUser) { this.relatedUser = relatedUser;}

    public JSONObject toJSon() {
        JSONObject logJson = new JSONObject();
        try {
            logJson.put("id", this.id);
            logJson.put("comment", this.comment);
            logJson.put("note", this.note);
            logJson.put("relatedSpot", this.relatedSpot.toJSon());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return logJson;
    }

}
