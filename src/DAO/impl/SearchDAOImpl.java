package DAO.impl;

import DAO.ReservationDAO;
import DAO.SearchDAO;
import Entities.Reservation;
import Entities.Search;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ConnectionDB.ConnectionManager.getConnection;

public class SearchDAOImpl implements SearchDAO {
    private static volatile SearchDAO INSTANCE = null;

    private static final String saveSearch = "INSERT INTO search (DATE_FROM, DATE_BEFOR, PERSON, CHILDREN, AMOUNT_ROOM, USER) VALUES (?,?,?,?,?,?)";
    private static final String updateSearch = "UPDATE search SET DATE_FROM=?, DATE_BEFOR=?, PERSON=?, CHILDREN=?, AMOUNT_ROOM=? WHERE ID=?";
    private static final String getSearch = "SELECT * FROM search WHERE ID=?";
    private static final String deleteSearch = "DELETE FROM search WHERE ID=?";
//    private static final String returnReservationUser = "SELECT * FROM reservation WHERE reservation.USER=?";



    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDel;
    //    private PreparedStatement psRetResUser;
    private ResultSet rs;

    {
        try {
            psSave = getConnection().prepareStatement(saveSearch, 1);
            psUpdate = getConnection().prepareStatement(updateSearch);
            psGet = getConnection().prepareStatement(getSearch);
            psDel = getConnection().prepareStatement(deleteSearch);
//            psRetResUser = getConnection().prepareStatement(returnReservationUser);

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    private SearchDAOImpl() {
    }

    public static SearchDAO getInstance() {
        SearchDAO searchDAO = INSTANCE;
        if (searchDAO == null) {
            synchronized (SearchDAOImpl.class) {
                searchDAO = INSTANCE;
                if (searchDAO == null) {
                    INSTANCE = searchDAO = new SearchDAOImpl();
                }
            }
        }

        return searchDAO;
    }

    @Override
    public Search save(Search search) throws SQLException {
        psSave.setDate(1, search.getDateFromSearch());
        psSave.setDate(2, search.getDateBeforSearch());
        psSave.setInt(3, search.getPerson());
        psSave.setInt(4, search.getPersonChildren());
        psSave.setInt(5, search.getAmountRoom());

        psSave.executeUpdate();

        rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            search.setId_search(rs.getInt(1));
        }
        close(rs);
        return search;
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
    public Search get(Serializable id) throws SQLException {
        psGet.setInt(1, (int)id);
        psGet.executeQuery();

        rs = psGet.getResultSet();
        if (rs.next()) {
            Search search = new Search();

            search.setId_search(rs.getInt(1));
            search.setDateFromSearch(rs.getDate(2));
            search.setDateBeforSearch(rs.getDate(3));
            search.setPerson(rs.getInt(4));
            search.setPersonChildren(rs.getInt(5));
            search.setAmountRoom(rs.getInt(6));
            return search;
        }
        close(rs);
        return null;
    }

    @Override
    public int update(Search search) throws SQLException {
        psUpdate.setDate(1, search.getDateFromSearch());
        psUpdate.setDate(2, search.getDateBeforSearch());
        psUpdate.setInt(3, search.getPerson());
        psUpdate.setInt(4, search.getPersonChildren());
        psUpdate.setInt(5, search.getAmountRoom());
        psUpdate.setInt(6, (int) search.getId_search());
        return psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDel.setInt(1, (int)id);
        return psDel.executeUpdate();
    }
/*
    @Override
    public List<Reservation> returnReservationUser (Serializable id_user) throws SQLException {
        List<Reservation> list = new ArrayList<>();
        psRetResUser.setInt(1, (int) id_user);
        psRetResUser.executeQuery();
        rs = psGet.getResultSet();
        if (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setId_reservation(rs.getInt(1));
            reservation.setHotel(rs.getInt(2));
            reservation.setRoom(rs.getInt(3));
            reservation.setQuantityRoom(rs.getInt(4));
            reservation.setDateFrom(rs.getDate(5));
            reservation.setDateBefor(rs.getDate(6));
            reservation.setPriceAll(rs.getDouble(7));
            reservation.setUser(rs.getInt(8));
            list.add(reservation);
        }
        close(rs);
        return list;
    }
 */

}
