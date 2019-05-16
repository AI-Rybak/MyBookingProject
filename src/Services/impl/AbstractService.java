package Services.impl;

import ConnectionDB.ConnectionManager;
import ConnectionDB.ManagerException;

import java.sql.SQLException;
import java.sql.Connection;

import static java.sql.Connection.TRANSACTION_REPEATABLE_READ;


public abstract class AbstractService {

    public void startTransaction() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);
    }

    public void commit() throws SQLException {
        ConnectionManager.getConnection().commit();
    }

    public Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    public void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            throw new ManagerException("rollback error");
        }
    }

}
