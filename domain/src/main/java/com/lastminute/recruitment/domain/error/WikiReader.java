package com.lastminute.recruitment.domain.error;

import com.lastminute.recruitment.domain.WikiPage;

public interface WikiReader {

    WikiPage read(String link);

}
