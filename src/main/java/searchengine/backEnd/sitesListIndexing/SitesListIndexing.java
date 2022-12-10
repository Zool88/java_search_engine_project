package searchengine.backEnd.sitesListIndexing;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import searchengine.config.SitesList;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SitesListIndexing{

    @Bean
    public static List<String> getUrlListIndexing(SitesList sites) {
        List<String> urlList = new ArrayList<>();
        for(int i = 0; i < sites.getSites().size() ; i++){
            urlList.add(sites.getSites().get(i).getUrl());
        }
        return urlList;
    }

    @Bean
    public static List<String> getNameListIndexing(SitesList sites) {
        List<String> nameList = new ArrayList<>();
        for(int i = 0; i < sites.getSites().size() ; i++){
            nameList.add(sites.getSites().get(i).getName());
        }
        return nameList;
    }
}
