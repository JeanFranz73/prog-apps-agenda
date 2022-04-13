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
    private Long cpf;
    private Long telefone;
    private String email;
    private String endereco;
}
