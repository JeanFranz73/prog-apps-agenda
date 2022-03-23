package utils;

import lombok.Getter;

@Getter
public enum CargoEnum {
    ATENDENTE(1, "Atendente"),
    MEDICO(2, "Médico");

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
}

