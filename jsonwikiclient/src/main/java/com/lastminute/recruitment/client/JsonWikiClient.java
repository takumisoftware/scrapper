package com.lastminute.recruitment.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lastminute.recruitment.domain.WikiPage;
import com.lastminute.recruitment.domain.WikiReader;
import com.lastminute.recruitment.domain.error.WikiPageNotFound;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class JsonWikiClient implements WikiReader {
    public String readJson(String link) {
        String name = link.replace("\"", "")
                .replace("http://wikiscrapper.test/", "/wikiscrapper/") + ".json";
        return Objects.requireNonNull(getClass().getResource(name)).getFile();
    }

    @Override
    public WikiPage read(String link) {


        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonFile = readJson(link);
            Page page = mapper.readValue(new File(jsonFile), Page.class);
            return new WikiPage(page.title, page.content, page.selfLink, page.links);

        } catch (Exception e) {
            throw new WikiPageNotFound(link);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Page {
        public String title;
        public String content;
        public String selfLink;
        public List<String> links;
    }
}
