package com.ostanin.service;

import com.ostanin.dto.Film;
import com.ostanin.service.interfaces.IFilmService;
import com.ostanin.service.interfaces.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PageService implements IPageService {

    @Autowired
    IFilmService filmService;

    public Model getPaginatedModel(int currentPage, int pageSize, Model model) {
        Page<Film> filmPage = filmService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("filmPage", filmPage);

        int totalPages = filmPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return model;
    }
}
