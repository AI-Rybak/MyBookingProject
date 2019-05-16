package Entities.TypeRoom;

import lombok.Getter;

@Getter
public class DoubleRoom1 implements TypeRoom {
    private long id_TypeRoom = 3;
    private String NAME_TYPE = "DOUBLE_ROOM";
    private Integer SINGL_BED = 2;
    private Integer DOUBLE_BED = 1;
    private Integer EXTRA_BED=0;
    private Integer PERSON_MAX = 2;
    private Integer CH_MAX=0;

    public DoubleRoom1(){
        id_TypeRoom = 3;
        NAME_TYPE = "DOUBLE_ROOM";
        SINGL_BED = 2;
        DOUBLE_BED = 1;
        EXTRA_BED=1;
        PERSON_MAX = 2;
        CH_MAX=0;

    }


}
