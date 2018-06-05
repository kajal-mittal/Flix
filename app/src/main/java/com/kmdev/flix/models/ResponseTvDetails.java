package com.kmdev.flix.models;

import java.util.List;

/**
 * Created by Kajal on 1/6/2017.
 */
public class ResponseTvDetails {

    /**
     * backdrop_path : /A30ZqEoDbchvE7mCZcSp6TEwB1Q.jpg
     * created_by : [{"id":37631,"name":"Michael Hirst","profile_path":"/pV1GW5TiYgkjaXF4ZdRZHXwmh5W.jpg"}]
     * episode_run_time : [44,60]
     * first_air_date : 2013-03-03
     * genres : [{"id":18,"name":"Drama"},{"id":10759,"name":"Action & Adventure"}]
     * homepage : http://www.history.com/shows/vikings
     * id : 44217
     * in_production : true
     * languages : ["en"]
     * last_air_date : 2017-02-01
     * name : Vikings
     * networks : [{"id":238,"name":"History"}]
     * number_of_episodes : 49
     * number_of_seasons : 4
     * origin_country : ["IE","CA"]
     * original_language : en
     * original_name : Vikings
     * overview : Vikings follows the adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.
     * popularity : 52.167638
     * poster_path : /mBDlsOhNOV1MkNii81aT14EYQ4S.jpg
     * production_companies : [{"name":"Shaw Media","id":7224},{"name":"World 2000 Entertainment","id":7692},{"name":"Take 5 Productions","id":39520}]
     * seasons : [{"air_date":"2013-02-25","episode_count":2,"id":53335,"poster_path":"/woP1jNltcWMLqK6pcFqPJq6pyJ6.jpg","season_number":0},{"air_date":"2013-03-03","episode_count":9,"id":53334,"poster_path":"/uYaskJzmBhBdvkitDTjlH6gj9Pt.jpg","season_number":1},{"air_date":"2014-02-27","episode_count":10,"id":53336,"poster_path":"/g2Pgu5Dae9P99yJs0xZQtEagA76.jpg","season_number":2},{"air_date":"2015-02-19","episode_count":10,"id":64422,"poster_path":"/aAuC7GU2K786iXheFevHep1AVGX.jpg","season_number":3},{"air_date":"2016-02-18","episode_count":20,"id":72979,"poster_path":"/nHJHNRApy8vmTUvZx59l1f1GXzB.jpg","season_number":4}]
     * status : Returning Series
     * type : Scripted
     * vote_average : 7
     * vote_count : 361
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
    private String overview;
    private double popularity;
    private String poster_path;
    private String status;
    private String type;
    private float vote_average;
    private int vote_count;
    /**
     * id : 37631
     * name : Michael Hirst
     * profile_path : /pV1GW5TiYgkjaXF4ZdRZHXwmh5W.jpg
     */

    private List<CreatedByBean> created_by;
    private List<Integer> episode_run_time;
    /**
     * id : 18
     * name : Drama
     */

    private List<GenresBean> genres;
    private List<String> languages;
    /**
     * id : 238
     * name : History
     */

    private List<NetworksBean> networks;
    private List<String> origin_country;
    /**
     * name : Shaw Media
     * id : 7224
     */

    private List<ProductionCompaniesBean> production_companies;
    /**
     * air_date : 2013-02-25
     * episode_count : 2
     * id : 53335
     * poster_path : /woP1jNltcWMLqK6pcFqPJq6pyJ6.jpg
     * season_number : 0
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
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

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public List<CreatedByBean> getCreated_by() {
        return created_by;
    }

    public void setCreated_by(List<CreatedByBean> created_by) {
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

    public List<ProductionCompaniesBean> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<ProductionCompaniesBean> production_companies) {
        this.production_companies = production_companies;
    }

    public List<SeasonsBean> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonsBean> seasons) {
        this.seasons = seasons;
    }

    public static class CreatedByBean {
        private int id;
        private String name;
        private String profile_path;

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

        public String getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(String profile_path) {
            this.profile_path = profile_path;
        }
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

    public static class ProductionCompaniesBean {
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
