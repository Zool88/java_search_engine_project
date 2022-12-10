package searchengine.backEnd.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import searchengine.model.Sites;

import java.util.List;

@Repository
public interface QuerySiteServices extends JpaRepository<Sites, Integer> {

    @Query(value = "SELECT status FROM search_engine.site", nativeQuery = true)
    List<String> findAllContains();

}
