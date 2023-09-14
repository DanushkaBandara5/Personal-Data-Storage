package lk.ijse.app.personal.entity;

import lk.ijse.app.personal.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//this entity class is defined to represent entity in a user table
public class User implements SuperEntity {
    private String userName;
    private String password;
    private Role role;
}
