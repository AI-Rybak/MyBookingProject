package DAO;

import Entities.User;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends DAO<User> {
    String getLogin (Serializable id) throws SQLException;
    User getByLogin(String login) throws SQLException;
    String getPassword (String password) throws SQLException;

}
