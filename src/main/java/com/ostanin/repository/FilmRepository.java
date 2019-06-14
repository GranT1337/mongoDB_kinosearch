package com.ostanin.repository;

import com.ostanin.dto.Film;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository  extends MongoRepository<Film, Long> {
    List<Film> findByTitleLike(String title);
    List<Film> findAllByOrderByPointsDesc();
    void deleteById(Long id);
    //List<Film> findByProducer(String producer);
    List<Film> findByTitleOrId(String title, long id);
    List<Film> findByTitle(String title);
    List<Film> findByPointsGreaterThan(double number);
}
