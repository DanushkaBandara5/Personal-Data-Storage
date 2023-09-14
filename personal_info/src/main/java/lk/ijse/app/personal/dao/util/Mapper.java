package lk.ijse.app.personal.dao.util;

import lk.ijse.app.personal.entity.Person;
import lk.ijse.app.personal.entity.User;
import lk.ijse.app.personal.entity.enums.Role;
import org.springframework.jdbc.core.RowMapper;

// This class is used to define the identifier of lambda expressions (
// functional interface ofRowMapper used as data type)
public class Mapper {

    public static RowMapper<Person> PERSON_ROW_MAPPER = ((rs, rowNum) -> {
        return Person.builder().id(rs.getInt("id")).name(rs.getString("name"))
                .age(rs.getInt("age")).email(rs.getString("email")).contact(rs.getString("contact"))
                .build();
    });

    public static RowMapper<User> USER_ROW_MAPPER = ((rs, rowNum) -> {
        return User.builder().userName(rs.getString("user_name")).password(rs.getString("password")).role(Role.valueOf(rs.getString("role"))).build();
    });
}
