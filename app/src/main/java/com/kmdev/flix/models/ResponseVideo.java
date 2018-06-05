package com.kmdev.flix.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kajal on 10/16/2016.
 */
public class ResponseVideo {

    /**
     * id : 157336
     * results : [{"id":"5794fffbc3a36829ab00056f","iso_639_1":"en","iso_3166_1":"US","key":"2LqzF5WauAw","name":"Official Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"5795006f92514142390035ae","iso_639_1":"en","iso_3166_1":"US","key":"Rt2LHkSwdPQ","name":"Official Trailer #2","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817ada9251417c28000b02","iso_639_1":"en","iso_3166_1":"US","key":"827FNDpQWrQ","name":"Official UK Teaser Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817b0fc3a368592500394d","iso_639_1":"en","iso_3166_1":"US","key":"LY19rHKAaAg","name":"Official UK Trailer 4","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817accc3a368592500392e","iso_639_1":"en","iso_3166_1":"US","key":"0vxOhd4qlnA","name":"Official Trailer #3","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817ab4c3a36813870024b7","iso_639_1":"en","iso_3166_1":"US","key":"KlyknsTJk0w","name":"Own it today","site":"YouTube","size":1080,"type":"Teaser"},{"id":"57817aa69251417bfc000a58","iso_639_1":"en","iso_3166_1":"US","key":"zSWdZVtXT7E","name":"Official UK Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"57817a91c3a36873ea000adf","iso_639_1":"en","iso_3166_1":"US","key":"nyc6RJEEe0U","name":"Official Teaser","site":"YouTube","size":1080,"type":"Teaser"},{"id":"57817b1a9251417b8e000a8c","iso_639_1":"en","iso_3166_1":"US","key":"ePbKGoIGAXY","name":"Official UK Trailer #3","site":"YouTube","size":1080,"type":"Trailer"}]
     */

    private int id;
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

    @SerializedName("results")
    private List<VideoBean> videoBean;

    public ResponseVideo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<VideoBean> getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(List<VideoBean> videoBean) {
        this.videoBean = videoBean;
    }

    public static class VideoBean {
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
