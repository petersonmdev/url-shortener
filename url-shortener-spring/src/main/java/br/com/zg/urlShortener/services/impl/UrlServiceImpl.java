package br.com.zg.urlShortener.services.impl;

import br.com.zg.urlShortener.models.UrlOriginal;
import br.com.zg.urlShortener.models.UrlShortener;
import br.com.zg.urlShortener.models.dtos.UrlShortenerGeneratedDTO;
import br.com.zg.urlShortener.repositories.UrlOriginalRepository;
import br.com.zg.urlShortener.repositories.UrlShortenerRepository;
import br.com.zg.urlShortener.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlOriginalRepository urlOriginalRepository;

    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    @Override
    public UrlShortenerGeneratedDTO generateUrlShortener(String url) {

        UrlOriginal urlOriginal  = this.urlOriginalRepository.findUrlOriginalByName(url);;
        UrlShortener urlShortener  = new UrlShortener();

        if (urlOriginal == null) {

            urlOriginal = new UrlOriginal();
            urlOriginal.setName(url);
            urlOriginal.setGenerated(1);
            this.saveOriginal(urlOriginal);

            String shortener = this.generateUrlHash(url);
            urlShortener.setName(shortener);
            this.saveShortener(urlShortener.getName(), urlOriginal, 0);
        } else {
            Integer numberGenerated = urlOriginal.getGenerated();
            urlOriginal.setGenerated(numberGenerated+1);
            urlOriginal = this.saveOriginal(urlOriginal);

            if ((urlOriginal.getGenerated() % 5) == 0) {
                UrlShortener urlShortenerNew  = new UrlShortener();
                String shortener = this.generateUrlHash(url);
                urlShortenerNew.setName(shortener);
                urlShortenerNew.setUrlOriginalId(urlOriginal);
                urlShortenerNew.setAccessed(0);
                urlShortener = this.saveShortenerSimple(urlShortenerNew);
            } else {
                urlShortener.setName(this.getLastShortener(urlOriginal).getName());
            }
        }

        return createDTO(url, urlShortener.getName(),  urlOriginal.getGenerated());
    }

    @Override
    public List<?> listUrls() {

        List<UrlShortener> listUrl = this.urlShortenerRepository.findAll();
        return listUrl;
    }

    @Override
    public String getUrlOriginal(String id) {

        UrlShortener urlShortener = this.urlShortenerRepository.findByName(id);
        Integer lastAccessed = urlShortener.getAccessed();
        urlShortener.setAccessed(lastAccessed+1);
        this.saveShortenerSimple(urlShortener);
        String urlRedirect = urlShortener.getUrlOriginalId().getName();
        if (!urlRedirect.contains("http://") || !urlRedirect.contains("https://")) {
            String http = "http://";
            String newUrlRedirect = http.concat(urlRedirect);
            return newUrlRedirect;
        } else {
            return urlRedirect;
        }

    }

    public String generateUrlHash(String url)
    {
        String randomStr = "";
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVXYWZabcdefghijklmnopqrstuvxwyz";
        for (int i=0; i<5; i++) {
            randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
        }
        return randomStr;
    }

    private UrlShortenerGeneratedDTO createDTO(String urlOriginal, String urlShortener, Integer generated)
    {
        UrlShortenerGeneratedDTO urlShortenerGeneratedDTO = new UrlShortenerGeneratedDTO();

        urlShortenerGeneratedDTO.setUrlOriginal(urlOriginal);
        urlShortenerGeneratedDTO.setUrlShortener(urlShortener);
        urlShortenerGeneratedDTO.setGenerated(generated);

        return urlShortenerGeneratedDTO;

    }

    private UrlShortener saveShortenerSimple(UrlShortener shortener) {
        return this.urlShortenerRepository.save(shortener);
    }

    private void saveShortener(String shortener, UrlOriginal original, Integer accessed)
    {
        UrlShortener urlShortener = new UrlShortener();

        urlShortener.setName(shortener);
        urlShortener.setAccessed(accessed);
        urlShortener.setUrlOriginalId(original);

        this.urlShortenerRepository.save(urlShortener);
    }

    private UrlOriginal saveOriginal(UrlOriginal original)
    {
        return this.urlOriginalRepository.save(original);
    }

    private UrlShortener getLastShortener(UrlOriginal original)
    {
        Integer lastItemShortener = original.getUrlShortener().size()-1;
        return original.getUrlShortener().get(lastItemShortener);
    }
}
