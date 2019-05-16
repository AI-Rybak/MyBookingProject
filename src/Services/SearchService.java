package Services;

import Entities.Reservation;
import Entities.Room;
import Entities.Search;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public interface SearchService {
    Search saveSearch (Date dateFrom, Date dateBefor, int person, int children, int amountRoom);
    List<Room> toSearch(Search search);
    void update(Search search);
    Search get(Serializable id);
    int delete(Serializable id);
}
