package br.com.zg.urlShortener.controllers;

import br.com.zg.urlShortener.exception.NullReturnShortenerException;
import br.com.zg.urlShortener.models.dtos.UrlShortenerGeneratedDTO;
import br.com.zg.urlShortener.models.dtos.UrlShortenerListDTO;
import br.com.zg.urlShortener.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:4200")
public class UrlController
{

    @Autowired
    private UrlService urlService;

    @GetMapping("/list")
    public ResponseEntity<?> listUrls()
    {

        try {
            List listShortener = this.urlService.listUrls();
            return ResponseEntity.ok().body(listShortener);
        } catch (NullPointerException e) {
            throw new NullReturnShortenerException("Nenhum link reduzido criado!");
        }
    }

    @GetMapping("/{id}")
    public RedirectView getUrlOriginal(@PathVariable String id)
    {

        try {
            String urlRedirect = this.urlService.getUrlOriginal(id);
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(urlRedirect);
            return redirectView;
        } catch (NullPointerException e) {
            throw new NullReturnShortenerException("Url não cadastrada ou inválida!");
        }
    }

    @PostMapping("/generated")
    public ResponseEntity<?> generateUrlShortener(@RequestBody UrlShortenerGeneratedDTO urlShortenerGeneratedDTO)
    {

        UrlShortenerGeneratedDTO generateDto = this.urlService.generateUrlShortener(urlShortenerGeneratedDTO.getUrlOriginal());
        return ResponseEntity.ok().body(generateDto);
    }

}
