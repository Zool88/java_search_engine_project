package searchengine.model;

import lombok.Data;
import searchengine.Enum.SiteEnumStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "site")
public class Sites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "ENUM('INDEXING', 'INDEXED', 'FAILED')NOT NULL")
    private SiteEnumStatus status;

    @Column(name = "status_time",columnDefinition = "DATETIME NOT NULL")
    private Date statusTime;

    @Column(name = "last_error",columnDefinition = "TEXT")
    private String lastError;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String url;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;


}
