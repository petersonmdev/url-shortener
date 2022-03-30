package br.com.zg.urlShortener.services;

import br.com.zg.urlShortener.models.dtos.UrlShortenerGeneratedDTO;

import java.util.List;

public interface UrlService {

    UrlShortenerGeneratedDTO generateUrlShortener(String url);
    List listUrls();
    String getUrlOriginal(String id);
}
