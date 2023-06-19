package org.unibl.etf.adminapp.dto;

import java.io.Serializable;

public class Client extends Person implements Serializable {
    private String username;
    private String password;
    private String city;
    private String email;
    private boolean activated;

    public Client() {
    }

    public Client(String name, String surname, String username, String password, String city, String email, boolean activated) {
        super(name, surname);
        this.username = username;
        this.password = password;
        this.city = city;
        this.email = email;
        this.activated = activated;
    }

    public Client(String name, String surname, String username, String password, String city, String email) {
        super(name, surname);
        this.username = username;
        this.password = password;
        this.city = city;
        this.email = email;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
