package DAO;

import Entities.Hotel;
import Entities.Room;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RoomDAO extends DAO<Room> {
  Map<Date, Date> getAllDateReservat(Serializable idHotel, Serializable idRoom) throws SQLException;
  int getActualRoomQuantyty (Serializable id_Room) throws SQLException;
  int updateActualRoomQuantyty(Serializable id_Room, int quantityAll, int quantityActyalRoom) throws SQLException;
  int getAllRoomQuantyty (Serializable id_Room) throws SQLException;
  List<Room> getRoomType(Serializable idType) throws SQLException;
}
