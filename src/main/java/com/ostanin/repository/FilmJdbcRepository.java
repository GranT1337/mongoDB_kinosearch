package com.ostanin.repository;


import com.ostanin.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FilmJdbcRepository implements IFilmJdbcRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    class FilmRowMapper implements RowMapper<Film> {
        @Override
        public Film mapRow(ResultSet resultSet, int i) throws SQLException {
            Film film = new Film();
            film.setId(resultSet.getLong("id"));
            film.setTitle(resultSet.getString("title"));
            film.setProducer(resultSet.getString("producer"));
            film.setPoints(resultSet.getDouble("points"));
            return film;
        }
    }

    public void addFilm(Film film) {
        String sql = "insert into film values( ? , ? , ? , ?)";
        jdbcTemplate.update(sql, film.getId(), film.getTitle(), film.getProducer(), film.getPoints());
    }

    public boolean refreshFilm(Film film) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(film.getId()));
        Update update = new Update();
        update.set("title", film.getTitle());
        update.set("producer", film.getProducer());
        update.set("points", film.getPoints());
        mongoTemplate.updateMulti(query, update, Film.class);
        return true;
    }

    public boolean deleteFilm(long id) {
        String sql = "delete from film where id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }

}
