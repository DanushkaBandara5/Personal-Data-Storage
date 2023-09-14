package lk.ijse.app.personal.dao.custom.impl;

import lk.ijse.app.personal.dao.custom.UserDAO;
import lk.ijse.app.personal.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static lk.ijse.app.personal.dao.util.Mapper.USER_ROW_MAPPER;

//This class is the implementation of UserDAO interface
@Repository
public class UserDAOImpl implements UserDAO {
    // this JdbcTemplate is provided by spring jdbc project  to deal with databases
    private final JdbcTemplate jdbcTemplate;

    //Constructor through dependencies injects is used  as best practise
    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User entity) {
        // in this method user entity is saved in database
        //finally return the saved user entity

        jdbcTemplate.update("insert into user (user_name,password,role) values (?,?,?)", entity.getUserName(), entity.getPassword(), entity.getRole().toString());
        return entity;
    }

    @Override
    public User update(User entity) {
        //this method is used to modify the user entity by primary key(userName)
        //finally return the modified person entity
        jdbcTemplate.update("update user set password=?,role=? where user_name=?", entity.getPassword(), entity.getRole().toString(), entity.getUserName());
        return entity;
    }

    @Override
    public void deleteById(String name) {
        //this method is used to delete the user entity from the database using its primary key(userName)
        jdbcTemplate.update("delete from user where user_name=?", name);

    }

    @Override
    public Optional<User> findById(String userName) {
        //this method return the user entity,and it is wrap up by optional
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from user where user_name=?", USER_ROW_MAPPER, userName));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> filterEntity(String query) {
        //this class is used to find the list of user entities which has a part like provided string
        return jdbcTemplate.query("select * from user", USER_ROW_MAPPER);
    }

    @Override
    public long count() {
        //this method is used to count the number of entitites in the database user table
        return jdbcTemplate.queryForObject("select count(*) from user", Long.class);
    }

    @Override
    public List<User> findAll() {
        //this method return all the list of entities in the database user table
        return jdbcTemplate.query("select * from user", USER_ROW_MAPPER);
    }

    @Override
    public boolean existById(String userName) {
        // this is checked, the given primary key is existed in the database
        return findById(userName).isPresent();
    }

    @Override
    public Optional<User> findAdmin(User user) {
        //this method return the user entity which has role=admin and  username=given entity username
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from user where role=? and user_name=?", USER_ROW_MAPPER, "ADMIN", user.getUserName()));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}