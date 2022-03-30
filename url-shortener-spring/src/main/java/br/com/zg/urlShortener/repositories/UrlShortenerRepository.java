package br.com.zg.urlShortener.repositories;

import br.com.zg.urlShortener.models.UrlShortener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerRepository extends JpaRepository <UrlShortener, Long>
{

    UrlShortener findByName(String id);

    UrlShortener findTopByOrderByIdDesc();

    UrlShortener findIdByUrlOriginalId(Integer id);
}
