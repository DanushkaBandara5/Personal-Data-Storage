package lk.ijse.app.personal.dao.custom;

import lk.ijse.app.personal.dao.SuperDAO;
import lk.ijse.app.personal.entity.User;

import java.util.Optional;

// Generic class of SuperDAO extended with parameterized types
//The customized abstract methods are added in this interface
public interface UserDAO extends SuperDAO<User, String> {
    Optional<User> findAdmin(User user);
}
