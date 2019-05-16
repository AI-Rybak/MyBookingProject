package DAO.impl;

import DAO.*;
import Entities.Order;
import Entities.Reservation;
import Entities.Search;
import Entities.User;

import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ConnectionDB.ConnectionManager.getConnection;

public class ReservationDAOImpl implements ReservationDAO {
    private static volatile ReservationDAO INSTANCE = null;

    private static final String saveReservation = "INSERT INTO reservation (HOTEL, ROOM, QUANTITY_ROOM, DATE_FROM, DATE_BEFOR, PRISE_ALL, USER) VALUES (?,?,?,?,?,?,?)";
    private static final String updateReservation = "UPDATE reservation SET HOTEL=?, ROOM=?, QUANTITY_ROOM=?, DATE_FROM=?, DATE_BEFOR=?, PRISE_ALL=? WHERE ID=?";
    private static final String getReservation = "SELECT * FROM reservation WHERE ID=?";
    private static final String deleteReservation = "DELETE FROM reservation WHERE ID=?";
    private static final String returnReservationUser = "SELECT * FROM reservation WHERE reservation.USER=?";
    private static final String getReservationToSearc = "SELECT * FROM reservation WHERE ROOM=? AND (? <= DATE_FROM OR ? >= DATE_BEFOR)"; // есть ли пересечение линейных отрезков дат

    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDel;
    private PreparedStatement psRetResUser;
    private PreparedStatement psgetReservationToSearc;
    private ResultSet rs;

    {
        try {
            psSave = getConnection().prepareStatement(saveReservation, 1);
            psUpdate = getConnection().prepareStatement(updateReservation);
            psGet = getConnection().prepareStatement(getReservation);
            psDel = getConnection().prepareStatement(deleteReservation);
            psRetResUser = getConnection().prepareStatement(returnReservationUser);
            psgetReservationToSearc = getConnection().prepareStatement(getReservationToSearc);

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    private ReservationDAOImpl() {
    }

    public static ReservationDAO getInstance() {
        ReservationDAO reservationDAO = INSTANCE;
        if (reservationDAO == null) {
            synchronized (ReservationDAOImpl.class) {
                reservationDAO = INSTANCE;
                if (reservationDAO == null) {
                    INSTANCE = reservationDAO = new ReservationDAOImpl();
                }
            }
        }

        return reservationDAO;
    }

    @Override
    public Reservation save(Reservation reservation) throws SQLException {
        psSave.setInt(1, (int)reservation.getHotel());
        psSave.setInt(2, (int) reservation.getRoom());
        psSave.setInt(3, reservation.getQuantityRoom());
        psSave.setDate(4, reservation.getDateFrom());
        psSave.setDate(5, reservation.getDateBefor());
        psSave.setDouble(6, reservation.getPriceAll());
        psSave.setInt(7,(int) reservation.getUser());

        psSave.executeUpdate();

        rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            reservation.setId_reservation(rs.getInt(1));
        }
        close(rs);
        return reservation;
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
    public Reservation get(Serializable id) throws SQLException {
        psGet.setInt(1, (int)id);
        psGet.executeQuery();

        rs = psGet.getResultSet();
        if (rs.next()) {
            Reservation reservation = new Reservation();

            reservation.setId_reservation(rs.getInt(1));
            reservation.setHotel(rs.getInt(2));
            reservation.setRoom(rs.getInt(3));
            reservation.setQuantityRoom(rs.getInt(4));
            reservation.setDateFrom(rs.getDate(5));
            reservation.setDateBefor(rs.getDate(6));
            reservation.setPriceAll(rs.getDouble(7));
            reservation.setUser(rs.getInt(8));
            return reservation;
        }
        close(rs);
        return null;
    }

    @Override
    public int update(Reservation reservation) throws SQLException {
        psUpdate.setInt(1, (int) reservation.getHotel());
        psUpdate.setInt(2, (int) reservation.getRoom());
        psUpdate.setInt(3,reservation.getQuantityRoom());
        psUpdate.setDate(4, reservation.getDateFrom());
        psUpdate.setDate(5, reservation.getDateBefor());
        psUpdate.setDouble(6, reservation.getPriceAll());
        psUpdate.setInt(7, (int) reservation.getUser());
        return psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDel.setInt(1, (int)id);
        return psDel.executeUpdate();
    }

    @Override
    public List<Reservation> returnReservationUser (Serializable id_user) throws SQLException {
        List<Reservation> list = new ArrayList<>();
        psRetResUser.setInt(1, (int) id_user);
        psRetResUser.executeQuery();
        rs = psGet.getResultSet();
        if (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setId_reservation(rs.getInt(1));
            reservation.setHotel(rs.getInt(2));
            reservation.setRoom(rs.getInt(3));
            reservation.setQuantityRoom(rs.getInt(4));
            reservation.setDateFrom(rs.getDate(5));
            reservation.setDateBefor(rs.getDate(6));
            reservation.setPriceAll(rs.getDouble(7));
            reservation.setUser(rs.getInt(8));
            list.add(reservation);
        }
        close(rs);
        return list;
    }

    public List<Reservation> getReservationToSearc (Serializable roomID, Date dateFrom, Date dateBefor) throws SQLException {
        List<Reservation> list = new ArrayList<>();
        psgetReservationToSearc.setInt(1, (int)roomID);
        psgetReservationToSearc.setDate(2, dateFrom);
        psgetReservationToSearc.setDate(3, dateBefor);
        psgetReservationToSearc.executeQuery();
        rs = psgetReservationToSearc.getResultSet();
        if (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setId_reservation(rs.getInt(1));
            reservation.setHotel(rs.getInt(2));
            reservation.setRoom(rs.getInt(3));
            reservation.setQuantityRoom(rs.getInt(4));
            reservation.setDateFrom(rs.getDate(5));
            reservation.setDateBefor(rs.getDate(6));
            reservation.setPriceAll(rs.getDouble(7));
            reservation.setUser(rs.getInt(8));
            list.add(reservation);
        }
        close(rs);
        return list;


    }

}
