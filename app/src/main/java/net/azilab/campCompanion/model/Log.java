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

    public JSONObject toJSon() {
        JSONObject spotJson = new JSONObject();
        try {
            spotJson.put("id", this.id);
            spotJson.put("comment", this.comment);
            spotJson.put("note", this.note);
            spotJson.put("relatedSpot", this.relatedSpot.toJSon());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return spotJson;
    }

}
