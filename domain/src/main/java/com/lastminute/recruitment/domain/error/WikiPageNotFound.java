package com.lastminute.recruitment.domain.error;

public class WikiPageNotFound extends RuntimeException{

    private final String link;
    public WikiPageNotFound(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
