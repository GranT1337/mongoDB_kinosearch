package com.ostanin.repository;

import com.ostanin.dto.Film;

import java.util.List;

public interface IFilmJdbcRepository {
    void addFilm(Film film);
    boolean refreshFilm(Film film);
    boolean deleteFilm(long id);

}
