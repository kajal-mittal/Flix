package com.kmdev.flix.models;

import java.util.List;

/**
 * Created by Kajal on 3/24/2017.
 */
public class ResponseCastTVShows {

    /**
     * cast : [{"character":"Christine Campbell","credit_id":"5257682b760ee36aaa3a485b","id":15886,"name":"Julia Louis-Dreyfus","profile_path":"/gKWRzUwkXcxSll5w7D7uPMAhBMU.jpg","order":0},{"character":"Richard Campbell","credit_id":"5257682b760ee36aaa3a4889","id":9048,"name":"Clark Gregg","profile_path":"/t8CLS2Zcy7IjcZkE1vtxl40h9rh.jpg","order":1},{"character":"Matthew Kimble","credit_id":"5257682b760ee36aaa3a48b7","id":184581,"name":"Hamish Linklater","profile_path":"/l6nAyT4ZqNq7fOAfB0I9Qi4xpzH.jpg","order":2},{"character":"Ritchie Campbell","credit_id":"5257682b760ee36aaa3a48e9","id":76098,"name":"Trevor Gagnon","profile_path":"/z4EaFmlhctXcdFBzHkUW68W4IRK.jpg","order":3},{"character":"New Christine","credit_id":"5257682b760ee36aaa3a4917","id":1233672,"name":"Emily Rutherfurd","profile_path":"/1clXrlBKFAAQ7ltkMp1uogAa6Ji.jpg","order":4},{"character":"","credit_id":"5257682b760ee36aaa3a4945","id":105715,"name":"Tricia O'Kelley","profile_path":"/9DpB1SETHKiQxshH8nKEKapVHaR.jpg","order":5},{"character":"Lindsay","credit_id":"5257682b760ee36aaa3a4973","id":1211955,"name":"Alex Kapp Horner","profile_path":"/sKL9c1fvDOv6PrAOmDopnSeB3iO.jpg","order":6},{"character":"Barbara 'Barb' Daran","credit_id":"5257682b760ee36aaa3a49a1","id":27102,"name":"Wanda Sykes","profile_path":"/8qSQRIKhxJWhLUrOKLsOmN6mbSd.jpg","order":7},{"character":"Production Assistant","credit_id":"586408209251412b7d00f549","id":1729244,"name":"Diego Garzon","profile_path":null,"order":500}]
     * crew : [{"credit_id":"5257682d760ee36aaa3a49e0","department":"Writing","id":1236672,"name":"Jeff Astrof","job":"Writer","profile_path":null},{"credit_id":"5257682e760ee36aaa3a4a11","department":"Writing","id":1236673,"name":"Katie Palmer","job":"Writer","profile_path":null},{"credit_id":"5257682e760ee36aaa3a4a45","department":"Writing","id":193707,"name":"Sherry Bilsing","job":"Writer","profile_path":"/yVMy3sw4wYfqJIlnqQFzxPKEKAs.jpg"},{"credit_id":"5257682f760ee36aaa3a4a76","department":"Writing","id":1236674,"name":"Ellen Kreamer","job":"Writer","profile_path":null},{"credit_id":"52576830760ee36aaa3a4aa7","department":"Writing","id":1236675,"name":"Frank Pines","job":"Writer","profile_path":null},{"credit_id":"52576831760ee36aaa3a4ade","department":"Writing","id":1236676,"name":"Allan Rice","job":"Writer","profile_path":null},{"credit_id":"52576833760ee36aaa3a4b15","department":"Writing","id":1236677,"name":"Danielle Evenson","job":"Writer","profile_path":null},{"credit_id":"52576834760ee36aaa3a4b46","department":"Writing","id":1236678,"name":"Amy Iglow","job":"Writer","profile_path":null},{"credit_id":"52576834760ee36aaa3a4b74","department":"Writing","id":1213739,"name":"Jack Burditt","job":"Writer","profile_path":null},{"credit_id":"52576835760ee36aaa3a4ba5","department":"Writing","id":1236679,"name":"Jonathan M. Goldstein","job":"Writer","profile_path":null},{"credit_id":"52576836760ee36aaa3a4bd6","department":"Writing","id":1236680,"name":"Steve Baldikoski","job":"Writer","profile_path":null},{"credit_id":"52576838760ee36aaa3a4c07","department":"Writing","id":1236681,"name":"Bryan Behar","job":"Writer","profile_path":null},{"credit_id":"52576839760ee36aaa3a4c38","department":"Writing","id":1236682,"name":"Matt Goldman","job":"Writer","profile_path":null},{"credit_id":"52576839760ee36aaa3a4c66","department":"Writing","id":111942,"name":"Jackie Filgo","job":"Writer","profile_path":null},{"credit_id":"52576839760ee36aaa3a4c94","department":"Writing","id":111943,"name":"Jeff Filgo","job":"Writer","profile_path":null},{"credit_id":"52576839760ee36aaa3a4cca","department":"Production","id":111705,"name":"Kari Lizer","job":"Producer","profile_path":null},{"credit_id":"52576839760ee36aaa3a4cf8","department":"Production","id":1213790,"name":"Andy Ackerman","job":"Producer","profile_path":null},{"credit_id":"52576839760ee36aaa3a4d26","department":"Production","id":1229508,"name":"Lisa Helfrich","job":"Producer","profile_path":null},{"credit_id":"52576839760ee36aaa3a4d54","department":"Production","id":15886,"name":"Julia Louis-Dreyfus","job":"Producer","profile_path":"/gKWRzUwkXcxSll5w7D7uPMAhBMU.jpg"},{"credit_id":"52576839760ee36aaa3a4d82","department":"Production","id":1229505,"name":"Holli Gailen","job":"Producer","profile_path":null},{"credit_id":"52576839760ee36aaa3a4db0","department":"Production","id":1236672,"name":"Jeff Astrof","job":"Producer","profile_path":null},{"credit_id":"52576839760ee36aaa3a4dde","department":"Production","id":193707,"name":"Sherry Bilsing","job":"Producer","profile_path":"/yVMy3sw4wYfqJIlnqQFzxPKEKAs.jpg"},{"credit_id":"52576839760ee36aaa3a4e0c","department":"Production","id":1236674,"name":"Ellen Kreamer","job":"Producer","profile_path":null},{"credit_id":"52576839760ee36aaa3a4e3a","department":"Production","id":1219575,"name":"Adam Barr","job":"Producer","profile_path":null}]
     * id : 4402
     */

    private int id;
    /**
     * character : Christine Campbell
     * credit_id : 5257682b760ee36aaa3a485b
     * id : 15886
     * name : Julia Louis-Dreyfus
     * profile_path : /gKWRzUwkXcxSll5w7D7uPMAhBMU.jpg
     * order : 0
     */

    private List<CastBean> cast;
    /**
     * credit_id : 5257682d760ee36aaa3a49e0
     * department : Writing
     * id : 1236672
     * name : Jeff Astrof
     * job : Writer
     * profile_path : null
     */

    private List<CrewBean> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CastBean> getCast() {
        return cast;
    }

    public void setCast(List<CastBean> cast) {
        this.cast = cast;
    }

    public List<CrewBean> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewBean> crew) {
        this.crew = crew;
    }

    public static class CastBean {
        private String character;
        private String credit_id;
        private int id;
        private String name;
        private String profile_path;
        private int order;

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public String getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(String credit_id) {
            this.credit_id = credit_id;
        }

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

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }
    }

    public static class CrewBean {
        private String credit_id;
        private String department;
        private int id;
        private String name;
        private String job;
        private Object profile_path;

        public String getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(String credit_id) {
            this.credit_id = credit_id;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

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

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public Object getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(Object profile_path) {
            this.profile_path = profile_path;
        }
    }
}
