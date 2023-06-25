package tw.idv.petradisespringboot.roomType.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class searchHotelDTO {
    private String location;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String petType;
    private String petSize;
    private String hotelName;
}
