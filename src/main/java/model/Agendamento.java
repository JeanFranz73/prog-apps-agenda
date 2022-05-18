package model;

import lombok.*;

/**
 * @author jean.franz
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento extends Model<Agendamento> {
    private Long id;
    private User cadastrante;
    private Pessoa paciente;
    private User medico;
    private String descricao;
    private String horarioAgendado;
    private String dataRegistro;
}
