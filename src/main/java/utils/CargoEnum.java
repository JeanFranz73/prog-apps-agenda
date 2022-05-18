package utils;

import lombok.Getter;

@Getter
public enum CargoEnum {
    ADMIN(0, "Administrador"),
    ATENDENTE(1, "Atendente"),
    MEDICO(2, "Médico"),
    OFTALMOLOGISTA(3, "Oftalmolgista"),
    GASTRO(4, "Gastroenterologia"),
    OTORRINO(5, "Otorrinolaringologista");

    private final Integer id;
    private final String name;

    CargoEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    };

    public static CargoEnum getById(Integer id) {
        for (CargoEnum e : values()) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
