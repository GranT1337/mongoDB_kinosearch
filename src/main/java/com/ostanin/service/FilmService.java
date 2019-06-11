package com.ostanin.service;

import com.ostanin.dto.Film;
import com.ostanin.repository.FilmJdbcRepository;
import com.ostanin.repository.FilmRepository;
import com.ostanin.repository.IFilmJdbcRepository;
import com.ostanin.service.interfaces.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Service
public class FilmService implements IFilmService {

    @Autowired
    IFilmJdbcRepository repository;

    @Autowired
    private FilmRepository filmRepository;


    public Page<Film> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Film> list;
        List<Film> films = findAllMongo();

        if (films.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, films.size());
            list = films.subList(startItem, toIndex);
        }

        Page<Film> filmPage
                = new PageImpl<Film>(list, PageRequest.of(currentPage, pageSize), films.size());

        return filmPage;
    }

    public List<Film> findAllMongo() {
        return filmRepository.findAll();
    }

    @PostConstruct
    public void init() {
        filmRepository.deleteAll();
        filmRepository.save(new Film(filmRepository.count() + 1, "Малхолланд Драйв", "Дэвид Линч", 7.65));
        filmRepository.save(new Film(filmRepository.count() + 1, "Любовное настроение", "Вонг Кар-Вай", 7.85));
        filmRepository.save(new Film(filmRepository.count() + 1, "Нефть", "Пол Томас Андерсон", 7.76));
    }




}
