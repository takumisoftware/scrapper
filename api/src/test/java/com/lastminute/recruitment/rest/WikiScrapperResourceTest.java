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

import static org.mockito.Mockito.doThrow;
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
    void scrapWikipedia() throws Exception {

        String url = "\"http://wikiscrapper.test/invalid\"";
        doThrow(new WikiPageNotFound(url)).when(scrapper).read(url);



        mvc.perform(post("/wiki/scrap")
                .content(url)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
}