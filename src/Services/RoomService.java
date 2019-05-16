package Services;

import Entities.Room;

import java.io.Serializable;
import java.sql.SQLException;

public interface RoomService {
    Room save(Room room) throws SQLException;
    Room get(Serializable id) throws SQLException;
    int update(Room room) throws SQLException;
    int delete(Serializable id) throws SQLException;


}
