package lk.ijse.app.personal.api;

import lk.ijse.app.personal.business.custom.UserBO;
import lk.ijse.app.personal.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class HttpUserControl {
    private final UserBO userBO;

    public HttpUserControl(UserBO userBO) {
        this.userBO = userBO;
    }

    @PostMapping("/signup")
    public void registerUser(@RequestBody UserDTO userDTO){
        userBO.saveUser(userDTO);


    }
    @PatchMapping("/reset")
    public void modifyUser(@RequestBody UserDTO  userDTO){
        userBO.updateUser(userDTO);

    }
}
