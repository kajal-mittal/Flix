package com.kmdev.flix.models;

import java.util.List;

/**
 * Created by Kajal on 10/11/2016.
 */
public class ResponseMovieDetails {
    /**
     * adult : false
     * backdrop_path : /xu9zaAevzQ5nnrsXN6JcahLnG4i.jpg
     * belongs_to_collection : null
     * budget : 165000000
     * genres : [{"id":12,"name":"Adventure"},{"id":18,"name":"Drama"},{"id":878,"name":"Science Fiction"}]
     * homepage : http://www.interstellarmovie.net/
     * id : 157336
     * imdb_id : tt0816692
     * original_language : en
     * original_title : Interstellar
     * overview : Interstellar chronicles the adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage.
     * popularity : 11.710438
     * poster_path : /nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
     * production_companies : [{"name":"Paramount Pictures","id":4},{"name":"Legendary Pictures","id":923},{"name":"Warner Bros.","id":6194},{"name":"Syncopy","id":9996},{"name":"Lynda Obst Productions","id":13769}]
     * production_countries : [{"iso_3166_1":"CA","name":"Canada"},{"iso_3166_1":"US","name":"United States of America"},{"iso_3166_1":"GB","name":"United Kingdom"}]
     * release_date : 2014-11-05
     * revenue : 621752480
     * runtime : 169
     * spoken_languages : [{"iso_639_1":"en","name":"English"}]
     * status : Released
     * tagline : Mankind was born on Earth. It was never meant to die here.
     * title : Interstellar
     * video : false
     * vote_average : 8.1
     * vote_count : 5824
     * videos : {"results":[{"id":"5794fffbc3a36829ab00056f","iso_639_1":"en","iso_3166_1":"US","key":"2LqzF5WauAw","name":"Official Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"5795006f92514142390035ae","iso_639_1":"en","iso_3166_1":"US","key":"Rt2LHkSwdPQ","name":"Official Trailer #2","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817ada9251417c28000b02","iso_639_1":"en","iso_3166_1":"US","key":"827FNDpQWrQ","name":"Official UK Teaser Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817b0fc3a368592500394d","iso_639_1":"en","iso_3166_1":"US","key":"LY19rHKAaAg","name":"Official UK Trailer 4","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817accc3a368592500392e","iso_639_1":"en","iso_3166_1":"US","key":"0vxOhd4qlnA","name":"Official Trailer #3","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817ab4c3a36813870024b7","iso_639_1":"en","iso_3166_1":"US","key":"KlyknsTJk0w","name":"Own it today","site":"YouTube","size":1080,"type":"Teaser"},{"id":"57817aa69251417bfc000a58","iso_639_1":"en","iso_3166_1":"US","key":"zSWdZVtXT7E","name":"Official UK Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817a91c3a36873ea000adf","iso_639_1":"en","iso_3166_1":"US","key":"nyc6RJEEe0U","name":"Official Teaser","site":"YouTube","size":1080,"type":"Teaser"},{"id":"57817b1a9251417b8e000a8c","iso_639_1":"en","iso_3166_1":"US","key":"ePbKGoIGAXY","name":"Official UK Trailer #3","site":"YouTube","size":1080,"type":"Trailer"}]}
     * images : {"backdrops":[],"posters":[]}
     */

    private boolean adult;
    private String backdrop_path;
    private Object belongs_to_collection;
    private int budget;
    private String homepage;
    private int id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private int revenue;
    private int runtime;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;
    private VideosBean videos;
    private ImagesBean images;
    /**
     * id : 12
     * name : Adventure
     */

    private List<GenresBean> genres;
    /**
     * name : Paramount Pictures
     * id : 4
     */

    private List<ProductionCompaniesBean> production_companies;
    /**
     * iso_3166_1 : CA
     * name : Canada
     */

    private List<ProductionCountriesBean> production_countries;
    /**
     * iso_639_1 : en
     * name : English
     */

    private List<SpokenLanguagesBean> spoken_languages;

    public ResponseMovieDetails(int imdb_id) {
        this.imdb_id = String.valueOf(imdb_id);
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Object getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(Object belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
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

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public VideosBean getVideos() {
        return videos;
    }

    public void setVideos(VideosBean videos) {
        this.videos = videos;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public List<GenresBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresBean> genres) {
        this.genres = genres;
    }

    public List<ProductionCompaniesBean> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<ProductionCompaniesBean> production_companies) {
        this.production_companies = production_companies;
    }

    public List<ProductionCountriesBean> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<ProductionCountriesBean> production_countries) {
        this.production_countries = production_countries;
    }

    public List<SpokenLanguagesBean> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<SpokenLanguagesBean> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public static class VideosBean {
        /**
         * id : 5794fffbc3a36829ab00056f
         * iso_639_1 : en
         * iso_3166_1 : US
         * key : 2LqzF5WauAw
         * name : Official Trailer
         * site : YouTube
         * size : 1080
         * type : Trailer
         */

        private List<ResultsBean> results;

        public List<ResultsBean> getResults() {
            return results;
        }

        public void setResults(List<ResultsBean> results) {
            this.results = results;
        }

        public static class ResultsBean {
            private String id;
            private String iso_639_1;
            private String iso_3166_1;
            private String key;
            private String name;
            private String site;
            private int size;
            private String type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIso_639_1() {
                return iso_639_1;
            }

            public void setIso_639_1(String iso_639_1) {
                this.iso_639_1 = iso_639_1;
            }

            public String getIso_3166_1() {
                return iso_3166_1;
            }

            public void setIso_3166_1(String iso_3166_1) {
                this.iso_3166_1 = iso_3166_1;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSite() {
                return site;
            }

            public void setSite(String site) {
                this.site = site;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }

    public static class ImagesBean {
        private List<?> backdrops;
        private List<?> posters;

        public List<?> getBackdrops() {
            return backdrops;
        }

        public void setBackdrops(List<?> backdrops) {
            this.backdrops = backdrops;
        }

        public List<?> getPosters() {
            return posters;
        }

        public void setPosters(List<?> posters) {
            this.posters = posters;
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

    public static class ProductionCompaniesBean {
        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class ProductionCountriesBean {
        private String iso_3166_1;
        private String name;

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SpokenLanguagesBean {
        private String iso_639_1;
        private String name;

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
