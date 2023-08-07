package com.lastminute.recruitment.client;

import com.lastminute.recruitment.domain.WikiPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HtmlWikiClientTest {

    @InjectMocks
    private HtmlWikiClient client;

    @Test
    void readWhenLinksArePresent() {
        WikiPage page = client.read("http://wikiscrapper.test/site2");

        assertEquals("Site 2", page.getTitle());
        assertEquals("http://wikiscrapper.test/site2", page.getSelfLink());
        assertEquals("Content 2", page.getContent());
        assertEquals("http://wikiscrapper.test/site4, http://wikiscrapper.test/site5", page.getLinks()
                .stream()
                .collect(Collectors.joining(", ")));
    }

    @Test
    void readWhenLinksAreMissing() {
        WikiPage page = client.read("http://wikiscrapper.test/site1");

        assertEquals("Site 1", page.getTitle());
        assertEquals("http://wikiscrapper.test/site1", page.getSelfLink());
        assertEquals("Content1", page.getContent());
        assertEquals(true, page.getLinks().isEmpty());
    }

}