package DAO.impl;

import DAO.HotelDAO;
import DAO.OrderDAO;
import DAO.RoomDAO;
import DAO.UserDAO;
import Entities.Hotel;
import Entities.Order;
import Entities.Room;
import Entities.TypeHotel.TypeHotel;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static ConnectionDB.ConnectionManager.getConnection;

public class OrderDAOImpl implements OrderDAO {
    private static volatile OrderDAO INSTANCE = null;

    private static final String saveOrder = "INSERT INTO 'order' (HOTEL, ROOM, 'USER', DATE_FROM, DATE_BEFOR, PRICE_ALL) VALUES (?,?,?,?,?,?)";
    private static final String updateOrder = "UPDATE 'order' SET HOTEL=?, ROOM=?, 'USER'=?, DATE_FROM=?, DATE_BEFOR=?, PRICE_ALL=? WHERE ID=?";
    private static final String getOrder = "SELECT * FROM 'order' WHERE ID=?";
    private static final String deleteOrder = "DELETE FROM 'order' WHERE ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDel;
    private ResultSet rs;

    {
        try {
            psSave = getConnection().prepareStatement(saveOrder, 1);
            psUpdate = getConnection().prepareStatement(updateOrder);
            psGet = getConnection().prepareStatement(getOrder);
            psDel = getConnection().prepareStatement(deleteOrder);

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    private OrderDAOImpl() {
    }

    public static OrderDAO getInstance() {
        OrderDAO orderDAO = INSTANCE;
        if (orderDAO == null) {
            synchronized (OrderDAOImpl.class) {
                orderDAO = INSTANCE;
                if (orderDAO == null) {
                    INSTANCE = orderDAO = new OrderDAOImpl();
                }
            }
        }

        return orderDAO;
    }

    @Override
    public Order save(Order order) throws SQLException {
        psSave.setInt(1, (int)order.getHotel());
        psSave.setInt(2, (int) order.getRoom());
        psSave.setInt(3, (int) order.getUser());
        psSave.setDate(4, order.getDateFrom());
        psSave.setDate(5, order.getDateBefor());
        psSave.setDouble(6, order.getPriceAll());
        psSave.executeUpdate();

        rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            order.setId_order(rs.getInt(1));
        }
        close(rs);
        return order;
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
    public Order get(Serializable id) throws SQLException {
        psGet.setInt(1, (int)id);
        psGet.executeQuery();

        rs = psGet.getResultSet();
        if (rs.next()) {
            Order order = new Order();

            order.setId_order(rs.getInt(1));
            order.setHotel(rs.getInt(2));
            order.setRoom(rs.getInt(3));
            order.setUser(rs.getInt(4));
            order.setDateFrom(rs.getDate(5));
            order.setDateBefor(rs.getDate(6));
            order.setPriceAll(rs.getDouble(7));
            return order;
        }
        close(rs);
        return null;
    }

    @Override
    public int update(Order order) throws SQLException {
        psUpdate.setInt(1, (int) order.getHotel());
        psUpdate.setInt(2, (int) order.getRoom());
        psUpdate.setInt(3, (int) order.getUser());
        psUpdate.setDate(4, order.getDateFrom());
        psUpdate.setDate(5, order.getDateBefor());
        psUpdate.setDouble(6, order.getPriceAll());
        return psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDel.setInt(1, (int)id);
        return psDel.executeUpdate();
    }

}
