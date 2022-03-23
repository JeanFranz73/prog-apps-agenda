package model;

import lombok.*;
import utils.CargoEnum;

@Getter
@Setter
public class User {
    private Integer id;
    private String username;
    private String nome;
    private Integer cpf;
    private Integer telefone;
    private CargoEnum cargo;
    private String password;

    public void setPassword(String oldPassword, String newPassword) {
        if (!newPassword.isEmpty()) {
            if (oldPassword.equals(getPassword())) {

            }
        }
    }
}
