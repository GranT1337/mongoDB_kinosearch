package com.ostanin.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmForSite {
    private long id;
    private String title;
    private String producerName;
    private double points;
}
