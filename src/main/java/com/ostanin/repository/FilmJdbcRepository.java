package com.ostanin.repository;


import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.ostanin.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sample;

@Repository
public class FilmJdbcRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public boolean refreshFilm(Film film) {
          filmRepository.save(film);
//        Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(film.getId()));
//        Update update = new Update();
//        update.set("title", film.getTitle());
//        update.set("producer", film.getProducer());
//        update.set("points", film.getPoints());
//        mongoTemplate.updateMulti(query, update, Film.class);
        return true;
    }


    public double getAvgPoints() {
        double d = mongoTemplate.getDb().getCollection("film").aggregate(
                Arrays.asList(
                        Aggregates.group(null, Accumulators.avg("maxx", "$points"))
                )
        ).first().getDouble("maxx");
        System.out.println(d);
        return d;
    }

    public boolean deleteFilm(long id) {
        String sql = "delete from film where id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }

}
