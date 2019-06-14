package com.ostanin.controller;

import com.ostanin.dto.Film;
import com.ostanin.dto.FilmForSite;
import com.ostanin.repository.FilmJdbcRepository;
import com.ostanin.repository.FilmRepository;
import com.ostanin.service.interfaces.IFilmService;
import com.ostanin.service.interfaces.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    ISearchService searchService;

    @Autowired
    IFilmService filmService;

    @Autowired
    FilmJdbcRepository filmJdbcRepository;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/home")
    public String home(Model model) {
        List<FilmForSite> results = new ArrayList<>();
        filmService.findAllMongo().forEach(x -> results.add(new FilmForSite(x.getId(), x.getTitle(), x.getProducers().getName(), x.getPoints())));
        model.addAttribute("ALL_FILMS", results);
        return "/home";
    }

    @GetMapping("/searchFilm")
    public String searchFilm(Model model,
                             @RequestParam(required=false, value="q") String searchForm,
                             @RequestParam(required=false, value="options") String parameter) {
        model.addAttribute("filmList", searchService.searchFilm(searchForm, parameter));
        return "/searchFilm";
    }

    @PostMapping("/addFilm")
    public ModelAndView addFilm(@RequestParam("title") String title,
                                @RequestParam("producer") String producer,
                                @RequestParam("points") double points) {
        ModelAndView modelAndView = new ModelAndView();
        //filmRepository.save(new Film(filmRepository.count() + 1, title, producer, points));
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/allfilms")
    @ResponseBody
    public List<Film> getAllFilms() {
        return filmService.findAllMongo();
    }

    @GetMapping("/getmax")
    @ResponseBody
    public Film getMaxPoint() {
        return filmRepository.findAllByOrderByPointsDesc().get(0);
    }

    @GetMapping("/getavg")
    @ResponseBody
    public double getAvgPoint() {
        return filmJdbcRepository.getAvgPoints();
    }

    @GetMapping("/searchFilmOrIdJSON")
    @ResponseBody
    public List<Film> searchFilmOrIdJSON() {
        return filmRepository.findByTitleOrId("Нефть", 1L);
    }

    @GetMapping("/searchFilmJSON")
    @ResponseBody
    public List<Film> searchFilmJSON() {
        return filmRepository.findByTitle("Нефть");
    }

    @GetMapping("/greater")
    @ResponseBody
    public List<Film> greater() {
        return filmRepository.findByPointsGreaterThan(7.7);
    }

    @GetMapping("/updateGreater")
    @ResponseBody
    public List<Film> updateGreater() {
        List<Film> lastListFilm = filmRepository.findByPointsGreaterThan(7.7);
        lastListFilm.forEach(x -> x.setPoints(x.getPoints()+1));
        filmRepository.saveAll(lastListFilm);
        return  lastListFilm;
    }

//    @GetMapping("/editFilm")
//    @ResponseBody
//    public boolean refreshFilm(Film film) {
//        return filmJdbcRepository.refreshFilm(film);
//    }
//
    @GetMapping("/deleteFilm")
    @ResponseBody
    public boolean deleteFilm(long id) {
        filmRepository.deleteById(id);
        return true;
    }
}
