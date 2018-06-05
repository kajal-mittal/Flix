package com.kmdev.flix.models;

import java.util.List;

/**
 * Created by Kajal on 2/5/2017.
 */
public class ResponsePeopleDetails {

    /**
     * adult : false
     * also_known_as : ["Тревіс Фіммел","Трэвис Фиммел"]
     * biography : Travis Fimmel (born 15 July 1979) is an Australian actor and former Calvin Klein model who is currently based in the United States.
     * <p/>
     * Description above from the Wikipedia article Travis Fimmel, licensed under CC-BY-SA, full list of contributors on Wikipedia
     * birthday : 1979-07-15
     * deathday :
     * gender : 2
     * homepage : http://officialtravisfimmel.com/
     * id : 77700
     * imdb_id : nm1379938
     * name : Travis Fimmel
     * place_of_birth : nr. Echuca, Victoria, Australia
     * popularity : 64.304267
     * profile_path : /s2eX7lG0n35AtkenfZV0uoLSudv.jpg
     */

    private boolean adult;
    private String biography;
    private String birthday;
    private String deathday;
    private int gender;
    private String homepage;
    private int id;
    private String imdb_id;
    private String name;
    private String place_of_birth;
    private double popularity;
    private String profile_path;
    private List<String> also_known_as;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getPopularityInt() {
        return Double.valueOf(popularity).intValue();
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public List<String> getAlso_known_as() {
        return also_known_as;
    }

    public void setAlso_known_as(List<String> also_known_as) {
        this.also_known_as = also_known_as;
    }
}
