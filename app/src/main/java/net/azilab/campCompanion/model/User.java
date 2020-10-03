package net.azilab.campCompanion.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

public class User {

    // ATTRIBUTES

    private int id;
    private String username;
    private String password;
    private String token;
    private Role role;
    private Set<Spot> favoriteSpots;

    // CONSTRUCTOR
    public User() {}

    // GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Spot> getFavoriteSpots() {
        return favoriteSpots;
    }

    public void setFavoriteSpots(Set<Spot> favoriteSpots) {
        this.favoriteSpots = favoriteSpots;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // METHODS
    public JSONObject toJSon() {
        JSONObject spotJson = new JSONObject();
        try {
            spotJson.put("id", this.id);
            spotJson.put("username", this.username);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return spotJson;
    }

}

