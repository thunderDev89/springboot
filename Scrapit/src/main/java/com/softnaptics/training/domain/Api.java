package com.softnaptics.training.domain;

public class Api {
    private static Api instance;

    private final String PREFIX_URL = "http://musiques.show2babi.com/";

    private Api() {}

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    public String getBaseUrl() {
        return "http://musiques.show2babi.com/";
    }

    public String getPrefixUrl() {
        return PREFIX_URL;
    }

    public String getFullUrl(String hrefUrl) {
        return PREFIX_URL + hrefUrl;
    }
}
