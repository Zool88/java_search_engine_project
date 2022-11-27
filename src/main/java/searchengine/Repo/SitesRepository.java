package searchengine.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.Sites;

@Repository
public interface SitesRepository extends CrudRepository<Sites,Long> {
}
