package lk.ijse.app.personal.dao.custom.impl;

import lk.ijse.app.personal.dao.custom.PersonDAO;
import lk.ijse.app.personal.entity.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import static lk.ijse.app.personal.dao.util.Mapper.PERSON_ROW_MAPPER;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
@Repository
public class PersonalDAOImpl  implements PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonalDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Person save(Person entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement stm = con.prepareStatement("INSERT INTO person (name, age,email, contact) VALUES (?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, entity.getName());
            stm.setInt(2, entity.getAge());
            stm.setString(3, entity.getEmail());
            stm.setString(4,entity.getContact());
            return stm;
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
        return entity;

    }

    @Override
    public Person update(Person entity) {
      jdbcTemplate.update("update person set name=? ,age=?,email=?,contact=? where id=?",
                entity.getName(),entity.getAge(),entity.getEmail(),entity.getContact(),entity.getId());
      return entity;
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("delete from person where id=?",id);

    }

    @Override
    public Optional<Person> findById(Integer id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from person where id=?",PERSON_ROW_MAPPER,id));
    }

    @Override
    public List<Person> filterEntity(String query) {
        return jdbcTemplate.query("select * from person where name like ? or age like ? or email like ? or contact like ?"
                ,PERSON_ROW_MAPPER,query,query,query,query);
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(*) from person", Long.class);
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person",PERSON_ROW_MAPPER);
    }

    @Override
    public boolean existById(Integer id) {
        return findById(id).isPresent();
    }
}
