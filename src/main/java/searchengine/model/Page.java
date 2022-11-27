package searchengine.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "page", indexes = {
        @Index(name = "idx_page_path", columnList = "path")
})
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "site_id", nullable = false)
    private Sites site;


    @Column(name = "path", nullable = false)
    private String path;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer code;

    @Column(columnDefinition = "MEDIUMTEXT NOT NULL")
    private String content;

}
