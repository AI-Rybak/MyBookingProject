package ConnectionDB;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionManager {
    private static ResourceBundle resource = null;
    private static volatile boolean isDriverLoaded = false;
    static ThreadLocal<Connection> connection = new ThreadLocal<>();
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        // в папке resorse -> properties файл "dataBase",
        //с помощью ResourceBundle инициализируем поля класса ConnectionManager
        ResourceBundle resource = ResourceBundle.getBundle("booking_base");
        URL = resource.getString("url");
        USER = resource.getString("user");
        PASSWORD = resource.getString("password");

        try {
            //загружаем драйвер com.mysql.jdbc.Driver
            Class.forName(resource.getString("driver"));
            isDriverLoaded = true; //преводим флаг isDriverLoaded в положение true
        } catch (ClassNotFoundException ex) {
            LOGGER.info("Class.ConnectionManager -> ClassNotFoundException: Драйвер не загружен-------------", ex);
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws ManagerException {
        //проверка на то что драйвер com.mysql.jdbc.Driver загружен
        if(!isDriverLoaded) {
            throw new ManagerException("Class.ConnectionManager.getConnection() isDriverLoaded='false'->in Class.forName(resource.getString(\"driver\")): Драйвер не загружен");
        }
        try {
            if (connection.get() == null){ //connection тип ThreadLocal
                connection.set(DriverManager.getConnection(URL,USER,PASSWORD));
            }
            return connection.get();
        } catch (SQLException ex) {
            LOGGER.info("Class.ConnectionManager.getConnection() -> SQLException: Ошибка получения соединения-------------", ex);
            throw new ManagerException("Ошибка получения соединения" + ex.getMessage());
        }
    }

}
