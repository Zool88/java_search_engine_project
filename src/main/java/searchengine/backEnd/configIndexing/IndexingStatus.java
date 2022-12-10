package searchengine.backEnd.configIndexing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import searchengine.backEnd.configStatus.ConfigStatus;
import searchengine.backEnd.configStatus.ConfigStatusError;
import searchengine.backEnd.services.QuerySiteServices;

import java.util.List;

@Service
@ComponentScan
public class IndexingStatus {

    public IndexingStatus() {
    }

    @Autowired
    private QuerySiteServices querySiteServices;


    public Object getIndexingStatus() {

        List<String> status;
        status = querySiteServices.findAllContains();

        Object statuses = "";
        for (int i = 0; i < status.size(); i++) {
            if (status.get(i).equals("INDEXED") || status.get(i).equals("FAILED")){
                statuses = new ConfigStatus(true);
            } else if(status.get(i).equals("INDEXING")){
                statuses = new ConfigStatusError(false, "Индексация уже запущена");
            }
            break;
        }
        return statuses;
    }
}
