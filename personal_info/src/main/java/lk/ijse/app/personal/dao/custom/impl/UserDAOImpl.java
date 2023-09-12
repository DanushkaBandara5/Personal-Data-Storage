package lk.ijse.app.personal.dao.custom.impl;

import lk.ijse.app.personal.dao.custom.UserDAO;
import lk.ijse.app.personal.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import static lk.ijse.app.personal.dao.util.Mapper.USER_ROW_MAPPER;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
@Repository
public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User entity) {
        jdbcTemplate.update("insert into user (user_name,password) values (?,?)",entity.getUserName(),entity.getPassword());
        return entity;
    }

    @Override
    public User update(User entity) {
        jdbcTemplate.update("update user set password=? where user_name=?",entity.getPassword(),entity.getUserName());
        return entity;
    }

    @Override
    public void deleteById(String name) {
        jdbcTemplate.update("delete from user where user_name=?",name);

    }

    @Override
    public Optional<User> findById(String userName) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from user where user_name=?", USER_ROW_MAPPER, userName));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<User> filterEntity(String query) {
        return jdbcTemplate.query("select * from user",USER_ROW_MAPPER);
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(*) from user", Long.class);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from user",USER_ROW_MAPPER);
    }

    @Override
    public boolean existById(String userName) {

        return findById(userName).isPresent();
    }
}