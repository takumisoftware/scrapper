package com.lastminute.recruitment.domain.error;

public class WikiPageNotFound extends RuntimeException{
    public WikiPageNotFound(String link) {
        super(link);
    }
}
