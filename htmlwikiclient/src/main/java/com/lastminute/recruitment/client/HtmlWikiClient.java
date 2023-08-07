package com.lastminute.recruitment.client;

import com.lastminute.recruitment.domain.WikiPage;
import com.lastminute.recruitment.domain.WikiReader;
import com.lastminute.recruitment.domain.error.WikiPageNotFound;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class HtmlWikiClient implements WikiReader {

    public String readHtml(String link) {
        String name = link.replace("\"", "")
                .replace("http://wikiscrapper.test/", "/wikiscrapper/") + ".html";
        return Objects.requireNonNull(getClass().getResource(name)).getFile();
    }

    @Override
    public WikiPage read(String link) {


        try {
            String htmlFile = readHtml(link);
            Document doc = Jsoup.parse(new File(htmlFile), "UTF-8", "http://wikiscrapper.test");

            List<String> links = doc.getElementsByClass("links").get(0).getElementsByTag("a")
                    .stream()
                    .map(e -> e.attr("href"))
                    .collect(Collectors.toList());

            return new WikiPage(doc.getElementsByClass("title").text(),
                    doc.getElementsByClass("content").text(),
                    doc.getElementsByAttribute("selfLink").attr("selfLink"),
                    links);

        } catch (Exception e) {
            throw new WikiPageNotFound(link);
        }
    }
}
