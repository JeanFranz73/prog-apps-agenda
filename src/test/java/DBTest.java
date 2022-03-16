import dao.DB;

import java.io.IOException;
import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) throws Exception {
        Connection conn = new DB().connect();
        Main.main(args);
    }
}
