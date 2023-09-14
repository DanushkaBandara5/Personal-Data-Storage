package lk.ijse.app.personal.business.custom.impl;

import lk.ijse.app.personal.business.custom.PersonBO;
import lk.ijse.app.personal.business.exception.DuplicateEntryException;
import lk.ijse.app.personal.business.exception.RecordNotFundException;
import lk.ijse.app.personal.business.util.Transformer;
import lk.ijse.app.personal.dao.custom.PersonDAO;
import lk.ijse.app.personal.dto.PersonDTO;
import lk.ijse.app.personal.entity.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//this class is the implementation of the PersonBO interface
@Service
@Transactional
public class PersonBOImpl implements PersonBO {
    private final PersonDAO personDAO;
    private final Transformer transformer;

    public PersonBOImpl(PersonDAO personDAO, Transformer transformer) {
        this.personDAO = personDAO;
        this.transformer = transformer;
    }

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        // this method is used to save the entity in the database
        List<Person> savedList = personDAO.findAll();
        for (Person person : savedList) { //business validation of given details
            if (person.getContact().equals(personDTO.getContact()) || person.getEmail().equalsIgnoreCase(personDTO.getEmail())) {
                throw new DuplicateEntryException("Entered Data Already Exists");
            }
        }
        return transformer.fromPersonEntity(personDAO.save(transformer.toPersonEntity(personDTO)));

    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO) {
        if (!personDAO.existById(personDTO.getId())) { //check this id is already exist in the database
            throw new RecordNotFundException(personDTO.getId() + " Not exists");
        }
        List<Person> savedList = personDAO.findAll();
        for (Person person : savedList) { //business validation of given details
            if (person.getId() != personDTO.getId() && (person.getContact().equals(personDTO.getContact()) || person.getEmail().equalsIgnoreCase(personDTO.getEmail()))) {
                throw new DuplicateEntryException("Entered Data Already Exists");
            }
        }
        //return converted PersonDTO
        return transformer.fromPersonEntity(personDAO.update(transformer.toPersonEntity(personDTO)));
    }

    @Override
    public void deletePersonById(int id) {
        if (!personDAO.existById(id)) { //check this id is already exist in the database
            throw new RecordNotFundException(id + " Not exists");
        }
        personDAO.deleteById(id);
    }

    @Override
    public List<PersonDTO> findPersonByQuery(String query) {
        //this method return all the entity list as PersonDTO list,  which attributes are match with given string
        return personDAO.filterEntity(query).stream().map(transformer::fromPersonEntity).collect(Collectors.toList());

    }

    @Override
    public Optional<PersonDTO> getPersonById(int id) {
        if (!personDAO.existById(id)) { //check this id is already exist in the database
            throw new RecordNotFundException(id + " Not Exists");
        }
        //return the entity which matching to the given id
        return personDAO.findById(id).map(transformer::fromPersonEntity);
    }
}
