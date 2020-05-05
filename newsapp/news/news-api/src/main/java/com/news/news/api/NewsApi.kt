package com.news.news.api

/**
 * News API is an API to [https://newsapi.org/docs/endpoints/top-headlines]
 *
 * This will provide access to the endpoints you can hit in this api.
 *
 * All classes that consume this api, notable a {@link ViewModel} should use
 * background threads to access these functions. All functions are written
 * with the intention to be consume with networking in mind.
 */
interface NewsApi {

    /**
     * @param country: The 2-letter ISO 3166-1 code of the country you want to get headlines for.
     * Possible options: ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za .
     * Note: you can't mix this param with the sources param.
     *
     * @param category: Use the NewsCategory enum to provide a category of news you wish to access
     *
     * @param query: Search term
     *
     * @param sources: A comma-seperated string of identifiers for the news sources or blogs you want headlines from.
     * Use the /sources endpoint to locate these programmatically or look at the sources index.
     * Note: you can't mix this param with the country or category params.
     *
     * @param pageSize: The number of results to return per page (request). 20 is the default, 100 is the maximum.
     *
     * @param page: Use this to page through the results if the total results found is greater than the page size.
     *
     * @return [NewsResponse]
     */
    suspend fun getTopHeadlines(country: String? = "us",
                                category: NewsCategories? = NewsCategories.General,
                                sources: String? = "us",
                                query: String? = null,
                                pageSize: Int? = 20,
                                page: Int? = 1) : NewsResponse


    /**
     * @param query: Keywords or phrases to search for in the article title and body.
     * Advanced search is supported here:
     * Surround phrases with quotes (") for exact match.
     * Prepend words or phrases that must appear with a + symbol. Eg: +bitcoin
     * Prepend words that must not appear with a - symbol. Eg: -bitcoin
     * Alternatively you can use the AND / OR / NOT keywords, and optionally group these with parenthesis. Eg: crypto AND (ethereum OR litecoin) NOT bitcoin.
     * The complete value for q must be URL-encoded.
     *
     * @param queryInTitle: Keywords or phrases to search for in the article title only.
     * Advanced search is supported here:
     * Surround phrases with quotes (") for exact match.
     * Prepend words or phrases that must appear with a + symbol. Eg: +bitcoin
     * Prepend words that must not appear with a - symbol. Eg: -bitcoin
     * Alternatively you can use the AND / OR / NOT keywords, and optionally group these with parenthesis. Eg: crypto AND (ethereum OR litecoin) NOT bitcoin.
     * The complete value for qInTitle must be URL-encoded.
     *
     * @param sources: A comma-seperated string of identifiers (maximum 20) for the news sources or blogs you want headlines from. Use the /sources endpoint to locate these programmatically or look at the sources index.
     *
     * @param domains: A comma-seperated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com) to restrict the search to.
     *
     * @param excludeDomains: A comma-seperated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com) to remove from the results.
     *
     * @param from: A date and optional time for the oldest article allowed. This should be in ISO 8601 format (e.g. 2020-05-04 or 2020-05-04T08:32:30) Default: the oldest according to your plan.
     *
     * @param to: A date and optional time for the newest article allowed. This should be in ISO 8601 format (e.g. 2020-05-04 or 2020-05-04T08:32:30) Default: the newest according to your plan.
     *
     * @param language: The 2-letter ISO-639-1 code of the language you want to get headlines for. Possible options: ardeenesfrheitnlnoptruseudzh. Default: all languages returned.
     *
     * @param sortBy: The order to sort the articles in. Possible options: relevancy, popularity, publishedAt
     * relevancy = articles more closely related to q come first.
     * popularity = articles from popular sources and publishers come first.
     * publishedAt = newest articles come first.
     * Default: publishedAt
     *
     * @return [NewsResponse]
     */
    suspend fun getAllNews(query: String? = "general",
                           queryInTitle: String? = null,
                           sources: String? = "us",
                           domains: String? = null,
                           excludeDomains: String? = null,
                           from: String? = null,
                           to: String? = null,
                           language: String? = "en",
                           sortBy: NewsFilters? = NewsFilters.PublishedAt,
                           pageSize: Int? = 20,
                           page: Int? = 1) : NewsResponse
}