package com.lastminute.recruitment.rest;

import com.lastminute.recruitment.domain.WikiScrapper;
import com.lastminute.recruitment.domain.error.WikiPageNotFound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = WikiScrapperResource.class)
@WebMvcTest(WikiScrapperResource.class)
class WikiScrapperResourceTest {


    @MockBean
    private WikiScrapper scrapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void scrapWikiWhenPageNotFound() throws Exception {

        String link = "\"http://wikiscrapper.test/invalid\"";
        String normalized = "http://wikiscrapper.test/invalid";
        doThrow(new WikiPageNotFound(link)).when(scrapper).read(normalized);


        mvc.perform(post("/wiki/scrap")
                .content(link)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    void scrapWikiWhenPageExists() throws Exception {

        String link = "\"http://wikiscrapper.test/site1\"";
        String normalized = "http://wikiscrapper.test/site1";

        mvc.perform(post("/wiki/scrap")
                .content(link)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(scrapper, times(1)).read(normalized);
    }
}