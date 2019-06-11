package com.ostanin.service.interfaces;

import org.springframework.ui.Model;

public interface IPageService {
    Model getPaginatedModel(int currentPage, int pageSize, Model model);
}
