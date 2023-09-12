package lk.ijse.app.personal.business.custom;

import lk.ijse.app.personal.dto.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface PersonBO {
    PersonDTO savePerson(PersonDTO personDTO);
    void updatePerson(PersonDTO personDTO);
    void deletePersonById(int id);
    List<PersonDTO> findPersonByQuery(String query);
    Optional<PersonDTO> getPersonById(int id);



}
