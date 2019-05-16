package DAO;

import Entities.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface SearchDAO extends DAO<Search> {
   /*
    //возвращает кооличество  дней забранированных данным пользователем (Befor-From)
    int getDateReservation(Serializable id_search) throws SQLException;
    //возвращает общуюю стоимость за все дни, все комноты, всех людей выбранных данным пользователем номеров
    Double getAllPrice (Serializable id_search, Serializable id_Room) throws SQLException;

    //возвращает всех зарегистрированных пользователь
    List<User> getAllUser() throws SQLException;
    //возвращает все зарегистрированные гостиницы
    List<Hotel> getAllHotel() throws SQLException;

    //возвращает все гостиницы по выбрангому месту
    List <Hotel> getAllHotelPlace (Serializable locationCity) throws SQLException;

    //вернуть все однотипные комнаты  из всех гастиниц с учетом свободна или нет
    List<Room> getAllRoomByAllHotel(Serializable typeRoom)throws SQLException;
    //вернуть все однотипные комнаты  из всех гастиниц данной местности с учетом свободна или нет
    List<Room> getAllRoomByAllHotelPlace(Serializable typeRoom,Serializable locationCity)throws SQLException;
    */

}
