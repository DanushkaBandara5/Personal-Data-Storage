package lk.ijse.app.personal.business.custom.impl;

import lk.ijse.app.personal.business.custom.UserBO;
import lk.ijse.app.personal.business.exception.DuplicateEntryException;
import lk.ijse.app.personal.business.exception.InvalidAuthentications;
import lk.ijse.app.personal.business.exception.RecordNotFundException;
import lk.ijse.app.personal.business.util.Transformer;
import lk.ijse.app.personal.dao.custom.UserDAO;
import lk.ijse.app.personal.dto.UserDTO;
import lk.ijse.app.personal.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

//this class is the implementation of the UserBO interface
@Service
@Transactional
public class UserBOImpl implements UserBO {
    private final UserDAO userDAO;
    private final Transformer transformer;

    public UserBOImpl(UserDAO userDAO, Transformer transformer) {
        this.userDAO = userDAO;
        this.transformer = transformer;
    }

    public static String encryptThisString(String input) {
        //this method is used to encrypt the password using SHA-256 hashing algorithm
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        //this method is used to save a user in the database
        if (userDAO.existById(userDTO.getUserName())) { //check username is already exist in the database
            throw new DuplicateEntryException(userDTO.getUserName() + " Already Registered User");
        }
        userDTO.setPassword(encryptThisString(userDTO.getPassword())); //encrypt the password
        userDAO.save(transformer.toUserEntity(userDTO)); //save
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        // this method is used to modify the data for given username
        if (!userDAO.existById(userDTO.getUserName())) { //check username is already exist in the database
            throw new RecordNotFundException(userDTO.getUserName() + " Not a Registered Username");
        }

        userDAO.update(transformer.toUserEntity(userDTO)); //update the details in database related to given data

    }

    @Override
    public Boolean validateUser(UserDTO userDTO) {
        //this method is used to check weather current user is in the database and match the encrypted password
        if (!userDAO.existById(userDTO.getUserName())) { //check username is already exist in the database
            throw new InvalidAuthentications("Invalid User Name");
        }
        User registeredUser = userDAO.findById(userDTO.getUserName()).get();
        //compare the passwords
        if (registeredUser.getPassword().equals(encryptThisString(userDTO.getPassword()))) {
            return true;
        } else {
            throw new InvalidAuthentications("Invalid Password");
        }


    }

    @Override
    public List<UserDTO> filterUser(String query) {
        //this method return all the entity list as UserDTO list, which attributes are match with given string
        return userDAO.filterEntity(query).stream().map(transformer::fromUserEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String userName) {
        //check this id is already exist in the database
        if (!userDAO.existById(userName)) {
            throw new RecordNotFundException(userName + " Not Exists");
        }
        userDAO.deleteById(userName);

    }

    @Override
    public boolean validateAdmin(UserDTO userDTO) {
        // this method is used to check given details are represent the admin user
        boolean result = false;
        if (!userDAO.existById(userDTO.getUserName())) { //check username exist or not
            throw new InvalidAuthentications("Invalid UserName");
        }
        if (!userDAO.findAdmin(transformer.toUserEntity(userDTO)).isPresent()) { // check given details are related to the admin
            throw new InvalidAuthentications("Invalid UserName");
        }
        //check password match with the database records
        if (userDAO.findAdmin(transformer.toUserEntity(userDTO)).get().getPassword().equals(encryptThisString(userDTO.getPassword()))) {
            result = true;
        } else {
            throw new InvalidAuthentications("Invalid Password");

        }
        return result;
    }
}
