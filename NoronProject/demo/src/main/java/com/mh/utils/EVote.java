package com.mh.utils;

public enum EVote {

    POST("post"),COMMENT("comment");
    private String type;

    public String getType(){
        return type;
    }
    EVote(String type){
        this.type = type;
    }
}
