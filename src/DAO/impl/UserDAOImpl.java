package DAO.impl;

import ConnectionDB.ConnectionManager;
import DAO.UserDAO;
import Entities.User;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


import java.io.Serializable;
import java.sql.*;

import static ConnectionDB.ConnectionManager.*;


@Getter
@Setter
@NotNull
public class UserDAOImpl implements UserDAO {
    private static volatile UserDAO INSTANCE = null;

    private static final String saveUser = "INSERT INTO user (NAME, SURNAME, MAIL, LOGIN, PASSWORD) VALUES (?,?,?,?,?)";
    private static final String updateUser = "UPDATE user SET NAME=?, SURNAME=?, MAIL=?, LOGIN=?, PASSWORD=? WHERE ID=?";
    private static final String getUser = "SELECT * FROM user WHERE ID=?";
    private static final String deleteUser = "DELETE FROM user WHERE ID=?";
    private static final String getLogin = "SELECT LOGIN FROM user WHERE ID=?";
    private static final String getByLogin = "SELECT * FROM user WHERE LOGIN=?";
    private static final String getPassword = "SELECT PASSWORD FROM user WHERE ID=?";


    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDel;
    private PreparedStatement psgetLogin;
    private PreparedStatement psgetByLogin;
    private PreparedStatement psgetPassword;
    private ResultSet rs;

    {
        try {
            psSave = getConnection().prepareStatement(saveUser, 1);
            psGet = getConnection().prepareStatement(getUser);
            psUpdate = getConnection().prepareStatement(updateUser);
            psDel = getConnection().prepareStatement(deleteUser);
            psgetLogin = getConnection().prepareStatement(getLogin);
            psgetLogin = getConnection().prepareStatement(getByLogin);
            psgetLogin = getConnection().prepareStatement(getPassword);

        }catch (SQLException ex) {
            ex.printStackTrace();
        }



    }



    private UserDAOImpl() {
    }

    public static UserDAO getInstance() {
        UserDAO userDAO = INSTANCE;
        if (userDAO == null) {
            synchronized (UserDAOImpl.class) {
                userDAO = INSTANCE;
                if (userDAO == null) {
                    INSTANCE = userDAO = new UserDAOImpl();
                }
            }
        }

        return userDAO;
    }

    @Override
    public User save(User user) throws SQLException {
        psSave.setString(1, user.getNameUser());
        psSave.setString(2, user.getSurnameUser());
        psSave.setString(3, user.getMail());
        psSave.setString(4, user.getLogin());
        psSave.setString(5, user.getPassword());
        psSave.executeUpdate();

        rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            user.setId_user(rs.getInt(1));
        }
        close(rs);
        return user;
    }

   protected void close(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(Serializable id) throws SQLException {
        psGet.setInt(1, (int)id);
        psGet.executeQuery();
        rs = psGet.getResultSet();
        if (rs.next()) {
            User user = new User();
            user.setId_user(rs.getInt(1));
            user.setNameUser(rs.getString(2));
            user.setSurnameUser(rs.getString(3));
            user.setMail(rs.getString(4));
            user.setLogin(rs.getString(5));
            user.setPassword(rs.getString(6));
            return user;
        }
        close(rs);
        return null;
    }

    @Override
    public int update(User user) throws SQLException {
        psUpdate.setString(1, user.getNameUser());
        psUpdate.setString(2, user.getSurnameUser());
        psUpdate.setString(3, user.getMail());
        psUpdate.setString(4, user.getLogin());
        psUpdate.setString(5, user.getPassword());
        psUpdate.setInt(6, (int) user.getId_user());
        return psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {

        psDel.setInt(1, (int)id);
        return psDel.executeUpdate();
    }

    @Override
    public String getLogin (Serializable id) throws SQLException {
        psGet.setInt(1, (int)id);
        psGet.executeQuery();
        rs = psGet.getResultSet();
        if (rs.next()) {
            return rs.getString(1);
        }
        close(rs);
        return null;
    }
    @Override
    public User getByLogin(String login) throws SQLException {
        psgetByLogin.setString(1, login);
        psgetByLogin.executeQuery();
        rs = psgetByLogin.getResultSet();
        if (rs.next()) {
            User user = new User();
            user.setId_user(rs.getInt(1));
            user.setNameUser(rs.getString(2));
            user.setSurnameUser(rs.getString(3));
            user.setMail(rs.getString(4));
            user.setLogin(rs.getString(5));
            user.setPassword(rs.getString(6));
            return user;
        }
        close(rs);
        return null;
    }

    @Override
    public String getPassword (String password) throws SQLException {
        psGet.setString(1, password);
        psGet.executeQuery();
        rs = psGet.getResultSet();
        if (rs.next()) {
            return rs.getString(1);
        }
        close(rs);
        return null;
    }


}
