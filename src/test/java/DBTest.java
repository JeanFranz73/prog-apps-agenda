import utils.CargoEnum;

public class DBTest {
    public static void main(String[] args) throws Exception {
        CargoEnum cargoEnum = CargoEnum.MEDICO;
        System.out.println(cargoEnum.getId());
        System.out.println(cargoEnum.getName());
    }
}
