package com.ostanin.service.interfaces;

import com.ostanin.dto.Film;

import java.util.List;

public interface ISearchService {
    List<Film> searchFilm(String searchString, String parameter);
}
