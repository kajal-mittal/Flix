package com.kmdev.flix.models;

import java.util.List;

/**
 * Created by Kajal on 1/1/2017.
 */
public class ResponseTvShow {

    /**
     * backdrop_path : /qDNeHKsshqGO7bqNDTxzqoSxH65.jpg
     * created_by : []
     * episode_run_time : [45]
     * first_air_date : 2017-01-04
     * genres : [{"id":18,"name":"Drama"}]
     * homepage :
     * id : 69337
     * in_production : true
     * languages : ["zh"]
     * last_air_date : 2017-01-06
     * name : 遇见爱情的利先生
     * networks : [{"id":989,"name":"Zhejiang Television"}]
     * number_of_episodes : 5
     * number_of_seasons : 1
     * origin_country : ["CN"]
     * original_language : zh
     * original_name : 遇见爱情的利先生
     * overview : null
     * popularity : 1.25
     * poster_path : /8NzDVFWP0eMeRHkOTgxmzleTQSg.jpg
     * production_companies : []
     * seasons : [{"air_date":"2017-01-04","episode_count":6,"id":83284,"poster_path":"/kJzF8NXThXDT9zKLxO9OHCehr03.jpg","season_number":1}]
     * status : Returning Series
     * type : Scripted
     * vote_average : 0
     * vote_count : 0
     */

    private String backdrop_path;
    private String first_air_date;
    private String homepage;
    private int id;
    private boolean in_production;
    private String last_air_date;
    private String name;
    private int number_of_episodes;
    private int number_of_seasons;
    private String original_language;
    private String original_name;
    private Object overview;
    private double popularity;
    private String poster_path;
    private String status;
    private String type;
    private int vote_average;
    private int vote_count;
    private List<?> created_by;
    private List<Integer> episode_run_time;
    /**
     * id : 18
     * name : Drama
     */

    private List<GenresBean> genres;
    private List<String> languages;
    /**
     * id : 989
     * name : Zhejiang Television
     */

    private List<NetworksBean> networks;
    private List<String> origin_country;
    private List<?> production_companies;
    /**
     * air_date : 2017-01-04
     * episode_count : 6
     * id : 83284
     * poster_path : /kJzF8NXThXDT9zKLxO9OHCehr03.jpg
     * season_number : 1
     */

    private List<SeasonsBean> seasons;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
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

    public boolean isIn_production() {
        return in_production;
    }

    public void setIn_production(boolean in_production) {
        this.in_production = in_production;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public Object getOverview() {
        return overview;
    }

    public void setOverview(Object overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public List<?> getCreated_by() {
        return created_by;
    }

    public void setCreated_by(List<?> created_by) {
        this.created_by = created_by;
    }

    public List<Integer> getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(List<Integer> episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public List<GenresBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresBean> genres) {
        this.genres = genres;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<NetworksBean> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworksBean> networks) {
        this.networks = networks;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public List<?> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<?> production_companies) {
        this.production_companies = production_companies;
    }

    public List<SeasonsBean> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonsBean> seasons) {
        this.seasons = seasons;
    }

    public static class GenresBean {
        private int id;
        private String name;

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
    }

    public static class NetworksBean {
        private int id;
        private String name;

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
    }

    public static class SeasonsBean {
        private String air_date;
        private int episode_count;
        private int id;
        private String poster_path;
        private int season_number;

        public String getAir_date() {
            return air_date;
        }

        public void setAir_date(String air_date) {
            this.air_date = air_date;
        }

        public int getEpisode_count() {
            return episode_count;
        }

        public void setEpisode_count(int episode_count) {
            this.episode_count = episode_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public int getSeason_number() {
            return season_number;
        }

        public void setSeason_number(int season_number) {
            this.season_number = season_number;
        }
    }
}
