package searchengine.backEnd.PageHike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import searchengine.Enum.SiteEnumStatus;
import searchengine.backEnd.services.SitesServices;
import searchengine.backEnd.sitesListIndexing.SitesListIndexing;
import searchengine.config.SitesList;
import searchengine.model.Sites;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@ComponentScan
public class StartIndexing {

    @Autowired
    private SitesList sitesList;
    @Autowired
    private SitesServices sitesServices;
    @Autowired
    private static SiteParser sitemapRoot;

    public StartIndexing() {
    }


    public SitesServices getIndexing(){
        String formatDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
        List<String> nameIndexList = SitesListIndexing.getNameListIndexing(sitesList);
        List<String> urlIndexList = SitesListIndexing.getUrlListIndexing(sitesList);

        for (int i = 0; i < nameIndexList.size(); i++) {
//            sitemapRoot = new SiteParser(urlIndexList.get(i));
//            new ForkJoinPool().invoke(new PageIndexingStream(sitemapRoot));

            /**              !!!              **/
            sitesServices.saveOne(new Sites(
                    SiteEnumStatus.INDEXING,
                    formatDate,
                    "NULL",
                    urlIndexList.get(i),
                    nameIndexList.get(i)
            ));
        }
        return sitesServices;
    }
}
