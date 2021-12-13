package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {

    // first observe this is not a common java naming convention
    // because in java we start variable with lowercase
    // however we still want to instruct Jackson data-bind to match the fields we want
    // in order to instruct jackson library what json field to match what java field
    // you can use the annotation coming from jackson library @JsonProperty
    @JsonProperty("Title")
    private String theWhatEverNameIWant;

    @JsonProperty("Year")
    private String year;

    private String imdbID;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Poster")
    private String poster;

    public String getTheWhatEverNameIWant() {
        return theWhatEverNameIWant;
    }

    public void setTheWhatEverNameIWant(String theWhatEverNameIWant) {
        this.theWhatEverNameIWant = theWhatEverNameIWant;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Title='" + theWhatEverNameIWant + '\'' +
                ", Year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", Type='" + type + '\'' +
                ", Poster='" + poster + '\'' +
                '}';
    }
}
