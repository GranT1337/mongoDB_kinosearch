package com.ostanin.dto;

import org.springframework.data.annotation.Id;
import java.time.LocalDate;


public class Producer {
    @Id
    private String id;
    private String name;
    private LocalDate dob;


    public Producer(String id, String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
