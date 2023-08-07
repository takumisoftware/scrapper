package com.lastminute.recruitment.domain.error;

public class WikiPageNotFound extends RuntimeException{

    private String link;
    public WikiPageNotFound(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
