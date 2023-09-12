package lk.ijse.app.personal.business.custom.impl;

import lk.ijse.app.personal.business.custom.UserBO;
import lk.ijse.app.personal.business.exception.DuplicateEntryException;
import lk.ijse.app.personal.business.exception.RecordNotFundException;
import lk.ijse.app.personal.business.util.Transformer;
import lk.ijse.app.personal.dao.custom.UserDAO;
import lk.ijse.app.personal.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserBOImpl implements UserBO {
    private final UserDAO userDAO;
    private final Transformer transformer;


    public UserBOImpl(UserDAO userDAO, Transformer transformer) {
        this.userDAO = userDAO;
        this.transformer = transformer;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        if(userDAO.existById(userDTO.getUserName())){
            throw new DuplicateEntryException(userDTO.getUserName()+ " Already Registered User");
        }

        userDAO.save(transformer.toUserEntity(userDTO));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if(!userDAO.existById(userDTO.getUserName())){
            throw new RecordNotFundException(userDTO.getUserName()+ " Not a Registered Username");
        }

        userDAO.update(transformer.toUserEntity(userDTO));

    }
}
