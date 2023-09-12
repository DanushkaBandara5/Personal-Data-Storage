package lk.ijse.app.personal.business.util;

import lk.ijse.app.personal.dao.custom.PersonDAO;
import lk.ijse.app.personal.dto.PersonDTO;
import lk.ijse.app.personal.dto.UserDTO;
import lk.ijse.app.personal.entity.Person;
import lk.ijse.app.personal.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {
    private final ModelMapper modelMapper;

    public Transformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public PersonDTO fromPersonEntity(Person person){
        return modelMapper.map(person,PersonDTO.class);
    }
    public Person toPersonEntity(PersonDTO personDT){
        return modelMapper.map(personDT,Person.class);
    }
    public UserDTO fromUserEntity(User user){
        return modelMapper.map(user,UserDTO.class);
    }
    public User toUserEntity(UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }

}
