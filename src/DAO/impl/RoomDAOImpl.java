package DAO.impl;

import DAO.RoomDAO;
import Entities.Room;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static ConnectionDB.ConnectionManager.getConnection;

@Getter
@Setter
public class RoomDAOImpl implements RoomDAO {
    private static volatile RoomDAO INSTANCE = null;

    private static final String saveRoom = "INSERT INTO room (NAME, TYPE, PRICE) VALUES (?,?,?)";
    private static final String updateRoom = "UPDATE room SET NAME=?, TYPE=?, PRICE=? WHERE ID=?";
    private static final String getRoom = "SELECT * FROM room WHERE ID=?";
    private static final String deleteRoom = "DELETE FROM room WHERE ID=?";
    private static final String getAllDateReservat = "SELECT DATE_FROM, DATE_BEFOR FROM reservation WHERE HOTEL=? AND ROOM=?";
    private static final String getActualRoomQuantyty = "SELECT * FROM room_in_hotel WHERE ROOM=?";
    private static final String updateActualRoomQuantyty = "UPDATE room_in_hotel SET QUANTITY_ALL=?, ACTUAL_QUANTITY=? WHERE ROOM=?";
    private static final String getRoomType = "SELECT * FROM room WHERE TYPE=?";

    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDel;
    private PreparedStatement psgetAllDate;
    private PreparedStatement psgetActualRoomQuantyty;
    private PreparedStatement psupdateActualRoomQuantyty;
    private PreparedStatement psgetRoomType;
    private ResultSet rs;

    {
        try {
            psSave = getConnection().prepareStatement(saveRoom, 1);
            psGet = getConnection().prepareStatement(getRoom);
            psUpdate = getConnection().prepareStatement(updateRoom);
            psDel = getConnection().prepareStatement(deleteRoom);
            psgetAllDate = getConnection().prepareStatement(getAllDateReservat);
            psgetActualRoomQuantyty = getConnection().prepareStatement(getActualRoomQuantyty);
            psupdateActualRoomQuantyty = getConnection().prepareStatement(updateActualRoomQuantyty);
            psgetRoomType = getConnection().prepareStatement(getRoomType);


        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    private RoomDAOImpl() {
    }

    public static RoomDAO getInstance() {
        RoomDAO roomDAO = INSTANCE;
        if (roomDAO == null) {
            synchronized (RoomDAOImpl.class) {
                roomDAO = INSTANCE;
                if (roomDAO == null) {
                    INSTANCE = roomDAO = new RoomDAOImpl();
                }
            }
        }

        return roomDAO;
    }

    @Override
    public Room save(Room room) throws SQLException {
        psSave.setString(1, room.getNameRoom());
        psSave.setInt(2, (int) room.getTypeRoom());
        psSave.setDouble(3, room.getPrice());
        psSave.executeUpdate();

        rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            room.setId_Room(rs.getInt(1));
        }
        close(rs);
        return room;
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
    public Room get(Serializable id) throws SQLException {
        psGet.setInt(1, (int)id);
        psGet.executeQuery();

        rs = psGet.getResultSet();
        if (rs.next()) {
            Room room = new Room();
            room.setId_Room(rs.getInt(1));
            room.setNameRoom(rs.getString(2));
            room.setTypeRoom(rs.getInt(3));
            room.setPrice(rs.getDouble(4));
            return room;
        }
        close(rs);
        return null;
    }

    @Override
    public int update(Room room) throws SQLException {
        psUpdate.setString(1, room.getNameRoom());
        psUpdate.setInt(2, (int) room.getTypeRoom());
        psUpdate.setDouble(3, room.getPrice());
        psUpdate.setInt(4, (int) room.getId_Room());
        return psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {

        psDel.setInt(1, (int)id);
        return psDel.executeUpdate();
    }

//надо проверить???
    @Override
    public Map<Date, Date> getAllDateReservat(Serializable idHotel, Serializable idRoom) throws SQLException {
        Map<Date, Date> aLLDate = new TreeMap<>();
        psgetAllDate.setInt(1, (int)idHotel);
        psgetAllDate.setInt(2,(int) idRoom);
        psgetAllDate.executeQuery();
        rs = psgetAllDate.getResultSet();
        while (rs.next()) {
            aLLDate.put(rs.getDate(1),rs.getDate(2));
        }
        return aLLDate;
    }

    @Override
    public int getActualRoomQuantyty (Serializable id_Room) throws SQLException {
        psgetActualRoomQuantyty.setInt(1, (int) id_Room);
        psgetActualRoomQuantyty.executeQuery();
        rs = psgetActualRoomQuantyty.getResultSet();
        if (rs.next()) {
            return rs.getInt(2);
        } else return 0;
    }

    @Override
    public int getAllRoomQuantyty (Serializable id_Room) throws SQLException {
        psgetActualRoomQuantyty.setInt(1, (int) id_Room);
        psgetActualRoomQuantyty.executeQuery();
        rs = psgetActualRoomQuantyty.getResultSet();
        if (rs.next()) {
            return rs.getInt(4);
        } else return 0;
    }

    @Override
    public int updateActualRoomQuantyty(Serializable id_Room, int quantityAll, int quantityActyalRoom) throws SQLException {
        psupdateActualRoomQuantyty.setInt(1, quantityAll);
        psupdateActualRoomQuantyty.setInt(2, quantityActyalRoom);
        psupdateActualRoomQuantyty.setInt(3, (int) id_Room);
        return psUpdate.executeUpdate();
    }

    @Override
    public List<Room> getRoomType(Serializable idType) throws SQLException {
        List<Room> listRoom = new ArrayList<>();
        psgetRoomType.setInt(1, (int)idType);
        psgetRoomType.executeQuery();

        rs = psgetRoomType.getResultSet();
        if (rs.next()) {
            Room room = new Room();
            room.setId_Room(rs.getInt(1));
            room.setNameRoom(rs.getString(2));
            room.setTypeRoom(rs.getInt(3));
            room.setPrice(rs.getDouble(4));
            listRoom.add(room);

            return listRoom;
        }
        close(rs);
        return null;
    }

}
