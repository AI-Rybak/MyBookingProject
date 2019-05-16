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
public class Reservation {
    private long id_reservation;
    private long hotel;
    private long room;
    private int quantityRoom;
    private Date dateFrom;
    private Date dateBefor;
    private Double priceAll;
    private long user;

    Reservation (long hotel, long room, int quantityRoom, Date dateFrom, Date dateBefor, Double priceAll, long user) {
        this.hotel = hotel;
        this.room = room;
        this.quantityRoom = quantityRoom;
        this.dateFrom = dateFrom;
        this.dateBefor = dateBefor;
        this.priceAll = priceAll;
        this.user = user;
    }

}

