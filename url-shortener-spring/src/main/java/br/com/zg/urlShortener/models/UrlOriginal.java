package br.com.zg.urlShortener.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "url_original")
@Getter
@Setter
public class UrlOriginal {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "generated")
    private Integer generated;

    @JsonIgnore
    @OneToMany(mappedBy = "urlOriginalId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UrlShortener> urlShortener;

    public UrlOriginal() {

    }
}

