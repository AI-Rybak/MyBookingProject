package Entities;


import Entities.TypeHotel.TypeHotel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class Hotel {
    private long id_Hotel;
    private String nameHotel;
    private long typeHotel;
    private String locationCountr;
    private String locationCity;
    private int star;
    private List<Room> allRoomHotel = new ArrayList<>();

    public Hotel(String nameHotel, long typeHotel, String locationCountr, String locationCity, int star) {
        this.nameHotel = nameHotel;
        this.typeHotel = typeHotel;
        this.locationCountr = locationCountr;
        this.locationCity = locationCity;
        this.star = star;
    }

}
