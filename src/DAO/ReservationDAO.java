package DAO;

import Entities.Reservation;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO extends DAO<Reservation>{
  /*
    //отправить и валидация платежа за выбранные номера отправка его в учет(в Order)
    boolean toPay (Serializable id_reservation);
    //снятие брони по истечению определенного времени и не оплаты
    Reservation toNotReservation (Serializable id_reservation);
*/
    //показать выбранные реализации:
        //всех данного пользователя
    List<Reservation> returnReservationUser (Serializable id_user) throws SQLException ;
    //вывести все зарезервированные комнаты ктороые попадают в данный интервал
    List<Reservation> getReservationToSearc (Serializable roomID, Date dateFrom, Date dateBefor) throws SQLException;
        //всех данного с определеноой даты
//    List<Reservation> returnReservationUserToDate (Serializable id_user, Date dateFrom);
        //всех данного с-по определеноую дату
//    List<Reservation> returnReservationUserToDate (Serializable id_user, Date dateFrom, Date dateBefor);



}
