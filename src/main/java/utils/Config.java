package utils;

import lombok.Getter;
import lombok.Setter;
import model.User;

import java.net.URL;
import java.util.Properties;

public class Config {

    private static final String DB_URL = "dbUrl";
    private static final String DB_USERNAME = "dbUser";
    private static final String DB_PASSWORD = "dbPass";

    @Setter private static User loggedUser;

    private static Config instance;

    private Config() {}

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

}
