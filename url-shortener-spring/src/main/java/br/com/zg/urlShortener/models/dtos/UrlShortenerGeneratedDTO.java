package br.com.zg.urlShortener.models.dtos;

import lombok.Data;

@Data
public class UrlShortenerGeneratedDTO {
    private String urlOriginal;
    private String urlShortener;
    private Number generated;
}
