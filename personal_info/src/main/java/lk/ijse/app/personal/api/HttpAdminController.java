package lk.ijse.app.personal.api;

import lk.ijse.app.personal.business.custom.UserBO;
import lk.ijse.app.personal.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class HttpAdminController {
    private final UserBO userBO;

    public HttpAdminController(UserBO userBO) {
        this.userBO = userBO;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userName}")
    public void deleteUser(@PathVariable String userName) {
        userBO.deleteUser(userName);
    }

    @GetMapping()
    public List<UserDTO> getUsers(@RequestParam(name = "q", required = false) String query) {
        if (query == null) query = "";
        query = "%" + query + "%";
        return userBO.filterUser(query);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public void adminLogin(@RequestBody @Valid UserDTO userDTO) {
        userBO.validateAdmin(userDTO);


    }
}
