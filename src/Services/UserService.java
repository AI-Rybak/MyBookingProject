package Services;

import Entities.Search;
import Entities.User;

public interface UserService {
    User toRegister (String name, String surname, String mail, String login, String password);
    User toCommeIn (String login, String password);
}
