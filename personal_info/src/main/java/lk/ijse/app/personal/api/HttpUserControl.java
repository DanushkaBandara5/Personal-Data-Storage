package lk.ijse.app.personal.api;

import lk.ijse.app.personal.business.custom.UserBO;
import lk.ijse.app.personal.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class HttpUserControl {
    private final UserBO userBO;

    public HttpUserControl(UserBO userBO) {
        this.userBO = userBO;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@RequestBody @Valid UserDTO userDTO) {
        userBO.saveUser(userDTO);


    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/reset", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void modifyUser(@RequestBody @Valid UserDTO userDTO) {
        userBO.updateUser(userDTO);

    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void userLogin(@RequestBody @Valid UserDTO userDTO, HttpServletRequest request) {
        if (userBO.validateUser(userDTO)) {
            HttpSession session = request.getSession();
            session.setAttribute("userName", userDTO.getUserName());
        }

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/logout")
    public void userLogout(HttpServletRequest request) {
        request.getSession(false).invalidate();
    }
}
