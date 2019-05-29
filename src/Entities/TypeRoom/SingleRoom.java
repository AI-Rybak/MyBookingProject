package Entities.TypeRoom;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class SingleRoom implements TypeRoom{
    private long id_TypeRoom;
    private String NAME_TYPE;
    private Integer SINGL_BED;
    private Integer DOUBLE_BED;
    private Integer EXTRA_BED;
    private Integer PERSON_MAX;
    private Integer CH_MAX;

    public SingleRoom(){
        id_TypeRoom = 1;
        NAME_TYPE = "SINGL_ROOM";
        SINGL_BED = 1;
        DOUBLE_BED = 0;
        EXTRA_BED=0;
        PERSON_MAX = 1;
        CH_MAX=0;

    }


}

