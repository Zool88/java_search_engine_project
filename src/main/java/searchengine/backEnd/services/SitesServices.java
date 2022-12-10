package searchengine.backEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchengine.backEnd.Repo.SitesRepository;
import searchengine.model.Sites;

import java.io.IOException;


@Service
public class SitesServices {

    @Autowired
    private SitesRepository sitesRepo;


    public Sites saveOne(Sites sites){
        return sitesRepo.save(sites);
    }


    public Sites getOne(Integer id) throws IOException {
        Sites sites = sitesRepo.findById(id).get();
        if (sites == null) {
            throw new IOException("Пользователь не найден");
        }
        return sites;
    }

    public String delete(Integer id) {
        sitesRepo.deleteById(id);
        return "ID с номером " + id + " удален !!!";
    }

    public String allDelete() {
        sitesRepo.deleteAll();
        return "Все пользователи удален !!!";
    }

    public Iterable<Sites> allUser() {
        return sitesRepo.findAll();
    }
}
