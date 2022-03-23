import db.DB;
import utils.CargoEnum;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) throws Exception {
        CargoEnum cargoEnum = CargoEnum.MEDICO;
        System.out.println(cargoEnum.getId());
        System.out.println(cargoEnum.getName());
    }
}
