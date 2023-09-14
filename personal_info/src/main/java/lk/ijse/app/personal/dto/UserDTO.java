package lk.ijse.app.personal.dto;

import lk.ijse.app.personal.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "UserName Can't be empty")
    private String userName;
    @NotBlank(message = "Password Can't be empty")
    @Length(min = 5, message = "Password length should be greater than 5")
    private String password;
    @NotNull(message = "Role cannot be Null")
    private Role role;
}
