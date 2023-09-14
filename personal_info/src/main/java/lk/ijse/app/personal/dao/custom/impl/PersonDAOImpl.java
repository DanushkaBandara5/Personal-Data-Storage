package lk.ijse.app.personal.dao.custom.impl;

import lk.ijse.app.personal.dao.custom.PersonDAO;
import lk.ijse.app.personal.entity.Person;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static lk.ijse.app.personal.dao.util.Mapper.PERSON_ROW_MAPPER;
//This class is the implementation of PersonalDAO interface
@Repository
public class PersonDAOImpl implements PersonDAO {
    // this JdbcTemplate is provided by spring jdbc project  to deal with databases
    private final JdbcTemplate jdbcTemplate;
    //Constructor through dependencies inject is used  as best practise
    public PersonDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Person save(Person entity) {
        /* in this method person entity is saved in database,
         we used auto generated id from database as
        primary key of the person entity
         finally this method return person entity with generated id*/
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement stm = con.prepareStatement("INSERT INTO person (name, age,email, contact) VALUES (?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, entity.getName());
            stm.setInt(2, entity.getAge());
            stm.setString(3, entity.getEmail());
            stm.setString(4, entity.getContact());
            return stm;
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
        return entity;

    }

    @Override
    public Person update(Person entity) {
        //this method is used to modify the person entity by primary key(id)
        //finally return the modified person entity
        jdbcTemplate.update("update person set name=? ,age=?,email=?,contact=? where id=?",
                entity.getName(), entity.getAge(), entity.getEmail(), entity.getContact(), entity.getId());
        return entity;
    }

    @Override
    public void deleteById(Integer id) {
        //this method is used to delete the entity from the database using its primary key(id)
        jdbcTemplate.update("delete from person where id=?", id);

    }

    @Override
    public Optional<Person> findById(Integer id) {
        //this method return the person entity,and it is wrap up by optional
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from person where id=?", PERSON_ROW_MAPPER, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<Person> filterEntity(String query) {
        //this class is used to find the list of personal entities which has a part like provided string
        return jdbcTemplate.query("select * from person where name like ? or age like ? or email like ? or contact like ?"
                , PERSON_ROW_MAPPER, query, query, query, query);
    }

    @Override
    public long count() {
        //this method is used to count the number of entitites in the database person table
        return jdbcTemplate.queryForObject("select count(*) from person", Long.class);
    }

    @Override
    public List<Person> findAll() {
        //this method return all the list of entities in the database person table
        return jdbcTemplate.query("select * from person", PERSON_ROW_MAPPER);
    }

    @Override
    public boolean existById(Integer id) {
        // this is checked, the given primary key is existed in the database
        return findById(id).isPresent();
    }
}
