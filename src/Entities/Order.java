package Entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private long id_order;
    private long user;
    private long hotel;
    private long room;
    private Date dateFrom;
    private Date dateBefor;
    private Double priceAll;

    Order(long user, long hotel, long room, Date dateFrom, Date dateBefor) {
        this.user = user;
        this.hotel = hotel;
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateBefor = dateBefor;

    }
    Order(Reservation reservation) {
        this.user = reservation.getUser();
        this.hotel = reservation.getHotel();
        this.room = reservation.getRoom();
        this.dateFrom = reservation.getDateFrom();
        this.dateBefor = reservation.getDateBefor();
        this.priceAll = reservation.getPriceAll();
    }



}
