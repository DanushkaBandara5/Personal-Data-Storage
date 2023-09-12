package lk.ijse.app.personal.business.custom;

import lk.ijse.app.personal.dao.custom.UserDAO;
import lk.ijse.app.personal.dto.UserDTO;

public interface UserBO {
    void saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
}
