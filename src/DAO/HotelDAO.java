package DAO;


import Entities.Hotel;
import Entities.Room;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface HotelDAO extends DAO<Hotel>{
    //возвращает всех комнаты в гостинице
    List<Integer> allRoomHotel(Serializable id_Hotel) throws SQLException;
    Integer getIdHotelByRoom(Serializable id_Hotel) throws SQLException;
    public List<Integer> allRoomHotelReport (Serializable id_Hotel) throws SQLException;

}
