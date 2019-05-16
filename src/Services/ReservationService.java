package Services;

import Entities.Hotel;
import Entities.Reservation;
import Entities.Room;
import Entities.User;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReservationService {
    Reservation toReservation(Hotel hotel, Room room, User user, Date dateFrom, Date dateBefor, int quantityRoom) throws SQLException;
    Reservation get(Serializable id_reservation) throws SQLException;
    void update(Reservation toReservation) throws SQLException;
    int delete(Serializable id_reservation) throws SQLException;
    List<Reservation> returnReservationUser (Serializable userId) throws SQLException;


    }
