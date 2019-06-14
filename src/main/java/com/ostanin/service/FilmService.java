package com.ostanin.service;

import com.ostanin.dto.Film;
import com.ostanin.dto.Producer;
import com.ostanin.repository.FilmRepository;
import com.ostanin.repository.ProducerRepository;
import com.ostanin.service.interfaces.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class FilmService implements IFilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ProducerRepository producerRepository;

    public List<Film> findAllMongo() {
        return filmRepository.findAll();
    }

    @PostConstruct
    public void init() {
        filmRepository.deleteAll();
        producerRepository.deleteAll();
        producerRepository.save(new Producer(producerRepository.count() + 1 + "a" , "Дэвид Линч", LocalDate.of(1991, 6 , 7)));
        producerRepository.save(new Producer(producerRepository.count() + 1 + "a" , "Вонг Кар-Вай", LocalDate.of(1970, 5 , 20)));
        producerRepository.save(new Producer(producerRepository.count() + 1 + "a" , "Пол Томас Андерсон", LocalDate.of(1963, 1 , 4)));

        filmRepository.save(new Film(filmRepository.count() + 1, "Малхолланд Драйв",
                new Producer(1 + "a" , "Дэвид Линч", LocalDate.of(1991, 6 , 7)), 7.65));

        filmRepository.save(new Film(filmRepository.count() + 1, "Любовное настроение",
                new Producer(2 + "a" , "Вонг Кар-Вай", LocalDate.of(1970, 5 , 20)), 7.85));

        filmRepository.save(new Film(filmRepository.count() + 1, "Нефть",
                new Producer(3 + "a" , "Пол Томас Андерсон", LocalDate.of(1963, 1 , 4)), 7.76));
    }




}
