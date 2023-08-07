package com.lastminute.recruitment.domain;

import com.lastminute.recruitment.domain.error.WikiPageNotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.emptyList;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WikiScrapperTest {

    @Mock
    private WikiReader reader;
    @Mock
    private WikiPageRepository repository;
    @InjectMocks
    private WikiScrapper scrapper;

    @Test
    public void readWhenPageNotFound() {
        String invalidLink = "http://wikiscrapper.test/invalid";
        when(reader.read(invalidLink)).thenThrow(new WikiPageNotFound(invalidLink));

        WikiPageNotFound exception = assertThrows(WikiPageNotFound.class, () -> {
            scrapper.read(invalidLink);
        });

        assertEquals(exception.getLink(), invalidLink);
    }

    @Test
    public void readWhenSinglePageWasFound() {
        String link = "http://wikiscrapper.test/site1";
        WikiPage root = new WikiPage("title", "content", link, emptyList());
        when(reader.read(link)).thenReturn(root);

        scrapper.read(link);

        verify(repository, times(1)).save(root);
    }

    @Test
    public void readWhenManyPagesWasFound() {
        String link1 = "http://wikiscrapper.test/site1";
        String link2 = "http://wikiscrapper.test/site2";
        String link3 = "http://wikiscrapper.test/site3";

        WikiPage root = new WikiPage("title1", "content", link1, of(link2, link3));
        WikiPage child1 = new WikiPage("title2", "content", link2, emptyList());
        WikiPage child2 = new WikiPage("title3", "content", link3, emptyList());

        when(reader.read(link1)).thenReturn(root);
        when(reader.read(link2)).thenReturn(child1);
        when(reader.read(link3)).thenReturn(child2);

        scrapper.read(link1);

        verify(repository, times(1)).save(root);
        verify(repository, times(1)).save(child1);
        verify(repository, times(1)).save(child2);
    }


    @Test
    public void readWhenPageHasCycle() {
        String link1 = "http://wikiscrapper.test/site1";
        String link2 = "http://wikiscrapper.test/site2";
        String link3 = "http://wikiscrapper.test/site3";

        WikiPage root = new WikiPage("title1", "content", link1, of(link2, link3));
        WikiPage selfCycle = new WikiPage("title2", "content", link1, of(link2, link3));
        WikiPage rootCycle = new WikiPage("title3", "content", link2, of(link1, link2));

        when(reader.read(link1)).thenReturn(root);
        when(reader.read(link2)).thenReturn(selfCycle);
        when(reader.read(link3)).thenReturn(rootCycle);

        scrapper.read(link1);

        verify(repository, times(1)).save(root);
        verify(repository, times(1)).save(selfCycle);
        verify(repository, times(1)).save(rootCycle);
    }
}