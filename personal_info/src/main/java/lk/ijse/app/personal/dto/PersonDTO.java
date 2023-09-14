package lk.ijse.app.personal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private int id;
    @NotBlank(message = "Name can't be empty")
    private String name;
    @NotNull(message = "Age cannot be empty")
    @Positive(message = "Age cannot be zero or negative")
    private int age;
    @NotBlank(message = "Name can't be empty")
    @Pattern(regexp = "^.+@.+$", message = "Invalid format")
    private String email;
    @NotBlank(message = "Contact can't be empty")
    @Pattern(regexp = "^0\\d{2}-\\d{7}$", message = "Invalid format")
    private String contact;
}
