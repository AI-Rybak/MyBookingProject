package Services.impl;

import DAO.HotelDAO;
import DAO.ReservationDAO;
import DAO.RoomDAO;
import DAO.UserDAO;
import DAO.impl.HotelDAOImpl;
import DAO.impl.ReservationDAOImpl;
import DAO.impl.RoomDAOImpl;
import DAO.impl.UserDAOImpl;
import Entities.Hotel;
import Entities.Reservation;
import Entities.Room;
import Entities.User;
import Services.ReservationService;
import Services.ServiceException;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ResevationServiceImpl extends AbstractService implements ReservationService {
    private static volatile ReservationService INSTANCE = null;
    //примечание новое не из IDEA

    //примечание  что то будет
    //примечание2

    private ReservationDAO reservationDAO = ReservationDAOImpl.getInstance();
    private UserDAO userDAO = UserDAOImpl.getInstance();
    private HotelDAO hotelDAO = HotelDAOImpl.getInstance();
    private RoomDAO roomDAO = RoomDAOImpl.getInstance();
    //примечание
    //примечание2

    @Override
    public Reservation toReservation (Hotel hotel, Room room, User user, Date dateFrom, Date dateBefor, int quantityRoom){
        Reservation reservation = new Reservation();
        try {
            startTransaction();
            if(hotel!=null) {
                reservation.setHotel(hotel.getId_Hotel());
            } else {
                reservation.setHotel(hotelDAO.getIdHotelByRoom(room.getId_Room()));
            }

            reservation.setRoom(room.getId_Room());
            reservation.setUser(user.getId_user());

            reservation.setDateFrom(dateFrom);
            reservation.setDateBefor(dateBefor);

            if (quantityRoom < 1) {
                quantityRoom = 1;
            }
            int actyalRoomQuant = roomDAO.getActualRoomQuantyty(room.getId_Room());
            if(quantityRoom<=actyalRoomQuant) {
                reservation.setQuantityRoom(quantityRoom);
                roomDAO.updateActualRoomQuantyty((int)reservation.getRoom(),
                                                  roomDAO.getAllRoomQuantyty((int)room.getId_Room()),
                                                  roomDAO.getActualRoomQuantyty(room.getId_Room()));
            } else {
                rollback();
                throw new ServiceException("Error toReservation Room " + reservation);
            }

            reservation.setPriceAll( room.getPrice()* quantityRoom);
            reservation = reservationDAO.save(reservation);
            commit();
            return reservation;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error toReservation Room " + reservation, e);
        }
    }
    public Reservation toReservation (Room room, User user, Date dateFrom, Date dateBefor, int quantityRoom){
        return toReservation(null, room, user, dateFrom, dateBefor, quantityRoom);
    }


    @Override
    public Reservation get(Serializable id) {
        try {
            return reservationDAO.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Reservation by id" + id);
        }
    }

    @Override
    public void update(Reservation reservation) {
        try {
            startTransaction();
            reservationDAO.update(reservation);
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error updating Reservation " + reservation);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return reservationDAO.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting Reservation by id" + id);
        }
    }

    @Override
    public List<Reservation> returnReservationUser (Serializable userId) {
        try {
            return reservationDAO.returnReservationUser(userId);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Orders by userId" + userId);
        }
    }


    private ResevationServiceImpl() {

    }

    public static ReservationService getInstance() {
        ReservationService reservationService = INSTANCE;
        if (reservationService == null) {
            synchronized (ResevationServiceImpl.class) {
                reservationService = INSTANCE;
                if (reservationService == null) {
                    INSTANCE = reservationService = new ResevationServiceImpl();
                }
            }
        }

        return reservationService;
    }


}
