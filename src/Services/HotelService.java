package Services;

import Entities.Hotel;
import Entities.Room;

import java.io.Serializable;
import java.sql.SQLException;

public interface HotelService {
    Hotel save(Hotel hotel) throws SQLException;
    Hotel get(Serializable id) throws SQLException;
    int update(Hotel hotel) throws SQLException;
    int delete(Serializable id) throws SQLException;

}
