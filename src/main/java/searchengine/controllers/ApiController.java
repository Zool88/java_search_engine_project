package searchengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import searchengine.backEnd.configIndexing.IndexingStatus;
import searchengine.backEnd.PageHike.StartIndexing;
import searchengine.backEnd.services.PageServices;
import searchengine.backEnd.services.QuerySiteServices;
import searchengine.backEnd.services.SitesServices;
import searchengine.config.SitesList;
import searchengine.config.UserAgentList;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.model.Sites;
import searchengine.services.StatisticsService;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    UserAgentList userAgent;

    @Autowired
    SitesServices sitesServices;
    @Autowired
    PageServices pageServices;
    @Autowired
    private final StatisticsService statisticsService;
    @Autowired
    private final SitesList sitesList;
    @Autowired
    private IndexingStatus indexingStatus;
    @Autowired
    private StartIndexing startIndexing = new StartIndexing();


    @Autowired
    private QuerySiteServices querySiteServices;


    public ApiController(StatisticsService statisticsService, SitesList sites, SitesList sitesList, QuerySiteServices querySiteServices) {
        this.statisticsService = statisticsService;
        this.sitesList = sitesList;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }


    @GetMapping("/startIndexing")
    public Object startIndexing(Sites sites) throws IOException {

        sitesServices.allDelete();
        pageServices.allDelete();
        startIndexing.getIndexing();
        Object status = indexingStatus.getIndexingStatus();


        return status;

    }
}
