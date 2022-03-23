package utils;

import lombok.Getter;

import java.net.URL;
import java.util.Properties;

public class Config {

    private static final String DB_URL = "dbUrl";
    private static final String DB_USERNAME = "dbUser";
    private static final String DB_PASSWORD = "dbPass";

    private static Config instance;

    public static Config getInstance(){
        if (instance == null) {
        }
        return instance;
    }

}
