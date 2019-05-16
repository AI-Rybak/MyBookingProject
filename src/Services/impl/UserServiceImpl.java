package Services.impl;

import DAO.*;
import DAO.impl.*;
import Entities.Reservation;
import Entities.Room;
import Entities.Search;
import Entities.User;
import Services.ReservationService;
import Services.SearchService;
import Services.ServiceException;
import Services.UserService;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl extends AbstractService implements UserService {
    private static volatile UserService INSTANCE = null;

    private ReservationDAO reservationDAO = ReservationDAOImpl.getInstance();
    private UserDAO userDAO = UserDAOImpl.getInstance();
    private HotelDAO hotelDAO = HotelDAOImpl.getInstance();
    private RoomDAO roomDAO = RoomDAOImpl.getInstance();
    private SearchDAO searchDAO = SearchDAOImpl.getInstance();

    private UserServiceImpl() {

    }

    public static UserService getInstance() {
        UserService userService = INSTANCE;
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceImpl();
                }
            }
        }

        return userService;
    }



    @Override
    public User toRegister (String name, String surname, String mail, String login, String password) {
        User user = new User();
        user.setNameUser(name);
        user.setSurnameUser(surname);


        if(validateMail(mail)){
           user.setMail(mail);
        } else {
            return user = null; // что-то делать если неверно введен адресс почты
        }

        if (password.length()>=6) {
            user.setPassword(password);
        }


            try {
            startTransaction();
            if(login.equals(userDAO.getLogin((int)user.getId_user()))) {
               user.setLogin(login);
            } else {
                rollback();
            }
            user = userDAO.save(user);
            commit();
            return user;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error toReservation Room " + user, e);
        }
    }

    @Override
    public User toCommeIn (String login, String password) {
        User user = new User();
        try {
            startTransaction();
            user = userDAO.getByLogin(login);
            commit();
            if(password.equals(user.getPassword())) {
                return user;
            } else {
                return user = null; // что-то делать если неверно введен пароль
            }

        }catch (SQLException e) {
            rollback();
            throw new ServiceException("Error toReservation Room " + user, e);
        }

    }


    private static boolean validateMail (String str) {
        Pattern p = Pattern.compile("(\\b([a-zA-Z]+[_]?\\w*)@(\\w+).((\\w{2})|(com)|(org))\\b)");
        Matcher m = p.matcher(str);
        boolean res = false;
        if (m.find()) {
           res = str.equals(m.group());
        }
        return res;
    }

}
