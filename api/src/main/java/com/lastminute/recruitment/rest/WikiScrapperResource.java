package com.lastminute.recruitment.rest;

import com.lastminute.recruitment.domain.WikiScrapper;
import com.lastminute.recruitment.domain.error.WikiPageNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@RequestMapping("/wiki")
@RestController
public class WikiScrapperResource {

    static final String PAGE_NOT_FOUND = "Root link that doesn't exist: ";
    private final Logger LOGGER = LoggerFactory.getLogger(WikiScrapperResource.class);

    private final WikiScrapper scrapper;

    public WikiScrapperResource(WikiScrapper scrapper) {
        this.scrapper = scrapper;
    }


    @PostMapping("/scrap")
    public void scrapWikipedia(@RequestBody String link) {
        LOGGER.info("Hello Scrap -> " + link);

        try {

            scrapper.read(link);

        } catch (WikiPageNotFound e) {
            String msg = PAGE_NOT_FOUND + e.getMessage();
            LOGGER.error(msg);
            throw new ResponseStatusException(NOT_FOUND, msg);
        }
    }

}
