package searchengine.backEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchengine.backEnd.Repo.PageRepository;
import searchengine.model.Page;

import java.io.IOException;

@Service
public class PageServices {

    @Autowired
    private PageRepository pageRepository;

    public Page saveOne(Page sites){
        return pageRepository.save(sites);
    }


    public Page getOne(Integer id) throws IOException {
        Page page = pageRepository.findById(id).get();
        if (page == null) {
            throw new IOException("Пользователь не найден");
        }
        return page;
    }

    public String delete(Integer id) {
        pageRepository.deleteById(id);
        return "ID с номером " + id + " удален !!!";
    }

    public String allDelete() {
        pageRepository.deleteAll();
        return "Все пользователи удален !!!";
    }

    public Iterable<Page> allUser() {
        return pageRepository.findAll();
    }
}
