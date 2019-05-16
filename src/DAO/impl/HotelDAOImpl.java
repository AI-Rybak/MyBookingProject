package DAO.impl;

import DAO.HotelDAO;
import Entities.Hotel;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static ConnectionDB.ConnectionManager.getConnection;

public class HotelDAOImpl implements HotelDAO {
    private static volatile HotelDAO INSTANCE = null;

    private static final String saveHotel = "INSERT INTO hotel (HAME, TYPE, COUNTRY, CITY, STAR_FK) VALUES (?,?,?,?,?)";
    private static final String updateHotel = "UPDATE hotel SET HAME=?, TYPE=?, COUNTRY=?, CITY=?, STAR_FK=? WHERE ID_HOTEL=?";
    private static final String getHotel = "SELECT * FROM hotel WHERE ID_HOTEL=?";
    private static final String deleteHotel = "DELETE FROM hotel WHERE ID_HOTEL=?";
    private static final String allRoomHotel = "SELECT ROOM FROM room_in_hotel WHERE HOTEL=?";
    private static final String allRoomHotelReport = "SELECT * FROM room_in_hotel WHERE HOTEL=?";
    private static final String getIdHotel = "SELECT HOTEL FROM room_in_hotel WHERE ROOM=?";

    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDel;
    private PreparedStatement psAllRoomInHotel;
    private PreparedStatement psallRoomHotelReport;
    private PreparedStatement psgetIdHotel;

    private ResultSet rs;

    {
        try {
            psSave = getConnection().prepareStatement(saveHotel, 1);
            psGet = getConnection().prepareStatement(getHotel);
            psUpdate = getConnection().prepareStatement(updateHotel);
            psDel = getConnection().prepareStatement(deleteHotel);
            psAllRoomInHotel = getConnection().prepareStatement(allRoomHotel);
            psallRoomHotelReport = getConnection().prepareStatement(allRoomHotelReport);
            psgetIdHotel = getConnection().prepareStatement(getIdHotel);


        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    private HotelDAOImpl() {
    }

    public static HotelDAO getInstance() {
        HotelDAO hotelDAO = INSTANCE;
        if (hotelDAO == null) {
            synchronized (HotelDAOImpl.class) {
                hotelDAO = INSTANCE;
                if (hotelDAO == null) {
                    INSTANCE = hotelDAO = new HotelDAOImpl();
                }
            }
        }

        return hotelDAO;
    }

    @Override
    public Hotel save(Hotel hotel) throws SQLException {
        psSave.setString(1, hotel.getNameHotel());
        psSave.setInt(2, (int) hotel.getTypeHotel());
        psSave.setString(3, hotel.getLocationCountr());
        psSave.setString(4, hotel.getLocationCity());
        psSave.setInt(5,hotel.getStar());
        psSave.executeUpdate();

        rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            hotel.setId_Hotel(rs.getInt(1));
        }
        close(rs);
        return hotel;
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
    public Hotel get(Serializable id) throws SQLException {
        psGet.setInt(1, (int)id);
        psGet.executeQuery();

        rs = psGet.getResultSet();
        if (rs.next()) {
            Hotel hotel = new Hotel();
            hotel.setId_Hotel(rs.getInt(1));
            hotel.setNameHotel(rs.getString(2));
            hotel.setTypeHotel(rs.getInt(3));
            hotel.setLocationCountr(rs.getString(4));
            hotel.setLocationCity(rs.getString(5));
            hotel.setStar(rs.getInt(6));
            return hotel;
        }
        close(rs);
        return null;
    }

    @Override
    public int update(Hotel hotel) throws SQLException {

        psUpdate.setString(1, hotel.getNameHotel());
        psUpdate.setInt(2, (int) hotel.getTypeHotel());
        psUpdate.setString(3, hotel.getLocationCountr());
        psUpdate.setString(4, hotel.getLocationCity());
        psUpdate.setInt(5, hotel.getStar());
        psUpdate.setInt(6, (int) hotel.getId_Hotel());
        return psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {

        psDel.setInt(1, (int)id);
        return psDel.executeUpdate();
    }

    //надо проверить???
    @Override
    public List<Integer> allRoomHotel(Serializable id_Hotel) throws SQLException {
        List<Integer> aLLRoom = new LinkedList<>();
        psAllRoomInHotel.setInt(1, (int)id_Hotel);
        psAllRoomInHotel.executeQuery();

        rs = psAllRoomInHotel.getResultSet();
        while (rs.next()) {
            aLLRoom.add(rs.getInt(1));
        }
        return aLLRoom;
    }

    @Override
    public Integer getIdHotelByRoom(Serializable id_room) throws SQLException {
        Integer idHotel = null;
        psgetIdHotel.setInt(1, (int)id_room);
        psgetIdHotel.executeQuery();
        rs = psgetIdHotel.getResultSet();
        while (rs.next()) {
            idHotel = rs.getInt(1);
        }
        return idHotel;
    }

    //проверить!!!!!!!!!!!!!!!
    @Override
    public List<Integer> allRoomHotelReport (Serializable id_Hotel) throws SQLException {
        List<Integer> aLLRoom = new ArrayList<>();
        psallRoomHotelReport.setInt(1, (int)id_Hotel);
        psallRoomHotelReport.executeQuery();

        rs = psallRoomHotelReport.getResultSet();
        while (rs.next()) {
            aLLRoom.add(rs.getInt(2));
            aLLRoom.add(rs.getInt(3));
            aLLRoom.add(rs.getInt(4));
            aLLRoom.add(rs.getInt(5));
        }
        return aLLRoom;
    }

}
