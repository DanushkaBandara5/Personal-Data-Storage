package lk.ijse.app.personal.business.custom;

import lk.ijse.app.personal.dto.UserDTO;

import java.util.List;

// this interface is used to maintained loose coupling between components
public interface UserBO {
    void saveUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    Boolean validateUser(UserDTO userDTO);

    List<UserDTO> filterUser(String query);

    void deleteUser(String Username);

    boolean validateAdmin(UserDTO userDTO);

}
