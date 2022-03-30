package br.com.zg.urlShortener.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "url_shortener")
@Getter
@Setter
public class UrlShortener {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "accessed")
    private Integer accessed;

    @ManyToOne
    @JsonIgnoreProperties(value = { "id", "generated", "url_original_id" })
    @JoinColumn(name = "url_original_id", referencedColumnName = "id")
    private UrlOriginal urlOriginalId;

    public UrlShortener() {

    }
}

