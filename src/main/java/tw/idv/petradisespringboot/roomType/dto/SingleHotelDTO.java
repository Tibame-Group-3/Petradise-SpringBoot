package tw.idv.petradisespringboot.roomType.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class SingleHotelDTO {
    private String hotelName;
    private String hotelAddress;
    private String roomTypeName;
    private String roomTypeAbout;
    private Integer roomTypePrice;
    private LocalDateTime inDay;
    private LocalDateTime outDay;
}
