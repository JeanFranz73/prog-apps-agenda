package utils;

import lombok.Getter;
import lombok.Setter;
import model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class Config {

    private static final String DB_URL = "dbUrl";
    private static final String DB_USERNAME = "dbUser";
    private static final String DB_PASSWORD = "dbPass";

    @Setter @Getter private static User loggedUser;

    private static Config instance;

    private Config() {}

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public static boolean isAdmin() {
        return loggedUser.getCargo().equals(CargoEnum.ADMIN);
    }

    public static String encryptPass(String pass) {
        try {
            return Base64.getEncoder().encodeToString(
                    MessageDigest.getInstance("SHA-512").digest(
                            pass.getBytes(StandardCharsets.UTF_8)
                    ));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
