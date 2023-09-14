package lk.ijse.app.personal.dao.custom;

import lk.ijse.app.personal.dao.SuperDAO;
import lk.ijse.app.personal.entity.Person;
// Generic class of SuperDAO extended with parameterized types
//The customized abstract methods are added in this interface
public interface PersonDAO extends SuperDAO<Person, Integer> {
}
