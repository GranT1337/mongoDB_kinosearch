package com.ostanin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @Min(value = 0, message = "Правильно ID!")
    @Id
    private long id;
    @NotEmpty(message="Название фильма должно быть задано")
    private String title;
    @NotEmpty(message="Режиссёр должен быть указан")
    private Producer producers;
    @Pattern(regexp = "(\\d+.?,?\\d+)", message = "Неправильно указан балл")
    private double points;
}
