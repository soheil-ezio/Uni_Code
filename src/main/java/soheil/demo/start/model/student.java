package soheil.demo.start.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class student {

    private long id;
    private String name;
    private String lastName;
    private int student_id_number;


}
