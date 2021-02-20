package com.chotabeta.ptask.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Vinod Dirishala on 20-02-2021 11:57
 **/


public class Data {

    @SerializedName("title") private String title;

    @SerializedName("rows") ArrayList<DataBean> rows;

    public Data(String title, ArrayList<DataBean> rows) {
        this.title = title;
        this.rows = rows;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<DataBean> getRows() {
        return rows;
    }

    public class DataBean {

        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        @SerializedName("imageHref")
        private String imageHref;


        public DataBean(String title, String description, String imageHref) {
            this.title = title;
            this.description = description;
            this.imageHref = imageHref;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageHref() {
            return imageHref;
        }

        public void setImageHref(String imageHref) {
            this.imageHref = imageHref;
        }

    }

}
