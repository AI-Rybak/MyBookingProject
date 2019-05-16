package Services.impl;

import DAO.HotelDAO;
import DAO.ReservationDAO;
import DAO.RoomDAO;
import DAO.UserDAO;
import DAO.impl.HotelDAOImpl;
import DAO.impl.ReservationDAOImpl;
import DAO.impl.RoomDAOImpl;
import DAO.impl.UserDAOImpl;
import Services.ReservationService;

public class RoomServiceImpl {
    private static volatile ReservationService INSTANCE = null;

    private ReservationDAO reservationDAO = ReservationDAOImpl.getInstance();
    private UserDAO userDAO = UserDAOImpl.getInstance();
    private HotelDAO hotelDAO = HotelDAOImpl.getInstance();
    private RoomDAO roomDAO = RoomDAOImpl.getInstance();


}
