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
public class Search {
    private long id_search;
    private Date dateFromSearch;
    private Date dateBeforSearch;
    private int person;
    private int personChildren;
    private  int amountRoom;

    Search (Date dateFromSearch, Date dateBeforSearch, int person, int personChildren, int amountRoom) {
        this.dateFromSearch = dateFromSearch;
        this.dateBeforSearch = dateBeforSearch;
        this.person = person;
        this.personChildren = personChildren;
        this.amountRoom = amountRoom;
    }

}
