package br.com.zg.urlShortener.repositories;

import br.com.zg.urlShortener.models.UrlOriginal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlOriginalRepository extends JpaRepository <UrlOriginal, Long> {

    UrlOriginal findUrlOriginalByName(String name);

    UrlOriginal findById(UrlOriginal urlOriginalId);

    UrlOriginal findIdByName(String urlShortener);
}
