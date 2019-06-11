package com.ostanin.controller;

import com.ostanin.Exception.NotNormalAddFilmException;
import com.ostanin.dto.Film;
import com.ostanin.repository.FilmRepository;
import com.ostanin.repository.IFilmJdbcRepository;
import com.ostanin.service.interfaces.IFilmService;
import com.ostanin.service.interfaces.IPageService;
import com.ostanin.service.interfaces.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    ISearchService searchService;

    @Autowired
    IFilmService filmService;

    @Autowired
    IFilmJdbcRepository filmJdbcRepository;

    @Autowired
    IPageService pageService;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        pageService.getPaginatedModel(currentPage,pageSize,model);
        model.addAttribute("ALL_FILMS", filmService.findAllMongo());
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
        filmRepository.save(new Film(filmRepository.count() + 1, title, producer, points));
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/editFilm")
    @ResponseBody
    public boolean refreshFilm(Film film) {
        return filmJdbcRepository.refreshFilm(film);
    }

    @GetMapping("/deleteFilm")
    @ResponseBody
    public boolean deleteFilm(long id) {
        return filmJdbcRepository.deleteFilm(id);
    }
}
