package lk.ijse.app.personal.business.custom;

import lk.ijse.app.personal.dto.PersonDTO;

import java.util.List;
import java.util.Optional;

// this interface is used to maintained loose coupling between components
public interface PersonBO {
    PersonDTO savePerson(PersonDTO personDTO);

    PersonDTO updatePerson(PersonDTO personDTO);

    void deletePersonById(int id);

    List<PersonDTO> findPersonByQuery(String query);

    Optional<PersonDTO> getPersonById(int id);


}
