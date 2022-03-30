package br.com.zg.urlShortener.services.impl;

import br.com.zg.urlShortener.models.dtos.UrlShortenerGeneratedDTO;
import br.com.zg.urlShortener.services.UrlService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UrlOriginalServiceImplTest {

    @Autowired
    private UrlService urlService;

    @Test
    void testGenerateUrlShortener()
    {
        UrlShortenerGeneratedDTO urlShortenerGeneratedDTO = urlService.generateUrlShortener("http://zgsolucoes.com.br/");
        Assert.assertNotNull(urlShortenerGeneratedDTO.getUrlOriginal());
        Assert.assertNotNull(urlShortenerGeneratedDTO.getUrlShortener());
        Assert.assertNotNull(urlShortenerGeneratedDTO.getGenerated());
        Assert.assertEquals(5, urlShortenerGeneratedDTO.getUrlShortener().length());
    }

    @Test
    void testeGetUrlOriginal()
    {
        String urlOriginal = String.valueOf(urlService.getUrlOriginal("PIDDZ"));
        Assert.assertNotNull(urlOriginal);
    }

}
