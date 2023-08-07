package com.lastminute.recruitment.domain;

import java.util.HashSet;
import java.util.Set;

public class WikiScrapper {

    private final WikiReader wikiReader;
    private final WikiPageRepository repository;

    public WikiScrapper(WikiReader wikiReader, WikiPageRepository repository) {
        this.wikiReader = wikiReader;
        this.repository = repository;
    }


    public void read(String link) {
        read(link, new HashSet<>());
    }

    private void read(String link, Set<String> visited) {
        if (!visited.contains(link)) {
            visited.add(link);

            WikiPage page = wikiReader.read(link);

            repository.save(page);
            page.getLinks().forEach(child -> read(child, visited));
        }
    }

}
