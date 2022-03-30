package br.com.zg.urlShortener.models.dtos;

import lombok.Data;

@Data
public class UrlShortenerListDTO {
    private String urlOriginal;
    private String urlShortener;
    private Number accessed;
}
