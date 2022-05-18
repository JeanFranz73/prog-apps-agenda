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
    private Long id;
    private String username;
    private Pessoa pessoa;
    private CargoEnum cargo;
    private String password;

    public User(Long id, String username, Pessoa pessoa, CargoEnum cargo) {
        this.id = id;
        this.username = username;
        this.pessoa = pessoa;
        this.cargo = cargo;
    }
}
