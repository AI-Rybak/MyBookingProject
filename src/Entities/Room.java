package Entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Getter
@Setter
public class Room implements Serializable {
    private long id_Room;
    private String nameRoom;
    private long typeRoom;
    private Double price;
    private int statusRoom;

    public Room(String nameRoom, long typeRoom, Double price) {
        this.nameRoom = nameRoom;
        this.typeRoom = typeRoom;
        this.price = price; //price/day
    }
}

