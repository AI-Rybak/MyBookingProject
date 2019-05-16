package Entities.TypeRoom;

import lombok.Getter;

@Getter
public class SingleRoomCH {
    private long id_TypeRoom;
    private String NAME_TYPE;
    private Integer SINGL_BED;
    private Integer DOUBLE_BED;
    private Integer EXTRA_BED;
    private Integer PERSON_MAX;
    private Integer CH_MAX;

    public SingleRoomCH(){
        id_TypeRoom = 2;
        NAME_TYPE = "SINGL_ROOM+CHILD";
        SINGL_BED = 1;
        DOUBLE_BED = 0;
        EXTRA_BED=1;
        PERSON_MAX = 1;
        CH_MAX=1;

    }



}
