package searchengine.model;

import lombok.Data;
import searchengine.Enum.SiteEnumStatus;
import javax.persistence.*;

@Entity
@Data
@Table(name = "site")
public class Sites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private SiteEnumStatus status;

    @Column(name = "status_time", columnDefinition = "DATETIME NOT NULL")
    private String statusTime;

    @Column(name = "last_error", columnDefinition = "TEXT")
    private String lastError;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String url;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    public Sites() {

    }

    public Sites(
            SiteEnumStatus status,
            String statusTime,
            String lastError,
            String url,
            String name) {
        this.status = status;
        this.statusTime = statusTime;
        this.lastError = lastError;
        this.url = url;
        this.name = name;
    }
}
