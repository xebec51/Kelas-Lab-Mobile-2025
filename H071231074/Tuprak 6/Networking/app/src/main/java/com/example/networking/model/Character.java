package com.example.networking.model;

import java.io.Serializable;

public class Character implements Serializable { // brfungsi untuk menampung data karakter
    private int id;
    private String name;
    private String status;
    private String species;
    private String gender;
    private String image;
    private Origin origin;
    private Location location;

    public Character(int id, String name, String status, String species, String gender, String image, Origin origin, Location location) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.gender = gender;
        this.image = image;
        this.origin = origin;
        this.location = location;
    }

    public String getName() { return name; }
    public String getStatus() { return status; }
    public String getSpecies() { return species; }
    public String getGender() { return gender; }
    public String getImage() { return image; }
    public Origin getOrigin() { return origin; }
    public Location getLocation() { return location; }

    public static class Origin implements Serializable {
        private String name;
        private String url;

        public Origin(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() { return name; }
    }

    public static class Location implements Serializable {
        private String name;

        public Location(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }
}
