package tw.idv.petradisespringboot.roomType.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import tw.idv.petradisespringboot.roomType.vo.RoomPic;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AllHotelDTO {
    private String hotelName;
    private String hotelAddress;
    private String roomTypeName;
    private String roomTypeAbout;
    private Integer roomTypePrice;
    private Integer reviewScoreTotal;
    private List<String> roomPics = new ArrayList<>();
}
