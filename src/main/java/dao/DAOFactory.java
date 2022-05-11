package dao;

public class DAOFactory {

    public static final String sqlType = "sqlite";

    private static DAOFactory instance;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public static UserDAO getUserDAO() {
        if (isMariaDb()) {
            return UserMariaDbDAO.getInstance();
        } else {
            return UserDAOImpl.getInstance();
        }
    }

    public static PessoaDAO getPessoaDAO() {
        if (isMariaDb()) {
            return PessoaMariaDbDAO.getInstance();
        } else {
            return PessoaDAOImpl.getInstance();
        }
    }

    private static boolean isMariaDb() {
        return sqlType.equalsIgnoreCase("mariadb");
    }

}
