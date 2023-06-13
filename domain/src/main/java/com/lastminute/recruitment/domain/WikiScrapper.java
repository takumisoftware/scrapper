package com.lastminute.recruitment.domain;

import com.lastminute.recruitment.domain.error.WikiPageRepository;
import com.lastminute.recruitment.domain.error.WikiReader;

public class WikiScrapper {

    private final WikiReader wikiReader;
    private final WikiPageRepository repository;

    public WikiScrapper(WikiReader wikiReader, WikiPageRepository repository) {
        this.wikiReader = wikiReader;
        this.repository = repository;
    }


    public void read(String link) {

    }

}
