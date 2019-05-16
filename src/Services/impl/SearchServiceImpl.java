package Services.impl;

import DAO.*;
import DAO.impl.*;
import Entities.*;
import Services.SearchService;
import Services.ServiceException;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchServiceImpl extends AbstractService implements SearchService {
    private static volatile SearchService INSTANCE = null;

    private ReservationDAO reservationDAO = ReservationDAOImpl.getInstance();
    private UserDAO userDAO = UserDAOImpl.getInstance();
    private HotelDAO hotelDAO = HotelDAOImpl.getInstance();
    private RoomDAO roomDAO = RoomDAOImpl.getInstance();
    private SearchDAO searchDAO = SearchDAOImpl.getInstance();

    @Override
    public Search saveSearch (Date dateFrom, Date dateBefor, int person, int children, int amountRoom) {
        Search search = new Search();
        search.setDateFromSearch(dateFrom);
        search.setDateBeforSearch(dateBefor);
        search.setPerson(person);
        search.setPersonChildren(children);
        search.setAmountRoom(amountRoom);
        try {
            startTransaction();
            searchDAO.save(search);

            commit();
            return search;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error toSearch  " + search, e);
        }
    }




    @Override
    public List<Room> toSearch(Search search) {
        Room room = new Room();
        List<Room> listRoom = new ArrayList<>();
//        TypeRoom typeRoom = new TypeRoom();

        try {

            startTransaction();
            // написать запрос в соответсвти  с количеством людей тип номера ID выводящий
            // typeRoom=


//            listRoom = roomDAO.getRoomType(typeRoom.getKeyType());
            for (Room r:listRoom) {
                List<Reservation> listReservation = new ArrayList<>();
                listReservation = reservationDAO.getReservationToSearc((int)r.getId_Room(), search.getDateFromSearch(), search.getDateBeforSearch());
                for (Reservation resv:listReservation) {
                    if(r.getId_Room()==resv.getRoom()) {
                        listRoom.remove(r);
                    }
                }

            }

            commit();
            return listRoom;
        }catch (SQLException e) {
            rollback();
            throw new ServiceException("Error toSearch  " + search, e);
        }


    }


    @Override
    public Search get(Serializable id) {
        try {
            return searchDAO.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Search by id" + id);
        }
    }

    @Override
    public void update(Search search) {
        try {
            searchDAO.update(search);
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error updating Search " + search);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return searchDAO.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting Search by id" + id);
        }
    }

     private SearchServiceImpl() {

    }

    public static SearchService getInstance() {
        SearchService searchService = INSTANCE;
        if (searchService == null) {
            synchronized (SearchServiceImpl.class) {
                searchService = INSTANCE;
                if (searchService == null) {
                    INSTANCE = searchService = new SearchServiceImpl();
                }
            }
        }

        return searchService;
    }




}
