package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DB implements DAL{
    protected static Connection connection;
    private final String _HOST="localhost";
    private final String _PORT="3306";
    private final String _DB_NAME="product_history";
    private final String _USERNAME="root";
    private final String _PASSWORD="";

    public DB() {
        this.connection = getConnection();
    }

    private Connection getConnection() {
        if(connection==null){
            try {
                connection = DriverManager.getConnection("jdbc:mariadb://"+_HOST+"/"+_DB_NAME+"?characterEncoding=UTF-8", "root", null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("Ket noi database that bai!");
                System.exit(0);
            }
        }
        return connection;
    }
}
