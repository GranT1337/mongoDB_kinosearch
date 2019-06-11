package com.ostanin.repository;

import com.ostanin.dto.Film;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository  extends MongoRepository<Film, Long> {
    List<Film> findByTitleLike(String title);
    List<Film> findByProducer(String producer);
}
