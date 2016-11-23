package com.udacity.ilmov.kaizenhelper.models;

/**
 * Created by ilmov
 */

public class Improvement {
    private long id;
    private String name;
    private String improver;
    private float rating;
    private String whatToImprove;

    public Improvement() {
    }

    public Improvement(int id, String name, String improver, float rating, String whatToImprove) {
        this.id = id;
        this.name = name;
        this.improver = improver;
        this.rating = rating;
        this.whatToImprove = whatToImprove;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImprover() {
        return improver;
    }

    public void setImprover(String improver) {
        this.improver = improver;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getWhatToImprove() {
        return whatToImprove;
    }

    public void setWhatToImprove(String whatToImprove) {
        this.whatToImprove = whatToImprove;
    }
}
