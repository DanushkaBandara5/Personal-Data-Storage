package lk.ijse.app.personal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person implements SuperEntity {
    private int id;
    private String name;
    private int age;
    private String email;
    private String contact;
}
