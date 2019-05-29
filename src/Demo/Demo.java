package Demo;

import DAO.HotelDAO;
import DAO.RoomDAO;
import DAO.UserDAO;
import DAO.impl.HotelDAOImpl;
import DAO.impl.RoomDAOImpl;
import DAO.impl.UserDAOImpl;
import Entities.Hotel;
import Entities.Room;
import Entities.TypeHotel.TypeHotel;
import Entities.TypeRoom.SingleRoom;
import Entities.TypeRoom.TypeRoom;
import Entities.User;

import java.io.Serializable;
import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) {
        saveDemoUser();
//        getDemoUser();
//        saveDemoHotel();
//        getDemoHotel();
//        saveDemoRoom();
//        getDemoRoom();
//        TypeRoom typeRoom = new SingleRoom();
//        System.out.println(typeRoom.getNAME_TYPE());

/*
        TypeRoom typeRoom = new TypeRoom();
        typeRoom.saveType("TRIPLE_ROOM");
        System.out.println(typeRoom.getKeyType());
        TypeRoom typeRoom1 = new TypeRoom();
        typeRoom1.saveType(4);
        System.out.println(typeRoom1.getValuasType());

 */




    }//конец метода main





/*
    public static void saveDemoRoom(){
        TypeRoom typeRoom = new TypeRoom();
        typeRoom.saveType("DOUBLE_ROOM");
        Room room = new Room("room1", typeRoom, 43.50);
        Room room1 = new Room();
        try {
            RoomDAO roomDAO = RoomDAOImpl.getInstance();
            room1 = roomDAO.save(room);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("после сохранения");
        System.out.println(room1.getId_Room()+" " + room1.getNameRoom()+" "+room1.getTypeRoom().getValuasType()+" "+room1.getPrice()+" "+room1.getStatusRoom());
    }


    public static void getDemoRoom () {
        Serializable id = 5;
        Room room = null;
        try {
            RoomDAO roomDAO = RoomDAOImpl.getInstance();
            room = roomDAO.get(id);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("проверяем метод get: ");
        System.out.println(room.getId_Room()+" " + room.getNameRoom()+" "+room.getTypeRoom().getValuasType()+" "+room.getPrice()+" "+room.getStatusRoom());
    }
*/
/*
    public static void saveDemoHotel(){
        TypeHotel typeHotel = new TypeHotel();
        typeHotel.saveType("RESORT_HOTEL");
        Hotel hotel1 = new Hotel("Minsk3", typeHotel, "RB", "Minsk2", 3);


        Hotel hotel = new Hotel();
        try {
            HotelDAO hotelDAO = HotelDAOImpl.getInstance();
            hotel = hotelDAO.save(hotel1);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("после сохранения");
        System.out.println(hotel.getId_Hotel()+" " + hotel.getNameHotel()+" "+hotel.getTypeHotel().getValuasType()+" "+hotel.getLocationCountr()+" "+hotel.getLocationCity()+" "+hotel.getStar());
    }


    public static void getDemoHotel () {
        Serializable id = 4;
        Hotel hotel = null;
        try {
            HotelDAO hotelDAO = HotelDAOImpl.getInstance();
            hotel = hotelDAO.get(id);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("проверяем метод get: ");
        System.out.println(hotel.getId_Hotel()+" " + hotel.getNameHotel()+" "+hotel.getTypeHotel().getValuasType()+" "+hotel.getLocationCountr()+" "+hotel.getLocationCity()+" "+hotel.getStar());
    }
*/

    public static void saveDemoUser(){
        User user = new User("Sasha2", "Rybak3", "sash552@yandex.ru","sash52", "1234ty");
        System.out.println("Объект в JAVA: ");
        System.out.println(user.getId_user()+" " + user.getNameUser()+" "+user.getSurnameUser()+" "+user.getMail()+" "+user.getLogin()+" "+user.getPassword());
        try {
            UserDAO userDAO = UserDAOImpl.getInstance();
            user = userDAO.save(user);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("после сохранения");
        System.out.println(user.getId_user()+" " + user.getNameUser()+" "+user.getSurnameUser()+" "+user.getMail()+" "+user.getLogin());

    }

    public static void getDemoUser () {
        Serializable id = 6;
        User user = null;
        try {
            UserDAO userDAO = UserDAOImpl.getInstance();
            user = userDAO.get(id);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("проверяем метод get: ");
        System.out.println(user.getId_user()+" " +user.getNameUser()+ "" +
                " "+ user.getMail() + " " + user.getSurnameUser());
    }




}
