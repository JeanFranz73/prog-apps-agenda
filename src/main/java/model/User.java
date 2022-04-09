package model;

import lombok.*;
import utils.CargoEnum;

/**
 * @author jean.franz
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Model<User> {
    private Integer id;
    private String username;
    private Pessoa pessoa;
    private CargoEnum cargo;
    private String password;
}
