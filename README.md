# WIKI SCRAPPER

## Implementation steps

1. Implement WikiScrapper class in domain module
    1. Consider cases when wikiReader throws `WikiPageNotFound` error
    2. Write tests for domain module (in order to test you need to mock all plugins - repository & wiki reader) //TDD approach can help
    3. Think about looped wikiPages (for example wikiPage1 -> wikiPage2 & wikiPage2 -> wikiPage1)
2. Implement `WikiReader` for both clients `html` & `json` using `Adapter` design pattern
3. Fix `WikiScrapperConfiguration` to return correct implementation for both `readers`
4. Implement `WikiScrapperResource` to call `WikiScrapper`
    1. 404 httpStatus should be returned for not existing `WikiPage`
    2. Implement some automated tests for rest requests (check 404 & 200 case)
    3. Implement some automated tests which uses `json` profile and read some `json` wikipage
    
## Hints

1. It's allowed to use any testing framework (for example Mockito for mocking)
2. It's allowed & recommended using external libraries to read both `html` & `json` files
3. Domain module shouldn't have dependency to any other module


## Submitting task

Task implementation should be shared on some public repository (for example github)



        