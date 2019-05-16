package Entities.TypeHotel;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTypeHotel {
    private Integer key;
    private String valuas;
    private static Map<Integer, String> typeHotel = createMap();

    private static  Map<Integer, String> createMap() {
        typeHotel = new HashMap<>();
        typeHotel.put(1,"RESORT_HOTEL");
        typeHotel.put(2,"BUSINESS_HOTEL");
        typeHotel.put(3,"BOUTIQUE_HOTEL");
        typeHotel.put(4,"APARTMENT_HOTEL");
        typeHotel.put(5,"BED_AND_BREAKFAST_HOTEL");
        typeHotel.put(6,"CHALET");//Шале
        typeHotel.put(7,"HOSTEL");
        typeHotel.put(8,"MINI_HOTEL");
        typeHotel.put(9,"SPA_HOTEL");
        typeHotel.put(10,"MOTEL");
        typeHotel.put(11,"GUEST_HOUSE");//гостевой дом
        typeHotel.put(12,"LODGES");
        typeHotel.put(13,"BOARDING_HOUSE"); //Пансионаты
        typeHotel.put(14,"ECO_HOTEL"); //Эко-отели
        typeHotel.put(0,"OTHER_HOTEL");
//        System.out.println(typeHotel.size());
        return typeHotel;
    }

    public void saveType(String typeName) {
//            System.out.println(typeMap);
        if(typeHotel.containsValue(typeName)) {
            for (Map.Entry<Integer,String> entry:typeHotel.entrySet()) {
                if(entry.getValue().equals(typeName)) {
                    this.key = entry.getKey();
                    this.valuas = typeName;
                }
            }
        }
    }

    public void saveType(int typeKey) {
//            System.out.println(typeMap);
        if(typeHotel.containsKey(typeKey)) {
            for (Map.Entry<Integer,String> entry:typeHotel.entrySet()) {
                if(entry.getKey().equals(typeKey)) {
                    this.key = entry.getKey();
                    this.valuas = entry.getValue();
                }
            }
        }
    }


    public Integer getKeyType() {
        return key;
    }

    public void setKeyType(Integer key) {
        this.key = key;
    }

    public String getValuasType() {
        return valuas;
    }
}
