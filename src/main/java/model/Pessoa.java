package model;

import lombok.*;

/**
 * @author jean.franz
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa extends Model<Pessoa> {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
}
